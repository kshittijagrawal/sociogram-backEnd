package com.example.User.service;

import com.example.User.dto.UserProfileDTO;
import com.example.User.entity.CorporateUser;

public interface CorporateUserService {

    public CorporateUser addProfileByUserId(UserProfileDTO userProfileDTO);

    UserProfileDTO getUserByCorporateId(String corporateId);

    Boolean addPostByCorporateId(String userId, String s);

    Boolean deletePostByCorporateId(String userId, String postId);

    Boolean addStoryByCorporateId(String userId, String s);

    Boolean deleteStoryByCorporateId(String userId, String storyId);
}
