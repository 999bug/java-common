package com.algo.concurrent.tmp;

class PrintNumbers {
    private final Object lock = new Object();
    private int current = 1; // 1: Thread1, 2: Thread2

    public void printNum(int num) {
        synchronized (lock) {
            for (int i = 0; i < 10; i++) {
                while (current != num) { // 检查当前线程是否该打印
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println(num);
                current = (current == 1) ? 2 : 1; // 切换到另一个线程
                lock.notifyAll(); // 通知其他线程
            }
        }
    }
}

public class AlternatePrinting {
    public static void main(String[] args) {
        PrintNumbers printNumbers = new PrintNumbers();

        Thread thread1 = new Thread(() -> printNumbers.printNum(1));
        Thread thread2 = new Thread(() -> printNumbers.printNum(2));

        thread1.start();
        thread2.start();
    }
}
