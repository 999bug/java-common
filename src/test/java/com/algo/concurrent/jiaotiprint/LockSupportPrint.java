package com.algo.concurrent.jiaotiprint;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.LockSupport;
import java.util.stream.IntStream;

/**
 * @Author: Lisy
 * @Date: 2024/10/22/上午9:55
 * @Description:
 */
public class LockSupportPrint {

    private final AtomicLong total = new AtomicLong(0);
    private final int threadCount = 5;
    private final int printCount = 5;

    private final Thread[] threads = new Thread[threadCount];

    @Test
    public void print() {
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(print(i));
            threads[i].start();
        }

        // 唤醒第一个线程开始打印
        LockSupport.unpark(threads[0]);

        // 等待所有线程完成
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }

    public Runnable print(int threadNum) {
        return () -> {
            IntStream.range(0, printCount)
                    .forEach(k -> {
                        // 进入循环，直到获取到信号量
                        while (true) {
                            // 检查是否轮到当前线程打印
                            if (total.get() == threadNum) {
                                System.out.println("Thread " + (threadNum + 1) + ": " + (threadNum + 1));
                                // 更新为下一个线程编号
                                total.set((total.get() + 1) % threadCount);
                                LockSupport.unpark(threads[(threadNum + 1) % threadCount]);
                                break;
                            }
                            LockSupport.park();
                        }
                    });
        };

    }

}
