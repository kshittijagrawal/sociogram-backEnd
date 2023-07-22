package com.example.story.service.impl;

import com.example.story.dto.StoryDTO;
import com.example.story.entity.Story;
import com.example.story.repository.StoryRepository;
import com.example.story.service.StoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
public class StoryServiceImpl implements StoryService {
    @Autowired
    StoryRepository storyRepository;

    @Override
    public String addStory(StoryDTO storyDTO) {
        Story story = new Story();
        BeanUtils.copyProperties(storyDTO, story);

        Story storyCreated;
        story.setDateUploaded(new Date());
        storyCreated = storyRepository.save(story);
        return storyCreated.getStoryId();
    }

    @Override
    public Boolean deleteStory(String storyId) {
        if (storyRepository.findById(storyId).get() != null) {
            storyRepository.deleteById(storyId);
            return true;
        }
        return false;
    }

    @Override
    public List<Story> findAllStories(List<String> followings) {
        List<Story> result = new ArrayList<>();

        HashSet<String> followingsSet = new HashSet<>();
        followingsSet.addAll(followings);

        for (Story storyField : storyRepository.findAll()) {
            if (followingsSet.contains(storyField.getUserId())) {
                if (storyField.getDateUploaded() != null) {
                    long diffInMs = (new Date()).getTime() - storyField.getDateUploaded().getTime();
                    long diffInMinutes = diffInMs / (60 * 1000);

                    if (diffInMinutes <= 10) {
                        result.add(storyField);
                    }
                }

            }
        }

        return result;
    }

    @Override
    public void deleteAllStories(String userId) {
        for (Story storyField : storyRepository.findAll()) {
            if (storyField.getUserId().equals(userId))
                storyRepository.deleteById(storyField.getStoryId());
        }
    }

    @Override
    public List<StoryDTO> getStoriesById(String userId) {
        List<Story> storyList = new ArrayList<>();
        storyList = storyRepository.findAll();
        List<Story> storyList1 = new ArrayList<>();
        for (Story story : storyList) {
            if (story.getUserId().equals(userId)) {
                if (story.getDateUploaded() != null) {
                    long diffInMs = (new Date()).getTime() - story.getDateUploaded().getTime();
                    long diffInMinutes = diffInMs / (60 * 1000);

                    if (diffInMinutes <= 10) {
                        storyList1.add(story);
                    }
                }
            }
        }
            List<StoryDTO> storyDTOList = new ArrayList<>();
            for (Story story1 : storyList1) {
                StoryDTO storyDTO = new StoryDTO();
                BeanUtils.copyProperties(story1, storyDTO);
                storyDTOList.add(storyDTO);
            }
            return storyDTOList;
        }

}

