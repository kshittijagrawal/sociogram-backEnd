package com.example.story.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class StoryDTO {

    private String userId;
    private String fileType;
    private Date dateUploaded;
    private String fileUploadedURL;
}
