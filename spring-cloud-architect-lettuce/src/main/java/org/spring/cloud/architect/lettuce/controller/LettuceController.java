package org.spring.cloud.architect.lettuce.controller;

import com.google.common.primitives.Ints;
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

        Boolean result = cacheService.lock(key, Ints.tryParse(value), expire);

        System.out.println("---------------------=" + result);
        cacheService.unlock(key, value);
        return "SUCCESS";
    }

    @PostMapping("/unlock")
    public String unlock(String key, String value) {
        cacheService.unlock(key, value);
        return "SUCCESS";
    }



    @PostMapping("/acquireLock")
    public String acquireLock(String key, String value, Long expire) {
        System.out.println("key=" + key+",value=" + value+",expire=" + expire);
        Boolean result = cacheService.acquireLock(key, value, expire);

        System.out.println("key=" + key+",value=" + value+",expire=" + expire+",result=" + result);
        return "SUCCESS";
    }



    @PostMapping("/releaseLock")
    public String releaseLock(String key, String value) {
        System.out.println("key=" + key+",value=" + value);

        cacheService.releaseLock(key, value);
        return "SUCCESS";
    }




}
