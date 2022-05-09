package com.engeto;

public class Resource {

    private Integer jobId = null;

    @Override
    public String toString() {
        return "Resource{" +
                "jobId=" + jobId +
                '}';
    }

    public synchronized boolean askAndLock(Integer jobId) {
        if (this.jobId == null) {
            this.jobId = jobId;
            return true;
        } else
            return false;
    }

    public synchronized void release(Integer jobId) {
        if (this.jobId != null)
            if (this.jobId.equals(jobId))
                this.jobId = null;
    }

    public Integer getJobId() {
        return jobId;
    }

}
