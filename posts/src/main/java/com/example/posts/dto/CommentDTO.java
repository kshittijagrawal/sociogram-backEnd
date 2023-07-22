package com.example.posts.dto;

import com.example.posts.entity.CommentLevel2;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@ToString
public class CommentDTO {

    private String commentId;
    private String postId;
    private String userId;
    private String commentText;
    private Date dateCommented;
}
