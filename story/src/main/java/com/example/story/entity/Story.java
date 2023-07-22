package com.example.story.entity;

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
@Document(collection = "story")
public class Story {
    @Id
    private String storyId;
    private String userId;
    private String fileType;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUploaded;
    private String fileUploadedURL;

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Date getDateUploaded() {
        return dateUploaded;
    }

    public void setDateUploaded(Date dateUploaded) {
        this.dateUploaded = dateUploaded;
    }

    public String getFileUploadedURL() {
        return fileUploadedURL;
    }

    public void setFileUploadedURL(String fileUploadedURL) {
        this.fileUploadedURL = fileUploadedURL;
    }


}
