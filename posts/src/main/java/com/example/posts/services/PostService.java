package com.example.posts.services;

import com.example.posts.dto.PostDTO;
import com.example.posts.entity.Post;

import java.util.List;

public interface PostService {

    Post addPost(PostDTO postDTO);

    Boolean deletePost(String postId);

    List<Boolean> likePost(String postId, String userId);

    Boolean removeLike(String postId, String userId);

    Boolean dislikePost(String postId, String userId);

    Boolean removeDislike(String postId, String userId);

    List<Post> getPostsByUser(String userId);

    List<PostDTO> getFollowingPosts(List<String> following);

    List<String> getCategoriesByPostId(String postId);

//    Post findByPostId(String postId);
}
