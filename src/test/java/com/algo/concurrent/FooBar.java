package com.algo.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * 交替打印FooBar
 */
class FooBar {
    private int n;

    public FooBar(int n) {
        this.n = n;
    }

    Semaphore semaphoreFoo = new Semaphore(1);
    Semaphore semaphoreBar = new Semaphore(0);

    public void foo(Runnable printFoo) throws InterruptedException, BrokenBarrierException {

        for (int i = 0; i < n; i++) {
            semaphoreFoo.acquire();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            semaphoreBar.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException, BrokenBarrierException {

        for (int i = 0; i < n; i++) {
            semaphoreBar.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            semaphoreFoo.release();
        }
    }
}