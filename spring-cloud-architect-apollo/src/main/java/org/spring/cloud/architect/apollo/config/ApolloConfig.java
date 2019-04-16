package org.spring.cloud.architect.apollo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lee on 2019/3/23.
 */
@Configuration
@Data
public class ApolloConfig {


    @Value("${rule:'guys'}")
    private String rule;




}
