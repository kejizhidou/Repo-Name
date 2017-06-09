package com.fq.miblog.core.dao.impl;

import com.fq.miblog.client.domain.MiBlog;
import com.fq.miblog.core.constant.Constant;
import com.fq.miblog.core.dao.MiBlogDAO;
import com.fq.miblog.core.constant.PostConstant;
import com.fq.miblog.core.dao.RelationDAO;
import com.fq.miblog.core.util.CollectionUtil;
import com.fq.miblog.core.util.Util;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jifang
 * @since 16/3/13 下午3:43.
 */
@Repository
public class MiBlogDAOImpl implements MiBlogDAO {

    @Autowired
    private Jedis redis;

    @Autowired
    private RelationDAO relationDAO;

    @Override
    public long publish(MiBlog miBlog) {
        // 获得微博ID
        long id = redis.incr(Constant.MI_BLOG_COUNT);

        // 插入微博数据
        Map<String, String> map = new HashMap<>();
        map.put(Constant.AUTHOR, String.valueOf(miBlog.getAuthor()));
        map.put(Constant.TIME, String.valueOf(System.currentTimeMillis()));
        map.put(Constant.CONTENT, miBlog.getContent());
        String dataKey = String.format(Constant.MI_BLOG_DATA, id);
        redis.hmset(dataKey, map);

        // 插入到当前用户已发表微博
        String myKey = String.format(Constant.MI_BLOG_MY, miBlog.getAuthor());
        redis.lpush(myKey, String.valueOf(id));

        // 为每一个自己的粉丝推送微博消息
        // 获得所有粉丝
        List<Long> followers = relationDAO.getFollowers(miBlog.getAuthor());
        for (Long follower : followers) {
            String key = String.format(Constant.MI_BLOG_FLOW, follower);
            redis.lpush(key, String.valueOf(id));
        }

        return id;
    }

    @Override
    public boolean unpublish(long uid, long id) {
        String sId = String.valueOf(id);
        String myKey = String.format(Constant.MI_BLOG_MY, uid);
        // 确实是uid发布的微博
        if (redis.lrem(myKey, 1L, sId) == 1L) {
            // 删除所有粉丝微博
            List<Long> followers = relationDAO.getFollowers(uid);
            for (Long follower : followers) {
                String flowKey = String.format(Constant.MI_BLOG_FLOW, follower);
                redis.lrem(flowKey, 1L, sId);
            }

            // 删除微博数据
            String dataKey = String.format(Constant.MI_BLOG_DATA, id);
            redis.del(dataKey);

            return true;
        }
        return false;
    }

    @Override
    public MiBlog getBlog(long id) {
        String key = String.format(Constant.MI_BLOG_DATA, id);
        Map<String, String> map = redis.hgetAll(key);
        return Util.mapToSimpleObject(map, MiBlog.class);
    }

    @Override
    public List<Long> getMyBlog(long uid) {
        String key = String.format(Constant.MI_BLOG_MY, uid);
        List<String> sids = redis.lrange(key, 0, -1);
        return CollectionUtil.stringToLong(sids);
    }

    @Override
    public List<Long> getFollowingBlog(long uid) {
        String key = String.format(Constant.MI_BLOG_FLOW, uid);
        List<String> sids = redis.lrange(key, 0, -1);
        return CollectionUtil.stringToLong(sids);
    }

    @Override
    public List<Long> getBlogFlow(long uid) {
        List<Long> myList = this.getMyBlog(uid);
        List<Long> flowList = this.getFollowingBlog(uid);
        int myEndIndex = 0;
        for (; myEndIndex < myList.size(); ++myEndIndex) {
            Long my = myList.get(myEndIndex);

            boolean isEnd = true;
            for (int i = 0; i < flowList.size(); ++i) {
                long flow = flowList.get(i);
                if (my > flow) {
                    flowList.add(i, my);
                    isEnd = false;
                    break;
                }
            }
            if (isEnd)
                break;
        }

        // 将所有my < flow的元素填充
        flowList.addAll(myList.subList(myEndIndex, myList.size()));

        return flowList;
    }
}
