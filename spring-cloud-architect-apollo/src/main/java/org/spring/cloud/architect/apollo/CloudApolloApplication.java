package org.spring.cloud.architect.apollo;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Created by lee on 2019/3/23.
 */
@EnableApolloConfig
@SpringBootApplication
public class CloudApolloApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(CloudApolloApplication.class).web(WebApplicationType.SERVLET).run(args);
    }



}
