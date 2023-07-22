package com.example.User.service;

import com.example.User.dto.AddProfileDTO;
import com.example.User.dto.RequestDTO;
import com.example.User.dto.UserDTO;
import com.example.User.dto.UserProfileDTO;
import com.example.User.entity.User;

import java.util.List;

public interface UserService {



    User findByUserId(String userId);


    User savePost(User user);

    Boolean addPostByUserId(String userId, String postId);

    User addProfileByUserId(AddProfileDTO addProfileDTO);

    List<UserDTO> getAllUserList();

    UserProfileDTO getProfileById(String userId);

    UserDTO getUserByUserId(String userId);

    Boolean deletePostByUserId(String userId, String postId);

    Boolean addStoryByUserId(String userId, String s);

    Boolean deleteStoryByUserId(String userId, String storyId);

    User insertUser(String userId, String username);

}
