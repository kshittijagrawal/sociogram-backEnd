package com.example.User.service.impl;

import com.example.User.dto.FollowerDTO;
import com.example.User.entity.Follower;
import com.example.User.entity.User;
import com.example.User.repository.UserRepository;
import com.example.User.service.FollowerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FollowerServiceImpl implements FollowerService {

    @Autowired
    UserRepository userRepository;


    @Override
    public List<FollowerDTO> getListOfFollowersByUserId(String userId) {
        User user = new User();
        if(userRepository.findById(userId) == null) {
            return null;
        }
        else if(userRepository.findById(userId).isPresent()) {
            user = userRepository.findById(userId).get();
            List<FollowerDTO> followerDTOList = new ArrayList<>();
            List<Follower> followerList = new ArrayList<>();
            if(user.getFollowerList()==null) {
                return new ArrayList<FollowerDTO>();
            }
            for(Follower follower : user.getFollowerList()) {
                FollowerDTO followerDTO = new FollowerDTO();
                BeanUtils.copyProperties(follower, followerDTO);
                followerDTOList.add(followerDTO);
            }
            return followerDTOList;
        }
        return null;
    }
}
