package com.example.posts.services.impl;

import com.example.posts.dto.CommentLevel2DTO;
import com.example.posts.entity.Comment;
import com.example.posts.entity.CommentLevel2;
import com.example.posts.entity.Post;
import com.example.posts.repository.CommentLevel2Repository;
import com.example.posts.repository.CommentRepository;
import com.example.posts.repository.PostRepository;
import com.example.posts.services.CommentLevel2Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CommentLevel2ServiceImpl implements CommentLevel2Service {

    @Autowired
    PostRepository postRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    CommentLevel2Repository commentLevel2Repository;

    @Override
    public Boolean addCommentLevel2(CommentLevel2DTO commentLevel2DTO) {
        Post post = postRepository.findById(commentLevel2DTO.getPostId()).get();
        CommentLevel2 commentLevel2 = new CommentLevel2();
        BeanUtils.copyProperties(commentLevel2DTO, commentLevel2);
        commentLevel2.setCommentLevel2Id(UUID.randomUUID().toString());
        Comment comment = commentRepository.findById(commentLevel2DTO.getCommentId()).get();
        if (comment.getCommentLevel2Ids() == null) {
            List<String> commentLevel2IdList = new ArrayList<>();
            commentLevel2IdList.add(commentLevel2.getCommentLevel2Id());
            comment.setCommentLevel2Ids(commentLevel2IdList);
        }
        else {
            comment.getCommentLevel2Ids().add(commentLevel2.getCommentLevel2Id());
        }

        postRepository.save(post);
        commentRepository.save(comment);
        commentLevel2Repository.save(commentLevel2);
        return true;
    }

    //    public Boolean addCommentLevel2(CommentLevel2DTO commentLevel2DTO) {
//        Post post = postRepository.findById(commentLevel2DTO.getPostId()).get();
//        CommentLevel2 commentLevel2 = new CommentLevel2();
//        BeanUtils.copyProperties(commentLevel2DTO, commentLevel2);
//        commentLevel2.setCommentLevel2Id(UUID.randomUUID().toString());
//        List<Comment> commentList = new ArrayList<>();
//        commentList = post.getComments();
//        for (Comment comment : commentList) {
//            if (comment.getCommentId().equals(commentLevel2DTO.getCommentId())) {
//                if (comment.getCommentLevel2() == null) {
//                    List<CommentLevel2> commentLevel2List = new ArrayList<>();
//                    commentLevel2List.add(commentLevel2);
//                    comment.setCommentLevel2(commentLevel2List);
//                    break;
//                }
//                else {
//                    comment.getCommentLevel2().add(commentLevel2);
//                    break;
//                }
//
//            }
//        }
//        post.setComments(commentList);
//        postRepository.save(post);
//        return true;
//    }

    @Override
    public Boolean deleteCommentLevel2(String commentId, String commentLevel2Id) {
        Comment comment = commentRepository.findById(commentId).get();
        comment.getCommentLevel2Ids().remove(commentLevel2Id);
        commentRepository.save(comment);
        commentLevel2Repository.deleteById(commentLevel2Id);
        return true;
    }


//    @Override
//    public Boolean deleteCommentLevel2(String postId, String commentId, String commentLevel2Id) {
//        Post post = postRepository.findById(postId).get();
//        List<Comment> commentList = post.getComments();
//        for (Comment comment : commentList) {
//            if (comment.getCommentId().equals(commentId)) {
//                comment.getCommentLevel2().remove(commentLevel2Id);
//                List<CommentLevel2> commentLevel2List = comment.getCommentLevel2();
//                for (CommentLevel2 commentLevel2 : commentLevel2List) {
//                    if (commentLevel2.getCommentId().equals(commentLevel2Id)) {
//                        commentLevel2List.remove(commentLevel2);
//                        postRepository.save(post);
//                        return true;
//                    }
//                postRepository.save(post);
//                return true;
//                }
//        }
//        return null;
//    }


    @Override
    public List<CommentLevel2> getCommentsLevel2(String commentId) {
        List<CommentLevel2> commentLevel2List = commentLevel2Repository.findAll();
        List<CommentLevel2> commentLevel2List1 = new ArrayList<>();
        for (CommentLevel2 commentLevel2 : commentLevel2List) {
            if (commentLevel2.getCommentId().equals(commentId)) {
                commentLevel2List1.add(commentLevel2);
            }
        }
        return commentLevel2List1;
    }
}
