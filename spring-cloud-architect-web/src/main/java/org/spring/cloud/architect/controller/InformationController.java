package org.spring.cloud.architect.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.cloud.architect.common.util.JsonUtil;
import org.spring.cloud.architect.model.ArchitectureVo;
import org.spring.cloud.architect.service.InformationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by lee on 2019/1/30.
 */
@Controller
@RequestMapping("/msg")
public class InformationController {
    protected final static Logger logger = LoggerFactory.getLogger(InformationController.class);

    @Resource
    InformationService informationService;

    @RequestMapping(value = "/receive", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String receive(String param) {
        logger.info("receive--param=" + param);
        long start = System.currentTimeMillis();
        try {
            this.informationService.find(param);
        } catch (Exception e) {
            logger.error("e={}", e);
            return "FAILURE";
        }
        logger.info("receive--param=" + param + ",cost=" + (System.currentTimeMillis() - start));
        return "SUCCESS";
    }


    @RequestMapping(value = "/hiveLog", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String hiveLog(String param) {
        logger.info("hiveLog--param=" + param);
        long start = System.currentTimeMillis();
        try {
            ArchitectureVo architectureVoJson = JsonUtil.decodeJson(param, new TypeReference<ArchitectureVo>() {
            });
            this.informationService.consumeTbox(architectureVoJson);
        } catch (Exception e) {
            logger.error("e={}", e);
            return "FAILURE";
        }
        logger.info("hiveLog--param=" + param + ",cost=" + (System.currentTimeMillis() - start));
        return "SUCCESS";
    }


    @RequestMapping(value = "/report", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String report(String json) {
        logger.info("report--json=" + json);
        long start = System.currentTimeMillis();
        try {
            ArchitectureVo architectureVoJson = JsonUtil.decodeJson(json, new TypeReference<ArchitectureVo>() {
            });
            this.informationService.consumeTbox(architectureVoJson);
        } catch (Exception e) {
            logger.error("e={}", e);
            return "FAILURE";
        }
        logger.info("report--json=" + json + ",cost=" + (System.currentTimeMillis() - start));
        return "SUCCESS";
    }


}



