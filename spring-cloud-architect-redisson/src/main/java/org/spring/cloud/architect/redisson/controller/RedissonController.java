package org.spring.cloud.architect.redisson.controller;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.spring.cloud.architect.redisson.cache.RedissonService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by lee on 2019/3/25.
 */
@Slf4j
@RestController
@RequestMapping("/redisson")
public class RedissonController {

    @Resource
    RedissonService redissonService;

    @RequestMapping("/lock")
    public String lock(String param) {

        RLock result = redissonService.lock(param);

        System.out.println("---------------------="+param);
        redissonService.unlock(result);
        return result.isLocked() + "";
    }

    @RequestMapping("/unlock")
    public String unlock(String param) {
        redissonService.unlock(param);
        return "SUCCESS";
    }


}
