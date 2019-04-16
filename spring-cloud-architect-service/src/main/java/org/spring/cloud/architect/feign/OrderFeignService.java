package org.spring.cloud.architect.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by lee on 2019/2/15.
 */
@FeignClient(name = "cloud-provider")
public interface OrderFeignService {

    @GetMapping(value = "/order/getId")
    String getId(@RequestParam(value = "param") String param);

    @GetMapping(value = "/order/doit")
    String doit(@RequestParam(value = "param") String param);


}
