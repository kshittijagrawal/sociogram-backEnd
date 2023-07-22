package com.example.User.controller;

import com.example.User.dto.*;
import com.example.User.entity.Following;
import com.example.User.entity.Request;
import com.example.User.feignInterface.AnalyticsFeignInterface;
import com.example.User.feignInterface.CommonInfraFeignInterface;
import com.example.User.feignInterface.PostFeignInterface;
import com.example.User.feignInterface.StoryFeignInterface;
import com.example.User.repository.UserCustomRepository;
import com.example.User.service.FollowingService;
import com.example.User.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/following")
@CrossOrigin(origins = "http://localhost:8080")
public class FollowingController {

    @Autowired
    FollowingService followingService;

    @Autowired
    StoryFeignInterface storyFeignInterface;

    @Autowired
    PostFeignInterface postFeignInterface;

    @Autowired
    CommonInfraFeignInterface commonInfraFeignInterface;

    @Autowired
    UserService userService;

    @Autowired
    AnalyticsFeignInterface analyticsFeignInterface;



    @GetMapping("/getListOfFollowing/{userId}")
    public ResponseEntity<?> getListOfFollowingByUserId(@PathVariable("userId") String userId) {
        List<FollowingDTO> followingListDTO=new ArrayList<>();
        followingListDTO = followingService.getListOfFollowingByUserId( userId );
        if(followingListDTO==null) {
            return new ResponseEntity<String>("No user found with this id", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<FollowingDTO>>(followingListDTO,HttpStatus.OK);
    }


    @GetMapping("/getAllStoriesOfFollowing/{userId}")
    public ResponseEntity<?> getAllStoriesOfFollowingsByUserId(@PathVariable("userId") String userId) {
        List<StoryDTO> storyDTOList = new ArrayList<>();
        List<String> followingIdList = new ArrayList<>();
        followingIdList = followingService.getFollowingIdList(userId);
        if(followingIdList==null) {
            return new ResponseEntity<String>("User not exists", HttpStatus.BAD_REQUEST);
        }
        else {
            storyDTOList = storyFeignInterface.getAllStories(followingIdList);
            return new ResponseEntity<List<StoryDTO>>(storyDTOList, HttpStatus.OK);
        }

    }


    @GetMapping("/getAllPostsOfFollowing")
    public ResponseEntity<List<PostDTO>> getAllPostOfFollowingByUserId(@RequestParam String userId) {
        List<PostDTO> postDTOList = new ArrayList<>();
        List<String> followingIdList = new ArrayList<>();
        followingIdList = followingService.getFollowingIdList(userId);
        postDTOList = postFeignInterface.getFollowingPosts(followingIdList);
        return new ResponseEntity<List<PostDTO>>(postDTOList, HttpStatus.OK);

    }

    @PostMapping("/addRequest")
    public ResponseEntity<Boolean> addRequest(@RequestParam("userId") String userId, @RequestParam("isPrivate") Boolean isPrivate, @RequestBody Request request) {
        if(userService.getUserByUserId(userId)==null){
            return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
        }
        else{
            //commonInfraFeignInterface.checkPrivate(userService.getUserByUserId(userId).getUsername());
            AnalyticsDTO analyticsDTO = new AnalyticsDTO();
            analyticsDTO.setActionTime(LocalDateTime.now());
            analyticsDTO.setActionType("follow");
            analyticsDTO.setCategories(new ArrayList<String>());
            analyticsDTO.setPlatformId("instagram");
            analyticsDTO.setPostId("");
            analyticsDTO.setUserId(request.getUserId());
            analyticsFeignInterface.sendDataToAnalytics(analyticsDTO);
             Boolean isRequestAdded = followingService.addRequest(isPrivate, userId, request);
            return new ResponseEntity<Boolean>(isRequestAdded, HttpStatus.OK);
        }

    }

    @PostMapping("/acceptRequest")
    public ResponseEntity<Boolean> acceptRequest(@RequestParam("userId") String userId, @RequestParam("isPrivate") Boolean isPrivate,@RequestBody Request request) {
        if(userService.getUserByUserId(userId)==null){
            return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
        }
        else{
            ;//commonInfraFeignInterface.checkPrivate(userService.getUserByUserId(userId).getUsername());
            Boolean isRequestAccepted = followingService.acceptRequest(isPrivate, userId, request);
            if(isRequestAccepted==true){
                AnalyticsDTO analyticsDTO = new AnalyticsDTO();
                analyticsDTO.setActionTime(LocalDateTime.now());
                analyticsDTO.setActionType("follow");
                analyticsDTO.setCategories(new ArrayList<String>());
                analyticsDTO.setPlatformId("instagram");
                analyticsDTO.setPostId("");
                analyticsDTO.setUserId(userId);
                analyticsFeignInterface.sendDataToAnalytics(analyticsDTO);
                Boolean isRequestAdded = followingService.addRequest(isPrivate, userId, request);
            }
            return new ResponseEntity<Boolean>(isRequestAccepted, HttpStatus.OK);
        }


    }

    @GetMapping("/getRequestList/{userId}")
    public ResponseEntity<?> getRequestList(@PathVariable("userId") String userId) {
        List<RequestDTO> requestDTOList = new ArrayList<>();
        requestDTOList = followingService.getRequestListById(userId);
        if(requestDTOList==null)  {
            return new ResponseEntity<String>("User not exists", HttpStatus.BAD_REQUEST);
        }
        else{

            return new ResponseEntity<List<RequestDTO>>(requestDTOList, HttpStatus.OK);
        }
    }


    @PostMapping("/unFollow")
    public ResponseEntity<Boolean> unFollowUser(@RequestParam("userId1") String userId1, @RequestParam("userId2") String userId2) {
        Boolean isUnfollowed;
        isUnfollowed = followingService.unFollowUser(userId1, userId2);
        return new ResponseEntity<Boolean>(isUnfollowed, HttpStatus.OK);

    }
}
