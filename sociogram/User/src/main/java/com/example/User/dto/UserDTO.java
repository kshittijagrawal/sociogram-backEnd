package com.example.User.dto;

import com.example.User.entity.Follower;
import com.example.User.entity.Following;
import com.example.User.entity.Request;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    String userId;
    String username;
    String bio;
    String imageURL;
    List<String> postIdList;
    List<String> storyIdList;
    List<Follower> followerList;
    List<Following> followingList;
    List<Request> requestList;
}
