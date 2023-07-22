package com.example.User.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class StoryDTO {

    String storyId;
    String userId;
    String fileType;
    Date dateUploaded;
    String fileUploadedURL;


}
