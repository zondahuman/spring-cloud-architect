package org.spring.cloud.architect.lettuce.cache;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created by lee on 2019/3/25.
 * https://gitee.com/2016Young/codes/mvrlu9wtied6jpc10287z67
 */
@Component

public class CacheService {

    @Resource
    RedisTemplate<String, Object> redisTemplate;


    public boolean lock(String lockKey, Integer second, Long expireTime) {
        Long lockWaitTimeOut = 200L;
        Long currentTime = System.currentTimeMillis();
        Long deadTimeLine = currentTime + lockWaitTimeOut;
        Long lockValue = currentTime + second * 1000;
        try {

            for (; ; ) {

                Boolean result = redisTemplate.opsForValue()
                        .setIfAbsent(lockKey, lockValue, expireTime, TimeUnit.SECONDS);

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
        } catch (Exception e) {
            throw new RuntimeException(e);
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


    public boolean acquireLock(String lockKey, String lockValue, int expireTime) {

        for (; ; ) {
            Boolean result = redisTemplate.opsForValue()
                    .setIfAbsent(lockKey, lockValue, expireTime, TimeUnit.SECONDS);
            if (result) {
                return true;
            }
            return false;
        }

    }


}
