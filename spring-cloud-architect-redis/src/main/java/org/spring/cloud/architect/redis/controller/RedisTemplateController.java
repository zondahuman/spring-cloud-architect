package org.spring.cloud.architect.redis.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.cloud.architect.redis.cache.CacheService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lee on 2019/3/25.
 */
@Slf4j
@RestController
@RequestMapping("/redis")
public class RedisTemplateController {
    protected final static Logger logger = LoggerFactory.getLogger(RedisTemplateController.class);

    @Resource
    CacheService cacheService;

    @RequestMapping("/getValueByKeys")
    public List<Object> getValueByKeys(String param) throws Exception {
        List<String> keys = Lists.newArrayList("lee1", "lee3", "lee2");
        List<Object> result = cacheService.getValueByKeys(keys);

        System.out.println("---------------------result=" + JSON.toJSONString(result));
        return result;
    }



    @RequestMapping("/getValueByKeys1")
    public List<Object> getValueByKeys1(@RequestBody List<String> paramList) throws Exception {
//        List<String> keys = Lists.newArrayList("lee1", "lee3", "lee2");
        List<Object> result = cacheService.getValueByKeys(paramList);

        System.out.println("---------------------result=" + JSON.toJSONString(result));
        return result;
    }


    @RequestMapping("/getValueByKeys2")
    public List<Object> getValueByKeys1(@RequestBody String[] paramArray) throws Exception {
        List<String> paramList = Arrays.asList(paramArray);
        List<Object> result = cacheService.getValueByKeys(paramList);

        System.out.println("---------------------result=" + JSON.toJSONString(result));
        return result;
    }





}
