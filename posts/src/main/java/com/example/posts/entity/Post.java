package com.example.posts.entity;

import lombok.Data;
import lombok.ToString;
import org.bson.conversions.Bson;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@ToString
@Document(collection = "post")
public class Post {

    @Id
    private String postId;
    private String userId;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePosted;
    private String fileType;
    private String filePostedURL;
    private String caption;
    private List<String> categories;

    private List<String> commentIds;
    private List<String> likes;
    private List<String> dislikes;
    private List<Emoji> emojis;
}
