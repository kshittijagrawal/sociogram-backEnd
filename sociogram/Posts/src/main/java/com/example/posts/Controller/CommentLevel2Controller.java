package com.example.posts.Controller;

import com.example.posts.dto.CommentLevel2DTO;
import com.example.posts.entity.CommentLevel2;
import com.example.posts.services.CommentLevel2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commentLevel2")
@CrossOrigin(origins = "http://localhost:8080")
public class CommentLevel2Controller {

    @Autowired
    CommentLevel2Service commentLevel2Service;

    @PostMapping(value = "/addCommentLevel2")
    public ResponseEntity<Boolean> addCommentLevel2(@RequestBody CommentLevel2DTO commentLevel2DTO) {
        return new ResponseEntity<>(commentLevel2Service.addCommentLevel2(commentLevel2DTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteCommentLevel2/{commentId}/{commentLevel2Id}")
    public ResponseEntity<Boolean> deleteCommentLevel2(@PathVariable("commentId") String commentId,
                                                       @PathVariable("commentLevel2Id") String commentLevel2Id) {
        return new ResponseEntity<>(commentLevel2Service.deleteCommentLevel2(commentId, commentLevel2Id), HttpStatus.OK);
    }

    @GetMapping(value = "/getCommentsLevel2/{commentId}")
    public ResponseEntity<List<CommentLevel2>> getCommentsLevel2(@PathVariable("commentId") String commentId) {
        return new ResponseEntity<>(commentLevel2Service.getCommentsLevel2(commentId), HttpStatus.OK);
    }
}
