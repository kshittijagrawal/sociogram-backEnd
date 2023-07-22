package com.example.User.feignInterface;

import com.example.User.dto.AddProfileDTO;
import com.example.User.dto.AddSearchProfileDTO;
import com.example.User.dto.UserProfileDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url = "http://10.20.5.49:8085/search", value = "Search")
public interface SearchFeignInterface {

    @PostMapping("/addProfile")
    String addProfile(@RequestBody AddSearchProfileDTO addSearchProfileDTO);

    @GetMapping("/query/{userName}")
    List<UserProfileDTO> searchUser(@PathVariable("userName") String userName);

    @PostMapping("/insertSearchUser")
    String insertSearchUser(@RequestParam("userId") String userId, @RequestParam("username") String username);
}
