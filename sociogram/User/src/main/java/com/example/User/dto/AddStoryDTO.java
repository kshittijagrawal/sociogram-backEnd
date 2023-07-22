package com.example.User.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class AddStoryDTO {
    String userId;
    String fileType;
    Date dateUploaded;
    String fileUploadedURL;
}
