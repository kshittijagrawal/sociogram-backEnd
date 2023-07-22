package com.example.User.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class SignUpDTO {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String dob;
    private String platformId;
    private Boolean isPrivate;
    private List<String> categories;
}
