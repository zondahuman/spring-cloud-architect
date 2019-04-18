package org.spring.cloud.architect.redis.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.cloud.architect.redis.model.UserBean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lee on 2019/3/25.
 */
@Slf4j
@RestController
@RequestMapping("/model")
public class ModelController {

    protected final static Logger logger = LoggerFactory.getLogger(ModelController.class);


    @RequestMapping("/arrayToList")
    public List<UserBean> arrayToList(@RequestBody UserBean[] paramArray) throws Exception {
        logger.info("---------------------paramArray=" + JSON.toJSONString(paramArray));

        List<UserBean> paramList = Arrays.asList(paramArray);

        logger.info("---------------------paramList=" + JSON.toJSONString(paramList));
        return paramList;
    }


    @RequestMapping("/listToArray")
    public List<UserBean> listToArray(@RequestBody List<UserBean> paramList) throws Exception {
        logger.info("---------------------paramList=" + JSON.toJSONString(paramList));

        UserBean[] paramArray = paramList.toArray(new UserBean[paramList.size()]);

        logger.info("---------------------paramArray=" + JSON.toJSONString(paramArray));
        return paramList;
    }








}
