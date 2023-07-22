package com.example.story.controller;

import com.example.story.dto.StoryDTO;
import com.example.story.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/story")
@CrossOrigin(origins = {"http://localhost:8081/user", "http://localhost:8080"})
public class StoryController {

    @Autowired
    StoryService storyService;

    @PostMapping("/addStory")
    public ResponseEntity<String> addStory(@RequestBody StoryDTO storyDTO){
        return new ResponseEntity<>(storyService.addStory(storyDTO), HttpStatus.OK);
    }

    @DeleteMapping("/deleteStory")
    public ResponseEntity<Boolean> deleteStory(@RequestBody String storyId){
        return new ResponseEntity<Boolean>(storyService.deleteStory(storyId),HttpStatus.OK);
    }

    @PostMapping("/getAllStories")
    public ResponseEntity<List> getAllStories(@RequestBody List<String> followings){
        return new ResponseEntity<>(storyService.findAllStories(followings), HttpStatus.OK);
    }

    @GetMapping("/getStoriesByUserId/{userId}")
    public ResponseEntity<List<StoryDTO>> getStoriesById(@PathVariable("userId") String userId) {
        List<StoryDTO> storyDTOList = new ArrayList<>();
        storyDTOList = storyService.getStoriesById(userId);
        return new ResponseEntity<List<StoryDTO>>(storyDTOList, HttpStatus.OK);
    }

    @DeleteMapping("/deleteAllStories")
    public ResponseEntity<String> deleteAllStories(@RequestBody String userId){
        storyService.deleteAllStories(userId);
        return new ResponseEntity<>("deleted all stories", HttpStatus.OK);
    }
}
