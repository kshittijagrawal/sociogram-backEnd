package com.example.posts.Controller;

import com.example.posts.FeignInterface.Analytics2FeignInterface;
import com.example.posts.FeignInterface.AnalyticsFeignInterface;
import com.example.posts.dto.AnalyticsDTO;
import com.example.posts.dto.AnalyticsPageDTO;
import com.example.posts.dto.PostDTO;
import com.example.posts.entity.Post;
import com.example.posts.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/post")
@CrossOrigin(origins = "http://localhost:8080")
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    AnalyticsFeignInterface analyticsFeignInterface;

    @Autowired
    Analytics2FeignInterface analytics2FeignInterface;

    @PostMapping(value = "/hitPage")
    void callHitPage(String pageId) {
        AnalyticsPageDTO analyticsPageDTO = new AnalyticsPageDTO();
        analyticsPageDTO.setPageId(pageId);
        analyticsPageDTO.setPlatformId("instagram");
        analyticsPageDTO.setTime(LocalDateTime.now());
        analytics2FeignInterface.postPagemsg(analyticsPageDTO);
    }

    @PostMapping(value = "/addPost")
    public ResponseEntity<String> addPost(@RequestBody PostDTO postDTO) {
        return new ResponseEntity<>(postService.addPost(postDTO).getPostId(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deletePost/{postId}")
    public ResponseEntity<Boolean> deletePost(@PathVariable("postId") String postId) {
        return new ResponseEntity<>(postService.deletePost(postId), HttpStatus.OK);
    }

    @PostMapping(value = "/likePost/{postId}/{userId}")
    public ResponseEntity<Boolean> likePost(@PathVariable("postId") String postId, @PathVariable("userId") String userId) {
        AnalyticsDTO analyticsDTO = new AnalyticsDTO();
        analyticsDTO.setPlatformId("instagram");
        analyticsDTO.setActionType("like");
        analyticsDTO.setPostId(postId);
        analyticsDTO.setUserId(userId);
        List<String> categories = new ArrayList<>();
        categories = postService.getCategoriesByPostId(postId);
        analyticsDTO.setCategories(categories);
        analyticsDTO.setActionTime(LocalDateTime.now());
        System.out.println("+++++++"+analyticsDTO);
        List<Boolean> booleanList = new ArrayList<>();
        booleanList = postService.likePost(postId, userId);
        if(booleanList.get(1)==true){
            analyticsFeignInterface.sendDataToAnalytics(analyticsDTO);
        }
        else{
            analyticsDTO.setActionType("dislike");
            analyticsFeignInterface.sendDataToAnalytics(analyticsDTO);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

//    @DeleteMapping(value = "/removeLike/{postId}/{userId}")
//    public ResponseEntity<Boolean> removeLike(@PathVariable("postId") String postId, @PathVariable("userId") String userId) {
//        AnalyticsDTO analyticsDTO = new AnalyticsDTO();
//        analyticsDTO.setPlatformId("instagram");
//        analyticsDTO.setActionType("dislike");
//        analyticsDTO.setPostId(postId);
//        analyticsDTO.setUserId(userId);
//        List<String> categories = new ArrayList<>();
//        categories = postService.getCategoriesByPostId(postId);
//        analyticsDTO.setCategories(categories);
//        System.out.println("+++++++"+analyticsDTO);
//        analyticsFeignInterface.sendDataToAnalytics(analyticsDTO);
//        return new ResponseEntity<>(postService.removeLike(postId, userId), HttpStatus.OK);
//    }

//    @PostMapping(value = "/dislikePost/{postId}/{userId}")
//    public ResponseEntity<Boolean> dislikePost(@PathVariable("postId") String postId, @PathVariable("userId") String userId) {
//        return new ResponseEntity<>(postService.dislikePost(postId, userId), HttpStatus.OK);
//    }
//
//    @DeleteMapping(value = "/removeDislike/{postId}/{userId}")
//    public ResponseEntity<Boolean> removeDislike(@PathVariable("postId") String postId, @PathVariable("userId") String userId) {
//        return new ResponseEntity<>(postService.removeDislike(postId, userId), HttpStatus.OK);
//    }

    @GetMapping(value = "/getAllPostsByUser/{userId}")
    public ResponseEntity<List<Post>> getPostsByUser(@PathVariable("userId") String userId) {
        return new ResponseEntity<>(postService.getPostsByUser(userId), HttpStatus.OK);
    }

//    @GetMapping("/getPost/{postId}")
//    public ResponseEntity<Post> getPost(@RequestParam("postId") String postId) {
//        return new ResponseEntity<>(postService.findByPostId(postId), HttpStatus.OK);
//    }

    @PostMapping(value = "/getAllFollowingPosts")
    public ResponseEntity<List<PostDTO>> getFollowingPosts(@RequestBody List<String> following) {
        return new ResponseEntity<>(postService.getFollowingPosts(following), HttpStatus.OK);
    }
}