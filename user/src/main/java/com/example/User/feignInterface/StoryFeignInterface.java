package com.example.User.feignInterface;

import com.example.User.dto.AddStoryDTO;
import com.example.User.dto.StoryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(url = "http://10.20.5.50:8082/story", value = "Story")
public interface StoryFeignInterface {

    @PostMapping(value = "/addStory")
    String addStory(@RequestBody AddStoryDTO addStoryDTO);

    @DeleteMapping(value = "/deleteStory")
    Boolean deleteStory(@RequestBody String storyId);


    @GetMapping(value = "/getAllStories")
    List<StoryDTO> getAllStories(@RequestBody List<String> followings);


}
