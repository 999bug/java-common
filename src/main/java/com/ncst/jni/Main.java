package com.ncst.jni;

public class Main {
    public static void main(String[] args) {


        FileUtils fileUtils = new FileUtils();
        String filePath = "F:\\test.txt";

        // 打开文件
        int fd = fileUtils.open(filePath);
        System.out.println("fd = " + fd);
        if (fd == -1) {
            System.out.println("Failed to open file.");
            return;
        }

        // 写入文件
        String newData = "Hello, World!";
        fileUtils.write(fd, newData.getBytes(), 0, newData.length());
        System.out.println("Data written successfully.");

        // 关闭文件描述符
        fileUtils.close(fd);

        // 读取文件
        byte[] data = fileUtils.readFile(fd);
        if (data != null) {
            String content = new String(data);
            System.out.println("File content: " + content);
        }
    }
}
