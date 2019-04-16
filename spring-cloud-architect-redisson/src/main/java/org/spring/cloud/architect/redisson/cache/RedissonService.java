package org.spring.cloud.architect.redisson.cache;

import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

/**
 * Created by lee on 2019/3/25.
 */
public interface RedissonService {

    RLock lock(String lockKey);

    RLock lock(String lockKey, int timeout);

    RLock lock(String lockKey, TimeUnit unit, int timeout);

    boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime);

    void unlock(String lockKey);

    void unlock(RLock lock);



}