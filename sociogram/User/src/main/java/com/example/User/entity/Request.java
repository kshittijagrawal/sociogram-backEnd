package com.example.User.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Data
@ToString
//@Document(collection = Request.COLLECTION_NAME)
public class Request {

//    public static final String COLLECTION_NAME = "request";

    @Id
    String userId;
    String username;
    String imageURL;
}
