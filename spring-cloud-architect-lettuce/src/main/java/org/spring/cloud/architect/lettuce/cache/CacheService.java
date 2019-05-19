package org.spring.cloud.architect.lettuce.cache;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by lee on 2019/3/25.
 * https://gitee.com/2016Young/codes/mvrlu9wtied6jpc10287z67
 * https://blog.csdn.net/lmb55/article/details/78235768
 */
@Component

public class CacheService {

    @Resource
    RedisTemplate<String, Object> redisTemplate;


    public boolean lock(String lockKey, Integer second, Long expireTime) {
        Long lockWaitTimeOut = 2000L;
        Long currentTime = System.currentTimeMillis();
        Long deadTimeLine = currentTime + lockWaitTimeOut;
        Long lockValue = currentTime + second * 1000;
        String identifier = UUID.randomUUID().toString();

        for (; ; ) {

            Boolean result = redisTemplate.opsForValue()
                    .setIfAbsent(lockKey, identifier, expireTime, TimeUnit.SECONDS);

            if (result) {
                return true;
            }

            String currentValue = (String) redisTemplate.opsForValue().get(lockKey);

            // if lock is expired
            if (!StringUtils.isEmpty(currentValue) &&
                    Long.valueOf(currentValue) < System.currentTimeMillis()) {
                // gets last lock time
                String oldValue = (String) redisTemplate.opsForValue().getAndSet(lockKey, lockValue);

                if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)) {
                    return true;
                }
            }

            lockWaitTimeOut = deadTimeLine - System.currentTimeMillis();

            if (lockWaitTimeOut <= 0L) {
                return false;
            }
        }

    }


    public void unlock(String key, String value) {
        try {
            String currentValue = (String) redisTemplate.opsForValue().get(key);

            if (!StringUtils.isEmpty(currentValue)
                    && value.equals(currentValue)) {
                redisTemplate.delete(key);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public boolean acquireLock(String lockKey, String lockValue, Long expireTime) {
        Long lockWaitTimeOut = 2000L;
        Long currentTime = System.currentTimeMillis();
        Long deadTimeLine = currentTime + lockWaitTimeOut;
        for (; ; ) {
            Boolean result = redisTemplate.opsForValue()
                    .setIfAbsent(lockKey, lockValue, expireTime, TimeUnit.SECONDS);
            if (result) {
                return true;
            }
            lockWaitTimeOut = deadTimeLine - System.currentTimeMillis();

            if (lockWaitTimeOut <= 0L) {
                return false;
            }
        }

    }


    public void releaseLock(String lockKey, String lockValue) {
        String currentValue = (String) redisTemplate.opsForValue().get(lockKey);

        if (StringUtils.isNotBlank(currentValue)
                && StringUtils.equals(lockValue, currentValue)) {
            redisTemplate.delete(lockKey);
        }

    }


}
