package com.example.User.controller;

import com.example.User.dto.FollowerDTO;
import com.example.User.entity.Follower;
import com.example.User.service.FollowerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/follower")
@CrossOrigin(origins="http://localhost:8080")
public class FollowerController {

    @Autowired
    FollowerService followerService;


    @GetMapping("/getListOfFollowers/{userId}")
    public ResponseEntity<?> getListOfFollowersByUserId(@PathVariable("userId") String userId) {
        List<FollowerDTO> followerListDTO=new ArrayList<>();
        followerListDTO = followerService.getListOfFollowersByUserId( userId );
        if(followerListDTO==null) {
            return new ResponseEntity<String>("No user found with this id", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<FollowerDTO>>(followerListDTO,HttpStatus.OK);
    }


}
