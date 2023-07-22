package com.example.User.repository.impl;

import com.example.User.entity.Follower;
import com.example.User.entity.Following;
import com.example.User.entity.Request;
import com.example.User.entity.User;
import com.example.User.repository.UserCustomRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class UserCustomRepositoryImpl implements UserCustomRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Boolean savePost(String userId, String postId) {
        Query query = new Query();

        query.addCriteria(new Criteria().where("userId").is(userId));

        Update update = new Update().push("postIdList", postId);
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, User.class);

        return !(updateResult.getMatchedCount() == 0);

    }

    @Override
    public Boolean unFollow(String userId1, String userId2) {

        Query query = new Query();
        query.addCriteria(new Criteria().where("userId").is(userId1));
        query.addCriteria(new Criteria().where("followingList.userId").is(userId2));

        Update update = new Update();
        update.pull("followingList", new BasicDBObject("userId", userId2));
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, User.class);

        Query query2 = new Query();
        query2.addCriteria(new Criteria().where("userId").is(userId2));
        query2.addCriteria(new Criteria().where("followerList.userId").is(userId1));

        Update update2= new Update();
        update2.pull("followerList", new BasicDBObject("userId", userId1));
        UpdateResult updateResult2 = mongoTemplate.updateFirst(query2, update2, User.class);

        return !(updateResult.getMatchedCount() == 0 || updateResult2.getMatchedCount()==0);

//        Query query = new Query();
//        query.addCriteria(Criteria.where("_id").is(parentCollectionId));
//        query.addCriteria(Criteria.where("subCollection._id").is(subCollectionId));
//
//        Update update = new Update();
//        update.pull("subCollection", new BasicDBObject("_id", userId2));
//
//        mongoTemplate.updateFirst(query, update, ParentCollection.class);


    }

    @Override
    public Follower checkFollowerExists(String userId, String userId1) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        query.addCriteria(Criteria.where("followerList.userId").is(userId1));

        Follower follower = mongoTemplate.findOne(query, Follower.class);

        return follower;
    }

    @Override
    public Following checkFollowingExists(String userId, String userId1) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        query.addCriteria(Criteria.where("followingList.userId").is(userId1));

        Following following = mongoTemplate.findOne(query, Following.class);

        return following;
    }

    @Override
    public Request checkRequestExists(String userId, String userId1) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        query.addCriteria(Criteria.where("requestList.userId").is(userId1));

        Request request = mongoTemplate.findOne(query, Request.class);

        return request;

    }

}
