package com.fq.miblog.client.service;

import com.fq.miblog.client.domain.User;

import java.util.List;

/**
 * @author jifang
 * @since 16/3/13 下午1:06.
 */
public interface UserService {

    long register(String email, String nickname, String password);

    boolean login(String email, String password);

    List<User> newUserList(int limit);
}
