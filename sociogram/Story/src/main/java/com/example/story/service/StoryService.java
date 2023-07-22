package com.example.story.service;

import com.example.story.dto.StoryDTO;
import com.example.story.entity.Story;

import java.util.List;

public interface StoryService {

    public String addStory(StoryDTO storyDTO);
    public Boolean deleteStory(String storyId);
    public List<Story> findAllStories(List<String> followings);
    public void deleteAllStories(String userId);

    List<StoryDTO> getStoriesById(String userId);
}
