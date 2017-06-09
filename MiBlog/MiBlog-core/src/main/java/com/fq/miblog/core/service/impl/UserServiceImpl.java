package com.fq.miblog.core.service.impl;

import com.fq.miblog.client.service.UserService;
import com.fq.miblog.core.dao.UserDAO;
import com.fq.miblog.client.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jifang
 * @since 16/3/13 下午1:07.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO dao;

    @Override
    public long register(String email, String nickname, String password) {
        return dao.register(new User(null, email, nickname, password, null));
    }

    @Override
    public boolean login(String email, String password) {
        return dao.login(email, password);
    }

    @Override
    public List<User> newUserList(int limit) {
        List<Long> ids = dao.newUserList(limit);
        List<User> users = new ArrayList<>(ids.size());
        for (Long id : ids) {
            users.add(dao.getUser(id));
        }
        return users;
    }
}
