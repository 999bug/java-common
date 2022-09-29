package com.ncst.jvm;

import org.junit.Test;
import sun.awt.image.ImageWatched;

import java.io.*;
import java.lang.management.LockInfo;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author Lsy
 * @date 2022/4/25
 */
public class CustomClassLoader extends ClassLoader {

    public static void main(String[] args) {
        try {
            new CustomClassLoader().findClass("com.ncst.jvm.Custom").newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        test1(new ArrayList());
        test1(new LinkedList());

        test2(new ArrayList());
    }

    private void test1(List list) {

    }

    private void test2(ArrayList list) {

    }

    @Test
    public void testInputStream() throws IOException {
        String path = "D:\\code\\jam\\java-common\\java-common\\src\\test\\java\\com\\ncst\\jvm\\Custom.java";
        final BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(path));
        final int read = bufferedReader.read();
        final String s = bufferedReader.readLine();
        System.out.println("s = " + s);
    }


    @Test
    public void test1() throws IOException {
        StringWriter buffer = new StringWriter();
        try (PrintWriter pw = new PrintWriter(buffer)) {
            pw.println("Hello");
            pw.println(12345);
            pw.println(true);
        }
        System.out.println(buffer.toString());
    }
}
