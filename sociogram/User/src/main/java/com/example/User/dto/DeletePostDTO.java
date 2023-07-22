package com.example.User.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DeletePostDTO {
    String userId;
    String postId;
}
