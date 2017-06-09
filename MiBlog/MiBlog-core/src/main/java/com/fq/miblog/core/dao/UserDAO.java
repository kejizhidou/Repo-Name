package com.fq.miblog.core.dao;

import com.fq.miblog.client.domain.User;

import java.util.List;

/**
 * @author jifang
 * @since 16/3/13 下午12:33.
 */
public interface UserDAO {

    long register(User user);

    boolean login(String email, String password);

    long getUserId(String email);

    User getUser(long id);

    List<Long> newUserList(int limit);
}
