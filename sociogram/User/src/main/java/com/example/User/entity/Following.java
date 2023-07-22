package com.example.User.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Data
@ToString
//@Document(collection = Following.COLLECTION_NAME)
public class Following {

//    public static final String COLLECTION_NAME = "following";

    @Id
    String userId;
    String username;
    String imageURL;
}
