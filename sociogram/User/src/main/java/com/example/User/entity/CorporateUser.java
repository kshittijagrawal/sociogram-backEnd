package com.example.User.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@ToString
@Document(collection = CorporateUser.COLLECTION_NAME)
public class CorporateUser {

    public static final String COLLECTION_NAME = "corporateUser";

    @Id
    String userId;
    String username;
    List<String> ownerList;
    List<String> moderatorList;
    String bio;
    String imageURL;
    List<String> postIdList;
    List<String> storyIdList;
    List<Follower> followerList;
    List<Following> followingList;
}
