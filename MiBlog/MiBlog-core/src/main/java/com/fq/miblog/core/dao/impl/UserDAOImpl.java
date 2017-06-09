package com.fq.miblog.core.dao.impl;

import com.fq.miblog.client.domain.User;
import com.fq.miblog.core.constant.Constant;
import com.fq.miblog.core.dao.UserDAO;
import com.fq.miblog.core.util.PasswordUtil;
import com.fq.miblog.core.util.Util;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * @author jifang
 * @since 16/3/13 下午12:37.
 */
@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private Jedis redis;

    @Override
    public long register(User user) {
        long id = -1;

        // 当前email没有注册过
        if (!redis.hexists(Constant.USER_EMAIL_TO_ID, user.getEmail())) {
            // 为用户生成id
            id = redis.incr(Constant.USER_COUNT);
            // 插入email -> id 对应关系
            redis.hset(Constant.USER_EMAIL_TO_ID, user.getEmail(), String.valueOf(id));

            Map<String, String> map = new HashMap<>();
            map.put(Constant.EMAIL, user.getEmail());
            map.put(Constant.PASSWORD, PasswordUtil.encode(user.getPassword()));
            map.put(Constant.NICKNAME, user.getNickname());
            map.put(Constant.REGIST_TIME, String.valueOf(System.currentTimeMillis()));

            // 写入user:[id]:data
            String key = String.format(Constant.USER_ID_DATA, id);
            redis.hmset(key, map);
        }

        return id;
    }

    @Override
    public boolean login(String email, String password) {
        String id = redis.hget(Constant.USER_EMAIL_TO_ID, email);
        if (!Strings.isNullOrEmpty(id)) {

            String key = String.format(Constant.USER_ID_DATA, id);
            Map<String, String> map = redis.hgetAll(key);

            return PasswordUtil.checkEqual(password, map.get(Constant.PASSWORD));
        }

        return false;
    }

    @Override
    public long getUserId(String email) {
        String id = redis.hget(Constant.USER_EMAIL_TO_ID, email);
        if (!Strings.isNullOrEmpty(id)) {
            return Long.valueOf(id);
        }
        return -1;
    }

    @Override
    public User getUser(long id) {
        String key = String.format(Constant.USER_ID_DATA, id);
        Map<String, String> map = redis.hgetAll(key);
        return Util.mapToSimpleObject(map, User.class);
    }

    @Override
    public List<Long> newUserList(int limit) {
        Long maxId = Long.valueOf((redis.get(Constant.USER_COUNT)));
        Long minId = maxId - (limit - 1);
        if (minId < 1) {
            minId = 1L;
        }

        List<Long> ids = new ArrayList<>((int) (maxId - minId + 1));
        for (Long i = maxId; i >= minId; --i) {
            ids.add(i);
        }
        return ids;
    }
}
