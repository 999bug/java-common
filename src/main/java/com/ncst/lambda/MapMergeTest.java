package com.ncst.lambda;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: Lisy
 * @Date: 2022/09/06/11:06
 * @Description:
 */
public class MapMergeTest {

    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] array = new int[]{1, 4, 5, 6, 7, 8, 5, 5, 4, 1, 7, 8, 8};
        String[] strings = new String[]{"12", "ef", "ef", "rtR", "rTr"};
        countNums(strings);

        map.forEach((k, v) -> {
            System.out.print(k + ":");
            System.out.println(v);
        });
    }

    private static void countNums(String[] array) {
        Map<String, Long> collect = Arrays.stream(array)
                .collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()));

        collect.forEach((k, v) -> {
            System.out.print(k + ":");
            System.out.println(v);
        });
    }

    private static void countNumsV2(Map<Integer, Integer> map, int[] array) {
        Arrays.stream(array).forEach(
                word -> {
                    map.merge(word, 1, Integer::sum);
                }
        );
    }
}
