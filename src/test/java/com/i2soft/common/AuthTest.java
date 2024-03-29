//package com.i2soft.common;
//
//import com.i2soft.http.I2Rs;
//import com.i2soft.http.I2softException;
//import com.i2soft.util.Configuration;
//import com.i2soft.util.StringMap;
//import com.i2soft.util.TestConfig;
//import org.junit.Assert;
//import org.junit.BeforeClass;
//import org.junit.FixMethodOrder;
//import org.junit.Test;
//import org.junit.runners.MethodSorters;
//
//import java.util.Map;
//
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//public class AuthTest {
//
//    private static Auth auth;
//
//    @BeforeClass
//    public static void setUp() {
//        if (auth != null) {
//            return;
//        }
//        try {
//            auth = Auth.token(TestConfig.ip, TestConfig.user, TestConfig.pwd, TestConfig.cachePath, new Configuration());
//        } catch (I2softException e) {
//            e.printStackTrace();
//            Assert.fail();
//        }
//    }
//
//    @Test
//    public void T01_describePhoneCode() {
//        try {
//            Map rs = auth.describePhoneCode(new StringMap()); // 发送请求
//            Assert.assertNotNull(rs); // 检查结果
//        } catch (I2softException e) {
//            e.printStackTrace();
//            Assert.fail();
//        }
//    }
//
//    @Test
//    public void T02_regAccount() {
//        try {
//            I2Rs.I2SmpRs rs = auth.regAccount(new StringMap()); // 发送请求
//            Assert.assertNotNull(rs); // 检查结果
//        } catch (I2softException e) {
//            e.printStackTrace();
//            Assert.fail();
//        }
//    }
//
//    @Test
//    public void T03_token() {
//        Assert.assertNotNull(auth); // 检查结果
//    }
//
//    @Test
//    public void T04_resetPwd() {
//        try {
//            I2Rs.I2SmpRs rs = auth.resetPwd(new StringMap()); // 发送请求
//            Assert.assertNotNull(rs); // 检查结果
//        } catch (I2softException e) {
//            e.printStackTrace();
//            Assert.fail();
//        }
//    }
//
//    @Test
//    public void T05_checkLoginStatus() {
//        try {
//            String rs = auth.checkLoginStatus(); // 发送请求
//            Assert.assertNotNull(rs); // 检查结果
//        } catch (I2softException e) {
//            e.printStackTrace();
//            Assert.fail();
//        }
//    }
//}