package com.example.User.service;

import com.example.User.dto.FollowingDTO;
import com.example.User.dto.RequestDTO;
import com.example.User.entity.Following;
import com.example.User.entity.Request;

import java.util.List;

public interface FollowingService {

    public List<FollowingDTO> getListOfFollowingByUserId(String userId);

    List<String> getFollowingIdList(String userId);

    Boolean addRequest(Boolean isPrivate, String userId, Request request);

    Boolean acceptRequest(Boolean isPrivate, String userId, Request request);

    Boolean unFollowUser(String userId1, String userId2);

    List<RequestDTO> getRequestListById(String userId);
}
