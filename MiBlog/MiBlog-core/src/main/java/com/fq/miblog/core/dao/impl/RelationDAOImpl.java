package com.fq.miblog.core.dao.impl;

import com.fq.miblog.core.constant.Constant;
import com.fq.miblog.core.dao.RelationDAO;
import com.fq.miblog.client.domain.Relation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author jifang
 * @since 16/3/13 下午3:29.
 */
@Repository
public class RelationDAOImpl implements RelationDAO {

    @Autowired
    private Jedis redis;

    @Override
    public boolean follow(Relation relation) {
        if (relation.getFrom() != relation.getTo()) {

            // 主动关注
            String following = String.format(Constant.RELATION_FOLLOWING, relation.getFrom());
            Long result1 = redis.sadd(following, String.valueOf(relation.getTo()));

            // 被动被关注
            String follower = String.format(Constant.RELATION_FOLLOWER, relation.getTo());
            Long result2 = redis.sadd(follower, String.valueOf(relation.getFrom()));

            return result1 == 1L && result2 == 1L;
        }

        return false;
    }

    @Override
    public boolean unfollow(Relation relation) {
        if (relation.getFrom() != relation.getTo()) {

            // 取消主动关注
            String following = String.format(Constant.RELATION_FOLLOWING, relation.getFrom());
            Long result1 = redis.srem(following, String.valueOf(relation.getTo()));

            // 取消被动关注
            String follower = String.format(Constant.RELATION_FOLLOWER, relation.getTo());
            Long result2 = redis.srem(follower, String.valueOf(relation.getFrom()));

            return result1 == 1L && result2 == 1L;
        }

        return false;
    }

    @Override
    public List<Long> getFollowings(long id) {
        String following = String.format(Constant.RELATION_FOLLOWING, id);
        Set<String> members = redis.smembers(following);
        return stringToLong(members);
    }

    @Override
    public List<Long> getFollowers(long id) {
        String following = String.format(Constant.RELATION_FOLLOWER, id);
        Set<String> members = redis.smembers(following);
        return stringToLong(members);
    }

    @Override
    public List<Long> withFollowings(long... ids) {
        String[] keys = new String[ids.length];
        for (int i = 0; i < ids.length; ++i) {
            keys[i] = String.format(Constant.RELATION_FOLLOWING, ids[i]);
        }
        Set<String> sids = redis.sinter(keys);
        return stringToLong(sids);
    }

    private List<Long> stringToLong(Set<String> sets) {
        List<Long> list = new ArrayList<>(sets.size());
        for (String set : sets) {
            list.add(Long.valueOf(set));
        }
        return list;
    }
}
