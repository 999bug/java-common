package com.algo.concurrent;

class H2O {

    int h = 0;
    int o = 0;

    public H2O() {

    }

    private final Object object = new Object();

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        synchronized (object) {
            while (h == 2) {
                object.wait();
            }
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseHydrogen.run();
            h += 1;
            if (h == 2 && o == 1) {
                h = 0;
                o = 0;
                object.notifyAll();
            }
        }

    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        synchronized (object) {
            while (o == 1) {
                object.wait();
            }
            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            releaseOxygen.run();
            o += 1;
            if (h == 2 && o == 1) {
                h = 0;
                o = 0;
                object.notifyAll();
            }
        }
    }
}