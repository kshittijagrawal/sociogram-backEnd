package com.example.posts.dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString
public class AnalyticsDTO {
    String platformId;
    String postId;
    String userId;
    String actionType;
    LocalDateTime actionTime;
    List<String> categories;
}
