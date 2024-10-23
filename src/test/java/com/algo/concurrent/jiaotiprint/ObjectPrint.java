package com.algo.concurrent.jiaotiprint;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

public class ObjectPrint {

    private final AtomicLong total = new AtomicLong(0);
    private final int threadCount = 3;
    private final int printCount = 12;

    private final Object obj = new Object();

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
                        while (total.get() != threadNum) {
                            synchronized (obj) {
                                try {
                                    obj.wait();
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                        System.out.println("Thread " + (threadNum + 1) + ": " + (threadNum + 1));
                        total.set((total.get() + 1) % threadCount);
                        synchronized (obj) {
                            obj.notifyAll();
                        }
                    });
        };

    }

}
