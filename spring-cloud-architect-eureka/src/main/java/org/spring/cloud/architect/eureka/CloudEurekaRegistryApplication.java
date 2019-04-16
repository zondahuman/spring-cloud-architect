package org.spring.cloud.architect.eureka;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@EnableEurekaServer
@SpringBootApplication
public class CloudEurekaRegistryApplication {


    public static void main(String[] args) {
        new SpringApplicationBuilder(CloudEurekaRegistryApplication.class).web(WebApplicationType.SERVLET).run(args);
    }


}