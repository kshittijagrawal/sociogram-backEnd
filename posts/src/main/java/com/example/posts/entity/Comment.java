package com.example.posts.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Data
@ToString
@Document(collection = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String commentId;
    private String postId;
    private String userId;
    private String commentText;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCommented;
    private List<String> commentLevel2Ids;
}