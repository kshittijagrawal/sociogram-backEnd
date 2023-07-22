package com.example.User.feignInterface;

import com.example.User.dto.SignUpDTO;
import feign.Headers;
import io.swagger.v3.oas.annotations.headers.Header;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(url = "http://10.20.5.20:8080/api", value = "CommonInfra")
public interface CommonInfraFeignInterface {

    @PostMapping("/auth/register")
    String register(@RequestBody SignUpDTO signUpDTO);

    @GetMapping("/users/checkPrivate/{username}")
    Boolean checkPrivate(@PathVariable("username") String username);
}
