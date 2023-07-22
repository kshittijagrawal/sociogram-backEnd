package com.example.User.repository;

import com.example.User.entity.Follower;
import com.example.User.entity.Following;
import com.example.User.entity.Request;

public interface UserCustomRepository {
    Boolean savePost(String userId, String postId);

    Boolean unFollow(String userId1, String userId2);

    Follower checkFollowerExists(String userId, String userId1);

    Following checkFollowingExists(String userId, String userId1);

    Request checkRequestExists(String userId, String userId1);
}
