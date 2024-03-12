package com.ncst.json;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @Author: Lisy
 * @Date: 2024/03/12/11:23
 * @Description:
 */
public class RedisTest {
    Jedis jedis;

    @Before
    public void before() {
        jedis = new Jedis("127.0.0.1", 58067);
        jedis.auth("Info2soft@240119#!");
    }

    @Test
    public void testInfo() {
        String memory = jedis.info("memory");
        System.out.println("memory = " + memory);
        List<String> list = jedis.configGet("maxmemory");


    }

    @After
    public void after() {
        jedis.close();
    }
}
