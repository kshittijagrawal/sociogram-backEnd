package com.example.User.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FollowerDTO {

    String userId;
    String username;
    String imageURL;
}
