package com.example.posts.Controller;

import com.example.posts.FeignInterface.AnalyticsFeignInterface;
import com.example.posts.dto.AnalyticsDTO;
import com.example.posts.dto.CommentDTO;
import com.example.posts.entity.Comment;
import com.example.posts.services.CommentService;
import com.example.posts.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comment")
@CrossOrigin(origins = "http://localhost:8080")
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    PostService postService;

    @Autowired
    AnalyticsFeignInterface analyticsFeignInterface;

    @PostMapping(value = "/addComment")
    public ResponseEntity<Boolean> addComment(@RequestBody CommentDTO commentDTO) {
        AnalyticsDTO analyticsDTO = new AnalyticsDTO();
        analyticsDTO.setPlatformId("instagram");
        analyticsDTO.setActionType("comment");
        analyticsDTO.setPostId(commentDTO.getPostId());
        analyticsDTO.setUserId(commentDTO.getUserId());
        List<String> categories = new ArrayList<>();
        categories = postService.getCategoriesByPostId(commentDTO.getPostId());
        analyticsDTO.setCategories(categories);

        analyticsFeignInterface.sendDataToAnalytics(analyticsDTO);
        return new ResponseEntity<>(commentService.addComment(commentDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteComment/{postId}/{commentId}/{userId}")
    public ResponseEntity<Boolean> deleteComment(@PathVariable("postId") String postId,
                                                 @PathVariable("commentId") String commentId,
                                                 @PathVariable("userId") String userId) {
        return new ResponseEntity<>(commentService.deleteComment(postId, commentId, userId), HttpStatus.OK);
    }

    @GetMapping(value = "/getComments/{postId}")
    public ResponseEntity<List<Comment>> getComments(@PathVariable("postId") String postId) {
        return new ResponseEntity<>(commentService.getComments(postId), HttpStatus.OK);
    }
}
