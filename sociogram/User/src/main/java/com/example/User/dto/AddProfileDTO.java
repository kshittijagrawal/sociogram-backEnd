package com.example.User.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AddProfileDTO {

    String userId;
    String bio;
    String imageURL;
}
