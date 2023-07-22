package com.example.User.feignInterface;

import com.example.User.dto.PostDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url = "http://10.20.5.50:8081/post", value = "Post")
public interface PostFeignInterface {

    @PostMapping(value = "/addPost")
    String addPost(@RequestBody PostDTO postDTO);

    @DeleteMapping(value = "/deletePost/{postId}")
    Boolean deletePost(@PathVariable("postId") String postId);

    @PostMapping(value = "/getAllFollowingPosts")
    List<PostDTO> getFollowingPosts(@RequestBody List<String> following);
}
