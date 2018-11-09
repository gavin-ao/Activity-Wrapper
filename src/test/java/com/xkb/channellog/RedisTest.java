package com.xkb.channellog;

import com.xkb.channellog.Entity.AccessEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    JedisPool jedisPool;
    @Autowired
    AccessEntity accessEntity;
    @Test
    public void testString() throws JsonProcessingException {

        try (Jedis jedis = jedisPool.getResource()) {
            jedis.set("neo","ityouknow");
            Assert.assertEquals("ityouknow", jedis.get("neo"));
//            jedis.lpush("acess-test", "Runoob");
//            jedis.lpush("acess-test", "Google");
//            jedis.lpush("acess-test", "Taobao");
            List<String> list = jedis.lrange("acess-test", 0 ,-1);
            for(int i=0; i<list.size(); i++) {
                System.out.println("列表项为: "+list.get(i));
            }
//            JSONSerializer
//            JSON json  = JSONSerializer
//            JSONObject jsonObject = JSONObject.
//            AccessEntity accessEntity =
//                    new AccessEntity();
//            System.out.print(accessEntity.toString());
        }

    }

}