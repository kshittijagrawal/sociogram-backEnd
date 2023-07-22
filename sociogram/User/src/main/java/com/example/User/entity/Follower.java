package com.example.User.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@ToString
//@Document(collection = Follower.COLLECTION_NAME)
public class Follower {

//    public static final String COLLECTION_NAME = "follower";

    @Id
    String userId;
    String username;
    String imageURL;
}
