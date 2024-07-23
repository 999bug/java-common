package com.ncst.jni._9x;

public class StorageUnit {
    private boolean isNative;
    private String serverIP;
    private int serverPort;
    private String storageUnitPath;

    public StorageUnit setIsNative(boolean isNative) {
        this.isNative = isNative;
        return this;
    }

    public StorageUnit setServerIP(String serverIP) {
        this.serverIP = serverIP;
        return this;
    }

    public StorageUnit setServerPort(int serverPort) {
        this.serverPort = serverPort;
        return this;
    }

    public StorageUnit setStorageUnitPath(String storageUnitPath) {
        this.storageUnitPath = storageUnitPath;
        return this;
    }
}
