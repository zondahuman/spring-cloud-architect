package org.spring.cloud.architect.redis.cache;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCommands;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by lee on 2019/3/25.
 */
@Component

public class CacheService {

    @Resource
    RedisTemplate<String, Object> redisTemplate;


    public boolean lock(String key, String value, Long expire) {
        Long lockWaitTimeOut = 200L;
        Long deadTimeLine = System.currentTimeMillis() + lockWaitTimeOut;

        try {
            String realKey = key;

            for (; ; ) {
                RedisCallback<String> callback = (connection) -> {
                    JedisCommands commands = (JedisCommands) connection.getNativeConnection();
                    return commands.set(key, value, "NX", "PX", expire);
                };
                String result = redisTemplate.execute(callback);

                if (result.equals("1")) {
                    return true;
                }

                String currentValue = (String) redisTemplate.opsForValue().get(realKey);

                // if lock is expired
                if (!StringUtils.isEmpty(currentValue) &&
                        Long.valueOf(currentValue) < System.currentTimeMillis()) {
                    // gets last lock time
                    String oldValue = (String) redisTemplate.opsForValue().getAndSet(realKey, value);

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


    public List<Object> getValueByKeys(final List<String> keys) throws Exception{
        if (CollectionUtils.isEmpty(keys))
            return null;
        return redisTemplate.executePipelined(new RedisCallback<Object>() {
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
//                StringRedisConnection stringRedisConn = (StringRedisConnection) connection;
                for (String key : keys) {
                        connection.get(key.getBytes());
                }
                return null;
            }
        });
    }


}
