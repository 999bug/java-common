package com.ncst.jni._9x;

/**
 * @author Lisy
 */
public class FileUtils2 {

    static {
//        System.setProperty("java.library.path", "D:\\code\\jam\\java-common\\java-common\\src\\main\\resources\\lib");
//        reloadLibrary();
        System.loadLibrary("fileutils");
    }

    public native int open(DccBackupOption dccBackupOption);

    public native byte[] readFile(int fd);

    public native void write(int fd, byte[] data, int off, int len);

    public native void close(int fd);

//    private static void reloadLibrary() {
//        try {
//            Field sysPathsField = ClassLoader.class.getDeclaredField("sys_paths");
//            sysPathsField.setAccessible(true);
//
//            sysPathsField.set(null, null);
//        } catch (NoSuchFieldException | IllegalAccessException e) {
//            log.warn(e.getMessage(), e);
//        }
//    }
}
