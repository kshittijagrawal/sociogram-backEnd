package com.example.posts.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString
@Document(collection = "emoji")
public class Emoji {

    private String emojiType;
    private String userId;
    private String emoji;
}
