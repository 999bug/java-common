package com.algo.concurrent.jiaotiprint;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * @Author: Lisy
 * @Date: 2024/10/22/上午9:55
 * @Description:
 */
public class ConditionPrint {

    private final AtomicLong total = new AtomicLong(0);
    private final int threadCount = 5;
    private final int printCount = 12;

    private final Lock lock = new ReentrantLock(true);
    private final Condition condition = lock.newCondition();

    @Test
    public void print() {
        CompletableFuture.allOf(init()).join();
    }

    private CompletableFuture<?>[] init() {
        CompletableFuture<?>[] futures = new CompletableFuture<?>[threadCount];
        for (int i = 0; i < threadCount; i++) {
            futures[i] = CompletableFuture.runAsync(print(i));
        }
        return futures;
    }

    public Runnable print(int threadNum) {
        return () -> {
            IntStream.range(0, printCount)
                    .forEach(k -> {
                        lock.lock();
                        try {
                            while (total.get() != threadNum) {
                                condition.await();
                            }
                            System.out.println("Thread " + (threadNum + 1) + ": " + (threadNum + 1));
                            total.set((total.get() + 1) % threadCount);
                            condition.signalAll();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        } finally {
                            lock.unlock();
                        }
                    });
        };

    }

}
