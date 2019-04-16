package org.spring.cloud.architect.redisson.config;

/**
 * Created by lee on 2019/3/25.
 */

import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties(prefix = "redisson.config")
@Configuration
public class RedissonConfig extends CachingConfigurerSupport {

    private String address;

//    @Bean
//    RedissonClient redissonClient() throws Exception {
//
//        Config config = new Config();
//        ClusterServersConfig clusterConfig = config.useClusterServers().
//                setScanInterval(2000).
//                setKeepAlive(true).
//                setMasterConnectionPoolSize(20).
//                setMasterConnectionMinimumIdleSize(10).
//                setSlaveConnectionPoolSize(20).
//                setSlaveConnectionMinimumIdleSize(10);
//        clusterConfig.setPingConnectionInterval(3000);
//        clusterConfig.addNodeAddress(address);
//
//        RedissonClient redisson = Redisson.create(config);
//        return redisson;
//    }
    @Bean
    RedissonClient redissonClient() throws Exception {
        Config config = new Config();
        SingleServerConfig clusterConfig = config.useSingleServer().
                setKeepAlive(true).
                setConnectionPoolSize(100).
                setAddress(address) ;
        clusterConfig.setPingConnectionInterval(3000);

        RedissonClient redisson = Redisson.create(config);
        return redisson;
    }

    /**
     * 哨兵模式自动装配
     *
     * @return
     */
//    @Bean
//    @ConditionalOnProperty(name="redisson.master-name")
//    RedissonClient redissonSentinel() {
//        Config config = new Config();
//        SentinelServersConfig serverConfig = config.useSentinelServers().addSentinelAddress("10.47.91.83:26379")
//                .setMasterName("mymaster")
//                .setTimeout(10000)
//                .setMasterConnectionPoolSize(20)
//                .setSlaveConnectionPoolSize(10);
//
//        if(StringUtils.isNotBlank(redssionProperties.getPassword())) {
//            serverConfig.setPassword(redssionProperties.getPassword());
//        }
//        return Redisson.create(config);
//    }


}

