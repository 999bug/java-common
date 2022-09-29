package com.ncst.lambda;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: Lisy
 * @Date: 2022/09/06/10:35
 * @Description:
 */
public class FlatMapTest {

    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(4);
        strings.add("我");
        strings.add("是");
        strings.add("你");
        integers.stream()
                .flatMap(integer -> strings.stream().map(s -> func(integer, s)))
                .collect(Collectors.toList())
                .forEach(System.out::println);


    }


    static String func(int a, String c) {
        return a + c;
    }
}
