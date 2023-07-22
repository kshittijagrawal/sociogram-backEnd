package com.example.posts.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EmojiDTO {

    private String emojiType;
    private String userId;
    private String emoji;
}
