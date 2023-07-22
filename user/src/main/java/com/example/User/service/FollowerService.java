package com.example.User.service;

import com.example.User.dto.FollowerDTO;
import com.example.User.entity.Follower;

import java.util.List;

public interface FollowerService {

    public List<FollowerDTO> getListOfFollowersByUserId(String userId);

}
