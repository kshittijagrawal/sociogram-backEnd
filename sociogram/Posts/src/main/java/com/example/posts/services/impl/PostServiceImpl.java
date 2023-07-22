package com.example.posts.services.impl;

import com.example.posts.dto.PostDTO;
import com.example.posts.entity.Post;
import com.example.posts.repository.PostRepository;
import com.example.posts.services.PostService;
import javafx.geometry.Pos;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    public Post addPost(PostDTO postDTO) {
        Post post = new Post();
        BeanUtils.copyProperties(postDTO, post);
        return postRepository.save(post);
    }

    @Override
    public Boolean deletePost(String postId) {
        postRepository.deleteById(postId);
        return true;
    }

    @Override
    public List<Boolean> likePost(String postId, String userId) {
        List<Boolean> booleanList = new ArrayList<>();
        booleanList.add(true);
        Post post = postRepository.findById(postId).get();
        if (post.getLikes() == null) {
            List<String> likesList = new ArrayList<>();
            likesList.add(userId);
            post.setLikes(likesList);
            booleanList.add(true);
        }
        else if (!(post.getLikes().contains(userId))) {
            post.getLikes().add(userId);
            booleanList.add(true);
        }
        else {
            post.getLikes().remove(userId);
            booleanList.add(false);
        }
        postRepository.save(post);
        return booleanList;
    }

    @Override
    public Boolean removeLike(String postId, String userId) {
        Post post = postRepository.findById(postId).get();
        post.getLikes().remove(userId);
        postRepository.save(post);
        return true;
    }

    @Override
    public Boolean dislikePost(String postId, String userId) {
        Post post = postRepository.findById(postId).get();
        if (post.getDislikes() == null) {
            List<String> dislikesList = new ArrayList<>();
            dislikesList.add(userId);
            post.setDislikes(dislikesList);
        }
        else {
            post.getDislikes().add(userId);
        }
        postRepository.save(post);
        return true;
    }

    @Override
    public Boolean removeDislike(String postId, String userId) {
        Post post = postRepository.findById(postId).get();
        post.getDislikes().remove(userId);
        postRepository.save(post);
        return true;
    }

    @Override
    public List<Post> getPostsByUser(String userId) {
        List<Post> postList = postRepository.findAll();
        List<Post> postList1 = new ArrayList<>();
        for (Post post : postList) {
            if (post.getUserId().equals(userId))
                postList1.add(post);
        }
        Collections.reverse(postList1);
        return postList1;
    }

    @Override
    public List<PostDTO> getFollowingPosts(List<String> following) {
        List<Post> postList = postRepository.findAll();
        List<PostDTO> postDTOList = new ArrayList<>();
        for (Post post : postList) {
            if (following.contains(post.getUserId())) {
                PostDTO postDTO = new PostDTO();
                BeanUtils.copyProperties(post, postDTO);
                postDTOList.add(postDTO);
            }
        }
        return postDTOList;
    }

    @Override
    public List<String> getCategoriesByPostId(String postId) {
        Post post = new Post();
        if(postRepository.findById(postId)==null){
            return new ArrayList<String>();
        }
        else if(postRepository.findById(postId).isPresent()){
            post = postRepository.findById(postId).get();
            if(post.getCategories()==null) {
                return new ArrayList<String>();
            }
            else {
                return post.getCategories();
            }
        }

        return new ArrayList<String>();
    }


}
