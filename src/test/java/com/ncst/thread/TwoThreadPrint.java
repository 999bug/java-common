package com.ncst.thread;

import org.junit.ComparisonFailure;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: lisy
 * @Date: 2023/10/25/21:08
 * @Description:
 */
public class TwoThreadPrint {


    @Test
    public void test() {
        int num = 10;
        Object lockA = new Object();
        AtomicInteger count = new AtomicInteger(0);
        CompletableFuture.runAsync(() -> {
            while (count.get() < num) {
                synchronized (lockA) {
                    lockA.notifyAll();
                    try {
                        System.out.print("1\t");
                    } finally {
                        try {
                            lockA.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        count.incrementAndGet();
                    }
                }
            }
        });

        CompletableFuture.runAsync(() -> {
            while (count.get() < num) {
                synchronized (lockA) {
                    lockA.notifyAll();
                    try {
                        System.out.print("2\t");
                    } finally {
                        try {
                            lockA.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        count.incrementAndGet();
                    }
                }
            }
        });
    }

    @Test
    public void test2() throws InterruptedException {
        int num = 10;
        Lock lockA = new ReentrantLock();
        AtomicInteger count = new AtomicInteger(0);
        CompletableFuture.runAsync(() -> {
            while (count.get() < num && lockA.tryLock()) {
                lockA.lock();
                try {
                    System.out.print("1\t");
                } finally {
                    lockA.unlock();
                    count.incrementAndGet();
                }
            }
        });

        CompletableFuture.runAsync(() -> {
            while (count.get() < num && lockA.tryLock()) {
                lockA.lock();
                try {
                    System.out.print("2\t");
                } finally {
                    lockA.unlock();
                    count.incrementAndGet();
                }
            }
        });

        TimeUnit.DAYS.sleep(1);
    }


}
