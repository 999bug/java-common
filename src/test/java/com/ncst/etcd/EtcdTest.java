package com.ncst.etcd;

import org.junit.Test;

/**
 * @Author: Lisy
 * @Date: 2024/03/05/11:02
 * @Description:
 */
public class EtcdTest {
    String endpoints = "http://192.168.46.84:2379";
    @Test
    public void test() {
        Register register = new Register(endpoints);
        String key = "/web/node0";
        String value = "localhost:7999";
        try {
            register.put(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            register.putWithLease(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDiscovery() {
        Discovery ser = new Discovery(endpoints);
        ser.watchService("/web/");
        while (true) {

        }
    }
}
