package com.ncst.json;

import com.alibaba.fastjson.JSONObject;
import com.google.flatbuffers.FlatBufferBuilder;
import com.ncst.etcd.Register;
import com.ncst.flatbuffers.JsonObj;
import io.etcd.jetcd.ByteSequence;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.commands.RedisPipeline;

import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.LongStream;

/**
 * @Author: Lisy
 * @Date: 2024/03/11/17:58
 * @Description:
 */
public class JsonTest {

    String endpoints = "http://192.168.46.84:2379";
    Register register;
    int num = 10_000_000;

    @Before
    public void before() {
        register = new Register(endpoints);
    }

    @Test
    public void testSerialJson() {
        JsonObj jsonObj = new JsonObj();
        jsonObj.setId(12);
        jsonObj.setName("retert");
        String key = "test";
        AtomicInteger a = new AtomicInteger();
        long start = System.currentTimeMillis();
        LongStream.range(0, num).forEach(i -> {
            try {
                register.put(key + a.getAndIncrement(), JSONObject.toJSONString(jsonObj));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        long l = System.currentTimeMillis() - start;
        System.out.println("json put key num: " + num + " use time " + l);
    }

    @Test
    public void testRedis() {
        Jedis jedis = new Jedis("127.0.0.1", 58067);
        jedis.auth("Info2soft@240119#!");
        String key = "test3";
        String value = "retert";
        AtomicInteger a = new AtomicInteger();
        long start = System.currentTimeMillis();
        try (Pipeline pipeline = jedis.pipelined()) {
            AtomicInteger tmp = new AtomicInteger(1);
            LongStream.range(0, num).forEach(i -> {
                String key1 = key + a.getAndIncrement();
                pipeline.set(key1, value);
                pipeline.expire(key1, 600);
                // 执行批量操作
                if (tmp.getAndIncrement() % 10000 ==0) {
                    pipeline.sync();
                }
            });
        }
        jedis.close();
        long l = System.currentTimeMillis() - start;
        System.out.println("Redis put key num: " + num + " use time " + l);
    }

    @Test
    public void testRedisInfo() {
        Jedis jedis = new Jedis("127.0.0.1", 58067);
        jedis.auth("Info2soft@240119#!");
        String key = "test3";
        String value = "retert";
        AtomicInteger a = new AtomicInteger();
        long start = System.currentTimeMillis();
        try (Pipeline pipeline = jedis.pipelined()) {
            AtomicInteger tmp = new AtomicInteger(1);
            LongStream.range(0, num).forEach(i -> {
                String key1 = key + a.getAndIncrement();
                pipeline.set(key1, value);
                pipeline.expire(key1, 600);
                // 执行批量操作
                if (tmp.getAndIncrement() % 10000 ==0) {
                    pipeline.sync();
                }
            });
        }
        jedis.close();
        long l = System.currentTimeMillis() - start;
        System.out.println("Redis put key num: " + num + " use time " + l);
    }

    @Test
    public void testRedisPut() {
        Jedis jedis = new Jedis("127.0.0.1", 58067);
        jedis.auth("Info2soft@240119#!");
        String key = "testJson";
        String value = "retert";
        AtomicInteger a = new AtomicInteger();
        FlatBufferBuilder builder = new FlatBufferBuilder(1024);
        long start = System.currentTimeMillis();
        try (Pipeline pipeline = jedis.pipelined()) {
            AtomicInteger tmp = new AtomicInteger(1);
            String key1 = key + a.getAndIncrement();
            JsonObj jsonObj = new JsonObj();
            jsonObj.setId(12);
            jsonObj.setName("retert");
            pipeline.set(key1, JSONObject.toJSONString(jsonObj));
            pipeline.sync();

        }
        jedis.close();
        long l = System.currentTimeMillis() - start;
        System.out.println("Redis put key num: " + num + " use time " + l);
    }

//    @Test
//    public void testFlattBuffer() {
//        FlatBufferBuilder builder = new FlatBufferBuilder(1024);
//        String key = "test";
//        AtomicInteger a = new AtomicInteger();
//        Jedis jedis = new Jedis("127.0.0.1", 58067);
//        jedis.auth("Info2soft@240119#!");
//        long start = System.currentTimeMillis();
//            try {
//                jedis.set(key, setData("retert", 12, builder));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        long l = System.currentTimeMillis() - start;
//        System.out.println("FlatBuffer put key num: " + num + " use time " + l);
//    }

//    private ByteBuffer setData(String name, int id, FlatBufferBuilder builder) {
////        int nameOffset = builder.createString(name);
//////        MyObject.startMyObject(builder);
//////        MyObject.addId(builder, id);
//////        MyObject.addName(builder, nameOffset);
//////        int myObjectOffset = MyObject.endMyObject(builder);
////
////        // 结束FlatBuffer构建
////        builder.finish(myObjectOffset);
////        return builder.dataBuffer();
//        // 获取序列化后的数据
////        byte[] serializedData = builder.sizedByteArray();
////        return ByteSequence.from(serializedData);
//    }
}
