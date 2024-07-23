package com;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: Lisy
 * @Date: 2024/04/12/下午3:58
 * @Description:
 */
public class CommonTest {

    @Test
    public void test() {
        String property = System.getProperty("java.library.path");
        System.setProperty("java.library.path", "D:\\code\\jam\\java-common\\java-common\\src\\main\\resources\\lib");
        Arrays.stream(property.split(";")).forEach(System.out::println);
    }
}
