package com.engeto;

public class Resource {

    private boolean isInUse = false;
    private int usedById = -1;

    @Override
    public String toString() {
        return "Resource{" +
                "isInUse=" + isInUse +
                ", usedById=" + usedById +
                '}';
    }

    public synchronized boolean askAndLockResource(int id){
        if(!isInUse){
            this.isInUse = true;
            this.usedById = id;
            return true;
        } else
            return false;
    }

    public synchronized void releaseResource(int id){
        if(usedById == id){
            this.isInUse = false;
            this.usedById = -1;
        }
    }

    public boolean isInUse() {
        return isInUse;
    }

    public int getUsedById() {
        return usedById;
    }
}
