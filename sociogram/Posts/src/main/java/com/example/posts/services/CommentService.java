package com.example.posts.services;

import com.example.posts.dto.CommentDTO;
import com.example.posts.entity.Comment;

import java.util.List;

public interface CommentService {

    Boolean addComment(CommentDTO commentDTO);

    Boolean deleteComment(String postId, String commentId, String userId);

    List<Comment> getComments(String postId);
}
