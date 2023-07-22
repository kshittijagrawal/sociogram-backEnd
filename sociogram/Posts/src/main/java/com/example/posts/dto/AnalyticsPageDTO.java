package com.example.posts.dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class AnalyticsPageDTO {

    String platformId;
    String pageId;
    LocalDateTime time;
}
