package com.example.User.feignInterface;

import com.example.User.dto.AnalyticsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(url = "http://10.20.5.13:8075", value = "Analytics")
public interface AnalyticsFeignInterface {

    @PostMapping("/kafka/postMsg")
    void sendDataToAnalytics(@RequestBody AnalyticsDTO analyticsDTO);
}
