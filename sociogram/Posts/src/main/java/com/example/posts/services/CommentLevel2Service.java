package com.example.posts.services;

import com.example.posts.dto.CommentLevel2DTO;
import com.example.posts.entity.CommentLevel2;

import java.util.List;

public interface CommentLevel2Service {

    Boolean addCommentLevel2(CommentLevel2DTO commentLevel2DTO);

    Boolean deleteCommentLevel2(String commentId, String commentLevel2Id);

    List<CommentLevel2> getCommentsLevel2(String commentId);
}
