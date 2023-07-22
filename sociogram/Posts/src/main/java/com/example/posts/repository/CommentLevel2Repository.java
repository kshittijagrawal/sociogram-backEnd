package com.example.posts.repository;

import com.example.posts.entity.CommentLevel2;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentLevel2Repository extends MongoRepository<CommentLevel2, String> {
    void deleteByCommentId(String commentId);
}
