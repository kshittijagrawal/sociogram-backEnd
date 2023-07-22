package com.example.posts.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@ToString
@Document(collection = "commentlevel2")
public class CommentLevel2 {

    @Id
    private String commentLevel2Id;
    private String postId;
    private String commentId;
    private String userId;
    private String commentLevel2Text;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCommentedLevel2;
}