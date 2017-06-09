package com.fq.miblog.client.service;

import com.fq.miblog.client.domain.User;

import java.util.List;

/**
 * @author jifang
 * @since 16/3/13 下午4:44.
 */
public interface RelationService {

    boolean follow(long from, long to);

    boolean unfollow(long from, long to);

    List<User> getFollowings(long id);

    List<User> getFollowers(long id);

    List<User> withFollowings(long... ids);
}
