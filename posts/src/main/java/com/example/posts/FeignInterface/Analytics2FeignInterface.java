package com.example.posts.FeignInterface;

import com.example.posts.dto.AnalyticsDTO;
import com.example.posts.dto.AnalyticsPageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "http://10.20.5.45:8089", value = "Analytics2")
public interface Analytics2FeignInterface {

    @PostMapping("/kafka/save")
    void postPagemsg(@RequestBody AnalyticsPageDTO analyticsPageDTO);
}
