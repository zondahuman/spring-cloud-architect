package org.spring.cloud.architect.apollo.controller;

import lombok.extern.slf4j.Slf4j;
import org.spring.cloud.architect.apollo.config.ApolloConfig;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by lee on 2019/3/23.
 */
@Slf4j
@RestController
@RequestMapping("/apollo")
public class ApolloConfigController {


    @Resource
    ApolloConfig apolloConfig ;


    @RequestMapping("/rule")
    public String findRule() {
        String result = "hello " + apolloConfig.getRule();
        return result;
    }


}
