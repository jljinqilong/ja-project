package com.champlink.common.util.cache;

import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import com.champlink.common.constant.Constant;
import com.champlink.common.domain.system.User;

@Component
public class RedisService {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    public boolean writeValue(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Object, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean writeValue(final String key, Object value, long timeout) {
        boolean result = false;
        try {
            ValueOperations<Object, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Object readValue(final String key) {
        Object result = null;
        ValueOperations<Object, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    @SuppressWarnings("unchecked")
    public <T> T readValue(final String key, Class<T> clazz) {
        ValueOperations<Object, Object> operations = redisTemplate.opsForValue();
        Object value = operations.get(key);
        if (value != null) {
            return (T) value;
        }
        return null;
    }

    public boolean remove(final String key) {
        if (this.exists(key)) {
            return redisTemplate.delete(key);
        }
        return true;
    }

    /**
     * 根据token获取登录的用户
     * 
     * @param token
     * @return
     */
    public User getLoginUserByToken(String token) {
        return readValue(Constant.sessionKeyStart + token, User.class);
    }

}
