package org.spring.cloud.architect.controller;

import lombok.extern.slf4j.Slf4j;
import org.spring.cloud.architect.service.FeignService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by lee on 2019/2/1.
 */
@Slf4j
@RestController
@RequestMapping("/feign")
public class FeignController {


    @Resource
    FeignService feignService ;


    @RequestMapping(value = "/call", method = {RequestMethod.POST, RequestMethod.GET})
    public String call(String param) {
        log.info("call--param=" + param);
        long start = System.currentTimeMillis();
        String result = "";
        try {
            result = this.feignService.callService(param);
        } catch (Exception e) {
            log.error("e={}", e);
            result = "FAILURE";
        }
        log.info("call--param=" + param + ",cost=" + (System.currentTimeMillis() - start));
        return result;
    }


    @RequestMapping(value = "/getId", method = {RequestMethod.POST, RequestMethod.GET})
    public String getId(String param) {
        log.info("getId--param=" + param);
        long start = System.currentTimeMillis();
        String result = "";
        try {
            result = this.feignService.getId(param);
        } catch (Exception e) {
            log.error("e={}", e);
            result = "FAILURE";
        }
        log.info("getId--param=" + param + " ,result=" + result + ",cost=" + (System.currentTimeMillis() - start));
        return result;
    }


    @RequestMapping(value = "/doit", method = {RequestMethod.POST})
    public String doit(String param) {
        log.info("doit--param=" + param);
        long start = System.currentTimeMillis();
        String result = "";
        try {
            result = this.feignService.doit(param);
        } catch (Exception e) {
            log.error("e={}", e);
            result = "FAILURE";
        }
        log.info("doit--param=" + param + " ,result=" + result + ",cost=" + (System.currentTimeMillis() - start));
        return result;
    }


    @RequestMapping(value = "/getIdHystrix", method = {RequestMethod.POST, RequestMethod.GET})
    public String getIdHystrix(String param) {
        log.info("getIdHystrix--param=" + param);
        long start = System.currentTimeMillis();
        String result = "";
        try {
            for (int i = 0; i < 1000000; i++) {
                result = this.feignService.getId(param);
            }

        } catch (Exception e) {
            log.error("e={}", e);
            result = "FAILURE";
        }
        log.info("getIdHystrix--param=" + param + " ,result=" + result + ",cost=" + (System.currentTimeMillis() - start));
        return result;
    }


}
