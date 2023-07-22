package com.example.User.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.List;

@Data
@ToString
@Document(collection = User.COLLECTION_NAME)
public class User {

    public static final String COLLECTION_NAME = "user";

    @Id
    String userId;
    String username;
    String bio;
    String imageURL;
    List<String> postIdList;
    List<String> storyIdList;
    List<Follower> followerList;
    List<Following> followingList;
    List<Request> requestList;


}
