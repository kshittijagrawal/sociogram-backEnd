package com.example.posts.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class CommentLevel2DTO {

    private String commentLevel2Id;
    private String postId;
    private String commentId;
    private String userId;
    private String commentLevel2Text;
    private Date dateCommentedLevel2;
}
