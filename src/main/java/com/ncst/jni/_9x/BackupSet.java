package com.ncst.jni._9x;

public class BackupSet {
    private String clientId;
    private String backupChainDir;
    private String backupSetDir;
    private int copyId;
    private StorageUnit storageUnit;

    public BackupSet setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public BackupSet setBackupChainDir(String backupChainDir) {
        this.backupChainDir = backupChainDir;
        return this;
    }

    public BackupSet setBackupSetDir(String backupSetDir) {
        this.backupSetDir = backupSetDir;
        return this;
    }

    public BackupSet setCopyId(int copyId) {
        this.copyId = copyId;
        return this;
    }

    public StorageUnit getStorageUnit() {
        if (storageUnit == null) {
            storageUnit = new StorageUnit();
        }
        return storageUnit;
    }
}
