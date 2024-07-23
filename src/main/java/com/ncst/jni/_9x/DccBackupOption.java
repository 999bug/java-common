package com.ncst.jni._9x;

/**
 * @author SERVER
 */
public class DccBackupOption {
    private String jobId;
    private BackupSet backupSet;

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobId() {
        return jobId;
    }

    public BackupSet getBackupSet() {
        if (backupSet == null) {
            backupSet = new BackupSet();
        }
        return backupSet;
    }
}
