package com.fq.miblog.client.service;

import com.fq.miblog.client.domain.MiBlog;

import java.util.List;

/**
 * @author jifang
 * @since 16/3/13 下午4:39.
 */
public interface MiBlogService {

    long publish(long author, String content);

    boolean unpublish(long uid, long id);

    // 获取自己发的微博
    List<MiBlog> getMyBlog(long uid);

    // 获取关注人的微博
    List<MiBlog> getFollowingBlog(long uid);

    // 获取微博流(所有微博)
    List<MiBlog> getBlogFlow(long uid);
}
