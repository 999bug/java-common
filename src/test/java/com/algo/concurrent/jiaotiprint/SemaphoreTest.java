package com.algo.concurrent.jiaotiprint;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

/**
 * @Author: Lisy
 * @Date: 2024/10/22/上午9:55
 * @Description:
 */
public class SemaphoreTest {

    private final AtomicLong total = new AtomicLong(0);
    private final int threadCount = 5;
    private final int printCount = 5;

    private final Semaphore semaphore = new Semaphore(1, true);

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
                        while (true) {
                            try {
                                semaphore.acquire();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }

                            // 检查是否轮到当前线程打印
                            if (total.get() == threadNum) {
                                System.out.println("Thread " + (threadNum + 1) + ": " + (threadNum + 1));
                                // 更新为下一个线程编号
                                total.set((total.get() + 1) % threadCount);
                                semaphore.release();
                                break; // 退出循环
                            }
                            // 如果不是该线程打印，则释放信号量并继续循环
                            semaphore.release();
                        }
                    });
        };

    }

}
