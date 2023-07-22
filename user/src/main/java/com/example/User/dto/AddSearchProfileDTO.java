package com.example.User.dto;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AddSearchProfileDTO {

    String userId;
    String username;
    String bio;
    String imageURL;
}
