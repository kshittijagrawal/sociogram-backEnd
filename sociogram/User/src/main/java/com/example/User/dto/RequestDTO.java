package com.example.User.dto;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;


@Data
@ToString
public class RequestDTO {


    String userId;
    String username;
    String imageURL;
}
