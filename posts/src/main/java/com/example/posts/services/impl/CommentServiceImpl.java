package com.example.posts.services.impl;

import com.example.posts.dto.CommentDTO;
import com.example.posts.entity.Comment;
import com.example.posts.entity.Post;
import com.example.posts.repository.CommentLevel2Repository;
import com.example.posts.repository.CommentRepository;
import com.example.posts.repository.PostRepository;
import com.example.posts.services.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CommentLevel2Repository commentLevel2Repository;

    public Boolean addComment(CommentDTO commentDTO) {
        Post post = postRepository.findById(commentDTO.getPostId()).get();
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDTO, comment);
        comment.setCommentId(UUID.randomUUID().toString());
        if (post.getCommentIds() == null) {
            List<String> commentIdList = new ArrayList<>();
            commentIdList.add(comment.getCommentId());
            post.setCommentIds(commentIdList);
        }
        else {
            post.getCommentIds().add(comment.getCommentId());
        }
//        if(post.getComments()==null) {
//            List<Comment> commentList = new ArrayList<>();
//            commentList.add(comment);
//            post.setComments(commentList);
//        }
//        else
//        {
//            post.getComments().add(comment);
//        }
        postRepository.save(post);
        commentRepository.save(comment);
        return true;
    }

    @Override
    public Boolean deleteComment(String postId, String commentId, String userId) {
        Post post = postRepository.findById(postId).get();
        if (post.getUserId().equals(userId)) {
            post.getCommentIds().remove(commentId);
            postRepository.save(post);
            commentRepository.deleteById(commentId);
            commentLevel2Repository.deleteByCommentId(commentId);
            return true;
        }
        else {
            Comment comment = commentRepository.findById(commentId).get();
            if (comment.getUserId().equals(userId)) {
                post.getCommentIds().remove(commentId);
                postRepository.save(post);
                commentRepository.deleteById(commentId);
                commentLevel2Repository.deleteByCommentId(commentId);
                return true;
            }
            return false;
        }
    }

    @Override
    public List<Comment> getComments(String postId) {
        List<Comment> commentList = commentRepository.findAll();
        List<Comment> commentList1 = new ArrayList<>();
        for (Comment comment : commentList) {
            if (comment.getPostId().equals(postId)) {
                commentList1.add(comment);
            }
        }
        return  commentList1;
    }
}
