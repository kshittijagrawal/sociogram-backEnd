package com.example.posts.Controller;

import com.example.posts.dto.EmojiDTO;
import com.example.posts.entity.Emoji;
import com.example.posts.services.EmojiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emoji")
@CrossOrigin(origins = "http://localhost:8080")
public class EmojiController {

    @Autowired
    EmojiService emojiService;

    @PostMapping(value = "addEmoji/{postId}")
    public ResponseEntity<Boolean> addEmoji(@PathVariable("postId") String postId, @RequestBody EmojiDTO emojiDTO) {
        return new ResponseEntity<>(emojiService.addEmoji(postId, emojiDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "deleteEmoji/{postId}/{userId}")
    public ResponseEntity<Boolean> deleteEmoji(@PathVariable("postId") String postId, @PathVariable("userId") String userId) {
        return new ResponseEntity<>(emojiService.deleteEmoji(postId, userId), HttpStatus.OK);
    }
}
