package com.fq.miblog.core.dao;

import com.fq.miblog.client.domain.MiBlog;

import java.util.List;

/**
 * @author jifang
 * @since 16/3/13 下午3:26.
 */
public interface MiBlogDAO {

    long publish(MiBlog miBlog);

    boolean unpublish(long uid, long id);

    MiBlog getBlog(long id);

    // 获取自己发的微博
    List<Long> getMyBlog(long uid);

    // 获取关注人的微博
    List<Long> getFollowingBlog(long uid);

    // 获取微博流(所有微博)
    List<Long> getBlogFlow(long uid);
}
