package org.spring.cloud.architect.provider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lee on 2019/2/1.
 */
@Slf4j
@Controller
@RequestMapping("/order")
public class OrderController {


    @RequestMapping(value = "/getId", method = {RequestMethod.GET})
    @ResponseBody
    public String getId(String param) {
        log.info("getId--param=" + param);
        long start = System.currentTimeMillis();
        String result = "";
        try {
            result = "provider--service--getId " + param;
        } catch (Exception e) {
            log.error("e={}", e);
            result = "FAILURE";
        }
        log.info("getId--param=" + param + " ,result=" + result + ",cost=" + (System.currentTimeMillis() - start));
        return result;
    }


    @RequestMapping(value = "/doit", method = {RequestMethod.POST})
    @ResponseBody
    public String doit(String param) {
        log.info("doit--param=" + param);
        long start = System.currentTimeMillis();
        String result = "";
        try {
            result = "provider--service--doit " + param;
        } catch (Exception e) {
            log.error("e={}", e);
            result = "FAILURE";
        }
        log.info("doit--param=" + param + " ,result=" + result + ",cost=" + (System.currentTimeMillis() - start));
        return result;
    }


}
