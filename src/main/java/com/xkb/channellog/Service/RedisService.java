package com.xkb.channellog.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.Serializable;

@Service
public class RedisService {
//    @Autowired
//    JedisPool jedisPool;
//    public boolean set(final String key, Object value) {
//        boolean result = false;
//        try {
//            Jedis jedis = jedisPool.getResource();
//           jedis.
//            result = true;
//        } catch (Exception e) {
//        }
//        return result;
//    }
}
