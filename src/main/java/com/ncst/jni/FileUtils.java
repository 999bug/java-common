package com.ncst.jni;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 * @author Lisy
 */
@Slf4j
public class FileUtils {

    static {
        // 设置库路径
        System.setProperty("java.library.path", "D:\\code\\jam\\java-common\\java-common\\src\\main\\resources\\lib");
        // 重新加载库路径
        reloadLibrary();
        // 加载本地库
        System.loadLibrary("fileutils");
    }

    // 本地方法：打开文件
    public native int open(String filePath);

    // 本地方法：读取文件
    public native byte[] readFile(int fd);

    // 本地方法：写入文件
    public native void write(int fd, byte[] data, int off, int len);

    // 本地方法：关闭文件
    public native void close(int fd);

    private static void reloadLibrary() {
        try {
            // 获取 sys_paths 字段
            Field sysPathsField = ClassLoader.class.getDeclaredField("sys_paths");
            sysPathsField.setAccessible(true);

            // 设置 sys_paths 为 null
            sysPathsField.set(null, null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            log.warn(e.getMessage(), e);
        }
    }
}
