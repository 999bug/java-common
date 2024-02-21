package com.ncst.java8;

import org.junit.Test;

import java.util.Optional;

/**
 * @Author: lisy
 * @Date: 2023/09/20/21:46
 * @Description:
 */
public class OptionTest {

    @Test
    public void test() {
        System.out.println("--------不为null的情况----------");
        //不为 null
        String str1 = "hello";
        String result11 = Optional.ofNullable(str1).orElse(get(str1 + ":orElse()方法被执行了"));
        String result12 = Optional.ofNullable(str1).orElseGet(() -> get(str1 + ":orElseGet()方法被执行了"));
        System.out.println(result11);
        System.out.println(result12);
        System.out.println("--------为null的情况----------");
        //为 null
        String str2 = null;
        String result21 = Optional.ofNullable(str2).orElse(get(str1 + ":orElse()方法被执行了"));
        String result22 = Optional.ofNullable(str2).orElseGet(() -> get(str2 + ":orElseGet()方法被执行了"));
        System.out.println(result21);
        System.out.println(result22);
    }


    public String get(String name) {
        System.out.println(name);
        return name;
    }
}
