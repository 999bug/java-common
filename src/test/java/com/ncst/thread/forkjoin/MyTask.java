package com.ncst.thread.forkjoin;

import cn.hutool.core.collection.ConcurrentHashSet;

import java.io.File;
import java.util.*;
import java.util.concurrent.RecursiveTask;

/**
 * @Author: lisy
 * @Date: 2024/02/02/23:11
 * @Description:
 */
public class MyTask extends RecursiveTask<Set<File>> {

    private final File file;

    public MyTask(File file) {
        this.file = file;
    }

    @Override
    protected Set<File> compute() {
        Set<File> paths = new ConcurrentHashSet<>();
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (Objects.nonNull(files)) {
                Arrays.stream(files).parallel().forEach(k -> {
                    if (k.isDirectory()) {
                        MyTask myTask1 = new MyTask(k);
                        invokeAll(myTask1);
                        Set<File> join = myTask1.join();
                        paths.addAll(join);
                    } else {
                        paths.add(k);
                    }
                });
            }
        } else {
           paths.add(file);
        }
        return paths;
    }
}
