package com.ncst.concurrent;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @Author: Lisy
 * @Date: 2023/03/03/15:37
 * @Description:
 */
public class CasCount {
    volatile int count = 0;
    int total = 100;
    @Test
    public void test() {
        AtomicInteger a = new AtomicInteger(1);
        int andIncrement = a.getAndIncrement();
        System.out.println("andIncrement = " + andIncrement);

    }

    public int cas(int expect, int newValue) {
        int current = count;
        if (current == expect) {
            count = newValue;
            return count;
        } else{
            return newValue;
        }

    }
}
