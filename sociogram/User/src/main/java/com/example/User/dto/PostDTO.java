package com.example.User.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@ToString
public class PostDTO {

    private String postId;
    private String userId;
    private Date datePosted;
    private String fileType;
    private String filePostedURL;
    private String caption;
    private List<String> categories;
    private List<String> commentIds;
    private List<String> likes;
    private List<String> dislikes;


}
