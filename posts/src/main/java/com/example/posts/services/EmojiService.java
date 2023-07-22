package com.example.posts.services;

import com.example.posts.dto.EmojiDTO;
import com.example.posts.entity.Emoji;

public interface EmojiService {

    Boolean addEmoji(String postId, EmojiDTO emojiDTO);

    Boolean deleteEmoji(String postId, String userId);
}
