package org.spring.cloud.architect.service;

/**
 * Created by lee on 2019/2/1.
 */
public interface FeignService {


    String callService(String param);

    String getId(String param);

    String doit(String param);


}
