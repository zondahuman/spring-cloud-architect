package org.spring.cloud.architect.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.spring.cloud.architect.feign.OrderFeignService;
import org.spring.cloud.architect.service.FeignService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.RejectedExecutionException;

/**
 * Created by lee on 2019/2/1.
 */
@Slf4j
@Service
public class FeignServiceImpl implements FeignService {

    @Resource
    OrderFeignService orderFeignService;


    @HystrixCommand(fallbackMethod = "callServiceFallback",
            commandProperties = {
                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//设置熔断
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),//断路器的最小请求数
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),//休眠时间
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),//断路频率
            })
    @Override
    public String callService(String param) {
        log.error("param=" + param);
        if (true) {
            throw new RuntimeException("throw a new exception");
        }
        return "hi, " + param;
    }


    public String callServiceFallback(String param, Throwable throwable) {
        log.error("throwable=" + throwable);
        return "EXCEPTION";
    }


    @HystrixCommand(fallbackMethod = "getIdFallback",
            threadPoolProperties = {@HystrixProperty(name = "coreSize", value = "1"),
                    //缓冲区大小， 如果为-1，则不缓冲，直接进行降级 fallback, 当在配置时间窗口内达到此数量的失败后，进行短路。默认20个
//            @HystrixProperty(name = "maxQueueSize", value = "1")
                    //缓冲区大小超限的阈值，超限就直接降级
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "1")
            },
            commandProperties = {
                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//设置熔断
                    //前提条件，一定时间内发起一定数量的请求。  也就是5秒钟内(这个5秒对应下面的滚动窗口长度)至少请求4次，熔断器才发挥起作用。  默认20
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "3000"),//休眠时间
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),//断路频率
            })
    @Override
    public String getId(String param) {
        log.info("getId--param=" + param);
        String result = this.orderFeignService.getId(param);
        return result;
    }

    public String getIdFallback(String param, Throwable throwable) {
        log.info("getIdFallback--param=" + param + " ,throwable=" + throwable);
        if (throwable instanceof RejectedExecutionException) {
            System.out.println("--------------------------------限流------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
        if (!(throwable instanceof HystrixBadRequestException) && !(throwable instanceof RejectedExecutionException)) {
            System.out.println("************************************降级***********************************************************************************************************************************************************************************************************************");
        }
        return "EXCEPTION";
    }


    @Override
    public String doit(String param) {
        String result = this.orderFeignService.doit(param);
        return result;
    }


}
