package com.example.User.controller;

import com.example.User.dto.*;
import com.example.User.entity.User;
import com.example.User.feignInterface.CommonInfraFeignInterface;
import com.example.User.feignInterface.PostFeignInterface;
import com.example.User.feignInterface.SearchFeignInterface;
import com.example.User.feignInterface.StoryFeignInterface;
import com.example.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:8084"})
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PostFeignInterface postFeignInterface;

    @Autowired
    StoryFeignInterface storyFeignInterface;

    @Autowired
    SearchFeignInterface searchFeignInterface;

    @Autowired
    CommonInfraFeignInterface commonInfraFeignInterface;



// -----------------------------------------------  CommonInfra APIs ----------------------------------------------------------------------------


//    @PostMapping("/register")
//    public ResponseEntity<String> signUpUser(@RequestBody SignUpDTO signUpDTO) {
//        signUpDTO.setPlatformId("insta");
//        String userId = commonInfraFeignInterface.register(signUpDTO);
//        if(userId.contains("User Exists")) {
//            return new ResponseEntity<String>("Username already exists", HttpStatus.OK);
//        }
//        UserProfileDTO userProfileDTO = new UserProfileDTO();
//        userProfileDTO.setUserId(userId);
//        userProfileDTO.setUsername(signUpDTO.getUsername());
//        userService.addProfileByUserId(userProfileDTO);
//        return new ResponseEntity<String>(userId, HttpStatus.OK);
//    }



// --------------------------------------------------------------------------------------------------------------------------------

// -----------------------------------------------  User APIs ----------------------------------------------------------------------------

    @PostMapping("/insertUser")
    public ResponseEntity<String> insertUser(@RequestParam String userId, @RequestParam String username) {
        User user = userService.insertUser(userId, username);
        if(user==null){
            return new ResponseEntity<String>("Something went wrong", HttpStatus.OK);
        }
        else {
            if(searchFeignInterface.insertSearchUser(userId, username).contentEquals("Success")) {
                return new ResponseEntity<String>("Success", HttpStatus.OK);

            }
        }
        return new ResponseEntity<String>("Something went wrong", HttpStatus.OK);

    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> userDTOList = new ArrayList<>();
        userDTOList = userService.getAllUserList();
        return new ResponseEntity<List<UserDTO>>(userDTOList, HttpStatus.OK);
    }

    @GetMapping("/getUserById")
    public ResponseEntity<?> getuserById(@RequestParam String userId) {
        UserDTO userDTO = new UserDTO();
        userDTO = userService.getUserByUserId(userId);
        if(userDTO==null) {
            return new ResponseEntity<String>("No user found with this id", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/checkPrivate/{userId}")
    public ResponseEntity<?> checkPrivateByUserId(@PathVariable("userId") String userId) {
        if(userService.getUserByUserId(userId)==null) {
            return new ResponseEntity<String>("User not exists", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Boolean>(commonInfraFeignInterface.checkPrivate(userId),HttpStatus.OK);
    }

    @PostMapping("/addProfile")
    public ResponseEntity<Boolean> addProfile(@RequestBody AddProfileDTO addProfileDTO) {
        User user = new User();
        user = userService.addProfileByUserId(addProfileDTO);
        if(user==null) {
            return new ResponseEntity<Boolean>(false, HttpStatus.OK);

        }
        else {
            AddSearchProfileDTO addSearchProfileDTO = new AddSearchProfileDTO();

            UserProfileDTO userProfileDTO = new UserProfileDTO();
            userProfileDTO = userService.getProfileById(addProfileDTO.getUserId());

            addSearchProfileDTO.setUserId(addProfileDTO.getUserId());
            addSearchProfileDTO.setUsername(userProfileDTO.getUsername());
            addSearchProfileDTO.setBio(userProfileDTO.getBio());
            addSearchProfileDTO.setImageURL(userProfileDTO.getImageURL());
            if(searchFeignInterface.addProfile(addSearchProfileDTO).contentEquals("Success")) {
                return new ResponseEntity<Boolean>(true, HttpStatus.OK);

            }
        }

        return new ResponseEntity<Boolean>(false, HttpStatus.OK);

    }

    @PostMapping("/getProfileByUserId")
    public ResponseEntity<?> getProfileByUserId(@RequestParam String userId) {
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO = userService.getProfileById(userId);
        if(userProfileDTO==null){
            return new ResponseEntity<String>("No user found with this id", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<UserProfileDTO>(userProfileDTO, HttpStatus.OK);
    }
// --------------------------------------------------------------------------------------------------------------------------------------------


// -----------------------------------------------  Search APIs ----------------------------------------------------------------------------

    @GetMapping("/search/{username}")
    public ResponseEntity<List<UserProfileDTO>> searchByuserName(@PathVariable("username") String username) {
        List<UserProfileDTO> userProfileDTOList = new ArrayList<>();
        userProfileDTOList = searchFeignInterface.searchUser(username);
        return new ResponseEntity<List<UserProfileDTO>>(userProfileDTOList, HttpStatus.OK);
    }


// ----------------------------------------------------------------------------------------------------------------------------

// -----------------------------------------------  Post APIs ----------------------------------------------------------------------------

    @PostMapping("/addPost")
    public ResponseEntity<Boolean> addPostDTO(@RequestBody PostDTO postDTO) {

        Boolean isSaved = userService.addPostByUserId(postDTO.getUserId(), postFeignInterface.addPost(postDTO));
        return new ResponseEntity<Boolean>(isSaved, HttpStatus.OK);

    }


    @DeleteMapping("/deletePost")
    public ResponseEntity<Boolean> deletePostDTO(@RequestParam("userId") String userId, @RequestParam("postId") String postId) {
        Boolean isPostDeleted = postFeignInterface.deletePost(postId);
        Boolean isDeleted = userService.deletePostByUserId(userId, postId);
        return new ResponseEntity<Boolean>((isDeleted && isPostDeleted), HttpStatus.OK);
    }

// --------------------------------------------------------------------------------------------------------------------------------------------


// -----------------------------------------------  Story APIs ----------------------------------------------------------------------------

    @PostMapping("/addStory")
    public ResponseEntity<Boolean> addStoryDTO(@RequestBody AddStoryDTO addStoryDTO) {
        Boolean isSaved = userService.addStoryByUserId(addStoryDTO.getUserId(), storyFeignInterface.addStory(addStoryDTO));
        return new ResponseEntity<Boolean>(isSaved, HttpStatus.OK);

    }


    @DeleteMapping("/deleteStory")
    public ResponseEntity<Boolean> deleteStoryDTO(@RequestBody DeleteStoryDTO deleteStoryDTO) {
        Boolean isStoryDeleted = storyFeignInterface.deleteStory(deleteStoryDTO.getStoryId());
        Boolean isDeleted = userService.deleteStoryByUserId(deleteStoryDTO.getUserId(), deleteStoryDTO.getStoryId());
        return new ResponseEntity<Boolean>((isDeleted && isStoryDeleted), HttpStatus.OK);
    }



// --------------------------------------------------------------------------------------------------------------------------------------------


}
