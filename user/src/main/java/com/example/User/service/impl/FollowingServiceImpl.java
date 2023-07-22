package com.example.User.service.impl;

import com.example.User.dto.FollowingDTO;
import com.example.User.dto.RequestDTO;
import com.example.User.entity.Follower;
import com.example.User.entity.Following;
import com.example.User.entity.Request;
import com.example.User.entity.User;
import com.example.User.repository.UserCustomRepository;
import com.example.User.repository.UserRepository;
import com.example.User.service.FollowingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FollowingServiceImpl implements FollowingService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserCustomRepository userCustomRepository;

    @Override
    public List<FollowingDTO> getListOfFollowingByUserId(String userId) {
        User user = new User();
        if(userRepository.findById(userId) == null) {
            return null;
        }
        else if(userRepository.findById(userId).isPresent()) {
            user = userRepository.findById(userId).get();
            List<FollowingDTO> followingDTOList = new ArrayList<>();
            List<Following> followingList = new ArrayList<>();
            if(user.getFollowingList()==null) {
                return new ArrayList<FollowingDTO>();
            }
            for(Following following : user.getFollowingList()) {
                FollowingDTO followingDTO = new FollowingDTO();
                BeanUtils.copyProperties(following, followingDTO);
                followingDTOList.add(followingDTO);
            }
            return followingDTOList;
        }
        return null;
    }

    @Override
    public List<String> getFollowingIdList(String userId) {

        User user = new User();
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser==null) {
            return null;
        }
        else if(optionalUser.isPresent()){
            user = optionalUser.get();
            List<String> followingIDList = new ArrayList<>();
            if(user.getFollowingList()==null){
                return new ArrayList<String>();
            }
            else {
                for (Following following : user.getFollowingList()) {
                    followingIDList.add(following.getUserId());
                }
                return followingIDList;
            }
        }
        return null;
    }

    @Override
    public Boolean addRequest(Boolean isPrivate, String userId, Request request) {
        User user = new User();
        User userRequested = new User();
        user = userRepository.findById(userId).get();
        userRequested = userRepository.findById(request.getUserId()).get();

        if (isPrivate) {
            if (user.getRequestList() == null) {
                List<Request> requestList = new ArrayList<>();
                requestList.add(request);
                user.setRequestList(requestList);
            } else {
                Request request1 = userCustomRepository.checkRequestExists(user.getUserId(), request.getUserId());
                if(request1==null){
                    user.getRequestList().add(request);
                }
            }

        } else {
            Follower follower = new Follower();
            follower.setUserId(request.getUserId());
            follower.setUsername(request.getUsername());
            follower.setImageURL(request.getImageURL());

            Following following = new Following();
            following.setUserId(user.getUserId());
            following.setUsername(user.getUsername());
            following.setImageURL(user.getImageURL());

            if (user.getFollowerList() == null) {
                List<Follower> followerList = new ArrayList<>();
                followerList.add(follower);
                user.setFollowerList(followerList);
            } else {
                user.getFollowerList().add(follower);
            }

            if (userRequested.getFollowingList() == null) {
                List<Following> followingList = new ArrayList<>();
                followingList.add(following);
                userRequested.setFollowingList(followingList);
            } else {
                userRequested.getFollowingList().add(following);
            }

        }
        userRepository.save(user);
        userRepository.save(userRequested);
        return true;
    }

    @Override
    public Boolean acceptRequest(Boolean isPrivate, String userId, Request request) {
        User user = new User();
        User userRequested = new User();

        user = userRepository.findById(userId).get();
        userRequested = userRepository.findById(request.getUserId()).get();

        if(isPrivate) {
            if(user.getRequestList()==null) {
                return false;
            }
            else if(user.getRequestList().contains(request)) {
                if(user.getRequestList().remove(request)) {
                    Follower follower = new Follower();
                    follower.setUserId(userRequested.getUserId());
                    follower.setUsername(userRequested.getUsername());
                    follower.setImageURL(userRequested.getImageURL());

                    Following following = new Following();
                    following.setUserId(user.getUserId());
                    following.setUsername(user.getUsername());
                    following.setImageURL(user.getImageURL());


                    if (user.getFollowerList() == null) {
                        List<Follower> followerList = new ArrayList<>();
                        followerList.add(follower);
                        user.setFollowerList(followerList);
                    } else {
                        Follower follower1 = userCustomRepository.checkFollowerExists(user.getUserId(), follower.getUserId());
                        if(follower1==null){
                            user.getFollowerList().add(follower);
                        }
                    }

                    if (userRequested.getFollowingList() == null) {
                        List<Following> followingList = new ArrayList<>();
                        followingList.add(following);
                        userRequested.setFollowingList(followingList);
                    } else {
                        Following following1 = userCustomRepository.checkFollowingExists(userRequested.getUserId(), following.getUserId());
                        if(following1==null){
                            userRequested.getFollowingList().add(following);
                        }
                    }

                    userRepository.save(user);
                    userRepository.save(userRequested);

                    return true;
                }
            }
            return false;


        }

        else {
            Follower follower = new Follower();
            follower.setUserId(user.getUserId());
            follower.setUsername(user.getUsername());
            follower.setImageURL(user.getImageURL());

            Following following = new Following();
            following.setUserId(request.getUserId());
            following.setUsername(request.getUsername());
            following.setImageURL(request.getImageURL());


            if (userRequested.getFollowerList() == null) {
                List<Follower> followerList = new ArrayList<>();
                followerList.add(follower);
                userRequested.setFollowerList(followerList);
            } else {
                Follower follower1=userCustomRepository.checkFollowerExists(userRequested.getUserId(), follower.getUserId());
                if(follower1==null){
                    userRequested.getFollowerList().add(follower);
                }
            }

            if (user.getFollowingList() == null) {
                List<Following> followingList = new ArrayList<>();
                followingList.add(following);
                user.setFollowingList(followingList);
            } else {
                Following following1 = userCustomRepository.checkFollowingExists(user.getUserId(), following.getUserId());
                if (following1==null){
                    user.getFollowingList().add(following);

                }
            }

            userRepository.save(user);
            userRepository.save(userRequested);

            return true;
        }

    }

    @Override
    public Boolean unFollowUser(String userId1, String userId2) {
//
//        User user = new User();
//        User userUnFollow = new User();
//        if(userRepository.findById(userId1) == null || userRepository.findById(userId2) == null) {
//            return false;
//        }
//        else if(userRepository.findById(userId1).isPresent() && userRepository.findById(userId2).isPresent()) {
//            user = userRepository.findById(userId1).get();
//            userUnFollow = userRepository.findById(userId2).get();
//            if(user.getFollowingList()==null || userUnFollow.getFollowerList()==null){
//                return false;
//            }
//            else{
//                for(Following following: user.getFollowingList()){
//                    if(userId2==following.getUserId()){
//                        user.getFollowingList().remove(following);
//                        userRepository.save(user);
//                        break;
//                    }
//                }
//                for(Follower follower: userUnFollow.getFollowerList()){
//                    if(userId1==follower.getUserId()){
//                        userUnFollow.getFollowerList().remove(follower);
//                        userRepository.save(userUnFollow);
//                        break;
//                    }
//                }
//                return true;
//            }
//        }
//        return false;

        return userCustomRepository.unFollow(userId1, userId2);
    }

    @Override
    public List<RequestDTO> getRequestListById(String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = new User();
        List<RequestDTO> requestDTOList = new ArrayList<>();
        if(optionalUser==null){
            return null;
        }
        else if(optionalUser.isPresent()){
            user = optionalUser.get();
            if(user.getRequestList()==null){
                return new ArrayList<RequestDTO>();
            }
            else{
                for(Request request: user.getRequestList()){
                    RequestDTO requestDTO = new RequestDTO();
                    BeanUtils.copyProperties(request, requestDTO);
                    if(!requestDTOList.contains(requestDTO)){
                        requestDTOList.add(requestDTO);

                    }
                }
                return requestDTOList;

            }
        }
        return null;
    }
}