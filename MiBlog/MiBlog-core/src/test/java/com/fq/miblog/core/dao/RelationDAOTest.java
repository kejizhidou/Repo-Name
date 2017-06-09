package com.fq.miblog.core.dao;

import com.fq.miblog.core.TestBase;
import com.fq.miblog.client.domain.Relation;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author jifang
 * @since 16/3/13 下午3:35.
 */
public class RelationDAOTest extends TestBase {

    @Autowired
    private RelationDAO dao;

    @Test
    public void testFollow() throws Exception {
        Relation relation = new Relation(2, 2);
        dao.follow(relation);
        relation.setTo(3);
        dao.follow(relation);
        relation.setTo(5);
        dao.follow(relation);
        //System.out.println(follow);
    }

    @Test
    public void unfollow() {
        Relation relation = new Relation(1, 2);
        boolean unfollow = dao.unfollow(relation);
        System.out.println(unfollow);
    }

    @Test
    public void getFollowing() {
        List<Long> followings = dao.getFollowings(1);
        System.out.println(followings);
    }

    @Test
    public void getFollower() {
        List<Long> followers = dao.getFollowers(3);
        System.out.println(followers);
    }

    @Test
    public void with() {
        List<Long> longs = dao.withFollowings(1, 2);
        System.out.println(longs);
    }
}