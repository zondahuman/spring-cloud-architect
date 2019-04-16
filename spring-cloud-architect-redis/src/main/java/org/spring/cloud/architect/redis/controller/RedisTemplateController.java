package org.spring.cloud.architect.redis.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.spring.cloud.architect.redis.cache.CacheService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lee on 2019/3/25.
 */
@Slf4j
@RestController
@RequestMapping("/redis")
public class RedisTemplateController {

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
    public List<Object> getValueByKeys1(List<String> paramList) throws Exception {
//        List<String> keys = Lists.newArrayList("lee1", "lee3", "lee2");
        List<Object> result = cacheService.getValueByKeys(paramList);

        System.out.println("---------------------result=" + JSON.toJSONString(result));
        return result;
    }


}
