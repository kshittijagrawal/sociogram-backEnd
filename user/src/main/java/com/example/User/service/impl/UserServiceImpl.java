package com.example.User.service.impl;

import com.example.User.dto.AddProfileDTO;
import com.example.User.dto.UserDTO;
import com.example.User.dto.UserProfileDTO;
import com.example.User.entity.User;
import com.example.User.repository.UserCustomRepository;
import com.example.User.repository.UserRepository;
import com.example.User.service.UserService;
import com.example.User.utils.Constants;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserCustomRepository userCustomRepository;


    @Override
    public User findByUserId(String userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public User savePost(User user) {
        return userRepository.save(user);
    }

    @Override
    public Boolean addPostByUserId(String userId, String postId) {
        return userCustomRepository.savePost(userId, postId);
    }

    @Override
    public User addProfileByUserId(AddProfileDTO addProfileDTO) {
        User user = new User();
        if(userRepository.findById(addProfileDTO.getUserId())==null) {
            return null;
        }
        else if(userRepository.findById(addProfileDTO.getUserId()).isPresent())
        {
            user = userRepository.findById(addProfileDTO.getUserId()).get();
            user.setImageURL(addProfileDTO.getImageURL());
            user.setBio(addProfileDTO.getBio());
            return userRepository.save(user);
        }
        return null;

    }

    @Override
    public List<UserDTO> getAllUserList() {
        List<User> userList = new ArrayList<>();
        List<UserDTO> userDTOList = new ArrayList<>();
        userList = userRepository.findAll();
        for(User user: userList) {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(user, userDTO);
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    @Override
    public UserProfileDTO getProfileById(String userId) {
        User user = new User();
        UserDTO userDTO = new UserDTO();
        if(userRepository.findById(userId)==null) {
            return null;
        }
        else if(userRepository.findById(userId).isPresent())
        {
            user = userRepository.findById(userId).get();
            UserProfileDTO userProfileDTO = new UserProfileDTO();
            BeanUtils.copyProperties(user, userProfileDTO);
            return userProfileDTO;
        }
        return null;
    }

    @Override
    public UserDTO getUserByUserId(String userId) {
        User user = new User();
        UserDTO userDTO = new UserDTO();
        if(userRepository.findById(userId)==null) {
            return null;
        }
        else if(userRepository.findById(userId).isPresent()){
            user = userRepository.findById(userId).get();
            BeanUtils.copyProperties(user, userDTO);

            return userDTO;
        }
        return null;

    }

    @Override
    public Boolean deletePostByUserId(String userId, String postId) {

        User user = userRepository.findById(userId).get();
        Boolean isDeleted=false;
        isDeleted = user.getPostIdList().remove(postId);
        if(isDeleted) {
            userRepository.save(user);
        }
        return isDeleted;
    }

    @Override
    public Boolean addStoryByUserId(String userId, String storyId) {
        User user = new User();
        user = userRepository.findById(userId).get();
        if(user.getStoryIdList()==null) {
            List<String> storyIdList = new ArrayList<>();
            storyIdList.add(storyId);
            user.setStoryIdList(storyIdList);
        }
        else {
            user.getStoryIdList().add(storyId);
        }
        userRepository.save(user);
        return true;
    }

    @Override
    public Boolean deleteStoryByUserId(String userId, String storyId) {
        User user = userRepository.findById(userId).get();
        Boolean isDeleted=false;
        isDeleted = user.getStoryIdList().remove(storyId);
        if(isDeleted) {
            userRepository.save(user);
        }
        return isDeleted;
    }

    @Override
    public User insertUser(String userId, String username) {
        User user = new User();
        user.setUserId(userId);
        user.setUsername(username);
        user.setImageURL(Constants.imageProfileDefault);
        return userRepository.save(user);
    }
}

