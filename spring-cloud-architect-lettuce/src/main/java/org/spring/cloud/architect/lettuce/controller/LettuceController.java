package org.spring.cloud.architect.lettuce.controller;

import lombok.extern.slf4j.Slf4j;
import org.spring.cloud.architect.lettuce.cache.CacheService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by lee on 2019/3/25.
 */
@Slf4j
@RestController
@RequestMapping("/lettuce")
public class LettuceController {

    @Resource
    CacheService cacheService;

    @PostMapping("/lock")
    public String lock(String key, String value, Long expire) {

        Boolean result = cacheService.lock(key, value, expire);

        System.out.println("---------------------=" + result);
        cacheService.unlock(key, value);
        return "SUCCESS";
    }

    @PostMapping("/unlock")
    public String unlock(String key, String value) {
        cacheService.unlock(key, value);
        return "SUCCESS";
    }


}
