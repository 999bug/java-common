package com.ncst.thread.forkjoin;

import cn.hutool.core.collection.ConcurrentHashSet;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;

/**
 * @Author: lisy
 * @Date: 2024/02/02/23:10
 * @Description:
 */
public class ForkJoinPollTest {
    private static final String path = "C:\\";

    @Test
    public void test1() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool(200);
        File file = new File(path);
        MyTask myTask = new MyTask(file);
        ForkJoinTask<Set<File>> submit = forkJoinPool.submit(myTask);
        Set<File> files = submit.get();
        long useTIme = System.currentTimeMillis() - start;
//        files.forEach(System.out::println);
        System.out.println("文件数量" + files.size());
        System.out.println("useTime = " + useTIme);
    }

    @Test
    public void test2() {
        long start = System.currentTimeMillis();
        File file = new File(path);
        ConcurrentHashSet<File> sets = new ConcurrentHashSet<>();
        func(file, sets);
        long useTIme = System.currentTimeMillis() - start;
//        sets.forEach(System.out::println);
        System.out.println("文件数量" + sets.size());
        System.out.println("useTime = " + useTIme);
    }

    private static void func(File file, Set<File> sets) {
        File[] fs = file.listFiles();
        if (fs != null) {
            Arrays.stream(fs).parallel().forEach(f -> {
                if (f.isDirectory())    //若是目录，则递归打印该目录下的文件
                    func(f, sets);
                if (f.isFile())        //若是文件，直接打印
                    sets.add(f);
            });

        }
    }
}
