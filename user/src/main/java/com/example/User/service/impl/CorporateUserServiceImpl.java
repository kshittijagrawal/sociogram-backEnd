package com.example.User.service.impl;

import com.example.User.dto.UserProfileDTO;
import com.example.User.entity.CorporateUser;
import com.example.User.repository.CorporateUserRepository;
import com.example.User.service.CorporateUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CorporateUserServiceImpl implements CorporateUserService {

    @Autowired
    CorporateUserRepository corporateUserRepository;


    @Override
    public CorporateUser addProfileByUserId(UserProfileDTO userProfileDTO) {
        CorporateUser corporateUser = new CorporateUser();
        BeanUtils.copyProperties(userProfileDTO, corporateUser);
        return corporateUserRepository.save(corporateUser);
    }

    @Override
    public UserProfileDTO getUserByCorporateId(String corporateId) {
        CorporateUser corporateUser = new CorporateUser();
        corporateUser = corporateUserRepository.findById(corporateId).get();
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        BeanUtils.copyProperties(corporateUser, userProfileDTO);
        return userProfileDTO;
    }

    @Override
    public Boolean addPostByCorporateId(String userId, String postId) {
        CorporateUser corporateUser = new CorporateUser();
        corporateUser = corporateUserRepository.findById(userId).get();
        if(corporateUser.getPostIdList()==null) {
            List<String> postIdList = new ArrayList<>();
            postIdList.add(postId);
            corporateUser.setPostIdList(postIdList);
        }
        else {
            corporateUser.getPostIdList().add(postId);
        }
        corporateUserRepository.save(corporateUser);
        return true;
    }

    @Override
    public Boolean deletePostByCorporateId(String userId, String postId) {
        CorporateUser corporateUser = corporateUserRepository.findById(userId).get();
        Boolean isDeleted=false;
        isDeleted = corporateUser.getPostIdList().remove(postId);
        if(isDeleted) {
            corporateUserRepository.save(corporateUser);
        }
        return isDeleted;
    }

    @Override
    public Boolean addStoryByCorporateId(String userId, String storyId) {
        CorporateUser corporateUser = new CorporateUser();
        corporateUser = corporateUserRepository.findById(userId).get();
        if(corporateUser.getStoryIdList()==null) {
            List<String> storyIdList = new ArrayList<>();
            storyIdList.add(storyId);
            corporateUser.setStoryIdList(storyIdList);
        }
        else {
            corporateUser.getStoryIdList().add(storyId);
        }
        corporateUserRepository.save(corporateUser);
        return true;
    }

    @Override
    public Boolean deleteStoryByCorporateId(String userId, String storyId) {
        CorporateUser corporateUser = corporateUserRepository.findById(userId).get();
        Boolean isDeleted=false;
        isDeleted = corporateUser.getStoryIdList().remove(storyId);
        if(isDeleted) {
            corporateUserRepository.save(corporateUser);
        }
        return isDeleted;
    }
}
