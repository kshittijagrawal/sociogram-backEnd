package com.example.posts.services.impl;

import com.example.posts.dto.EmojiDTO;
import com.example.posts.entity.Emoji;
import com.example.posts.entity.Post;
import com.example.posts.repository.PostRepository;
import com.example.posts.services.EmojiService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmojiServiceImpl implements EmojiService {

    @Autowired
    PostRepository postRepository;

    @Override
    public Boolean addEmoji(String postId, EmojiDTO emojiDTO) {
        Post post = postRepository.findById(postId).get();
        Emoji emoji = new Emoji();
        BeanUtils.copyProperties(emojiDTO, emoji);
        if (post.getEmojis() == null) {
            List<Emoji> emojiList = new ArrayList<>();
            emojiList.add(emoji);
            post.setEmojis(emojiList);
        }
        else {
            post.getEmojis().add(emoji);
        }
        postRepository.save(post);
        return true;
    }

    @Override
    public Boolean deleteEmoji(String postId, String userId) {
        Post post = postRepository.findById(postId).get();
        List<Emoji> emojiList = post.getEmojis();
        for (Emoji emoji : emojiList) {
            if (emoji.getUserId().equals(userId)) {
                emojiList.remove(emoji);
                break;
            }
        }
        postRepository.save(post);
        return true;
    }
}
