package com.example.posts.repository;

import com.example.posts.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
//    void deleteByCommentLevel2Id(String commentLevel2Id);
}
