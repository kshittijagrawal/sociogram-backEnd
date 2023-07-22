package com.example.User.controller;

import com.example.User.dto.*;
import com.example.User.entity.CorporateUser;
import com.example.User.feignInterface.PostFeignInterface;
import com.example.User.feignInterface.StoryFeignInterface;
import com.example.User.service.CorporateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/corporateUser")
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:8083"})
public class CorporateUserController {

    @Autowired
    CorporateUserService corporateUserService;

    @Autowired
    PostFeignInterface postCorporateFeignInterface;

    @Autowired
    StoryFeignInterface storyCorporateFeignInterface;

// -----------------------------------------------  CorporateUser APIs ----------------------------------------------------------------------------

    @PostMapping("/addCorporateProfile")
    public ResponseEntity<Boolean> addProfile(UserProfileDTO userProfileDTO) {
        CorporateUser corporateUser = new CorporateUser();
        corporateUser = corporateUserService.addProfileByUserId(userProfileDTO);
        if(corporateUser.getUserId()==null) {
            return new ResponseEntity<Boolean>(false, HttpStatus.OK);
        }
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }


    @PostMapping("/getProfileByCorporateId")
    public ResponseEntity<UserProfileDTO> getProfileByCorporateId(@RequestParam String corporateId) {
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO = corporateUserService.getUserByCorporateId(corporateId);
        return new ResponseEntity<UserProfileDTO>(userProfileDTO, HttpStatus.OK);
    }


// --------------------------------------------------------------------------------------------------------------------------------------------


// -----------------------------------------------  Post APIs ----------------------------------------------------------------------------

    @PostMapping("/addCorporatePost")
    public ResponseEntity<Boolean> addPostDTO(@RequestBody PostDTO postDTO) {

        Boolean isSaved = corporateUserService.addPostByCorporateId(postDTO.getUserId(), postCorporateFeignInterface.addPost(postDTO));
        return new ResponseEntity<Boolean>(isSaved, HttpStatus.OK);

    }

    @DeleteMapping("/deleteCorporatePost")
    public ResponseEntity<Boolean> deletePostDTO(@RequestBody DeletePostDTO deletePostDTO) {
        Boolean isPostDeleted = postCorporateFeignInterface.deletePost(deletePostDTO.getPostId());
        Boolean isDeleted = corporateUserService.deletePostByCorporateId(deletePostDTO.getUserId(), deletePostDTO.getPostId());
        return new ResponseEntity<Boolean>((isDeleted && isPostDeleted), HttpStatus.OK);
    }



// --------------------------------------------------------------------------------------------------------------------------------------------


// -----------------------------------------------  Story APIs ----------------------------------------------------------------------------

    @PostMapping("/addCorporateStory")
    public ResponseEntity<Boolean> addStoryDTO(@RequestBody AddStoryDTO addStoryDTO) {
        Boolean isSaved = corporateUserService.addStoryByCorporateId(addStoryDTO.getUserId(), storyCorporateFeignInterface.addStory(addStoryDTO));
        return new ResponseEntity<Boolean>(isSaved, HttpStatus.OK);

    }


    @DeleteMapping("/deleteCorporateStory")
    public ResponseEntity<Boolean> deleteStoryDTO(@RequestBody DeleteStoryDTO deleteStoryDTO) {
        Boolean isStoryDeleted = storyCorporateFeignInterface.deleteStory(deleteStoryDTO.getStoryId());
        Boolean isDeleted = corporateUserService.deleteStoryByCorporateId(deleteStoryDTO.getUserId(), deleteStoryDTO.getStoryId());
        return new ResponseEntity<Boolean>((isDeleted && isStoryDeleted), HttpStatus.OK);
    }



// --------------------------------------------------------------------------------------------------------------------------------------------
}
