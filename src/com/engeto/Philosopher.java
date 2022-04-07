package com.engeto;

import java.util.concurrent.atomic.AtomicLong;

public class Philosopher implements Runnable {

    private static final AtomicLong countAllTries = new AtomicLong(0);
    private static final AtomicLong countAllSuccessfulTries = new AtomicLong(0);

    private final int id;
    private Resource leftResource;
    private Resource rightResource;
    private int mealPortion;
    private int countTries = 0;


    public Philosopher(int id, int mealPortion, Resource leftResource, Resource rightResource) {
        this.id = id;
        this.mealPortion = mealPortion;
        this.leftResource = leftResource;
        this.rightResource = rightResource;
    }

    @Override
    public void run() {

        long startTime = System.currentTimeMillis();

        while (mealPortion > 0) {

            countTries++;
            countAllTries.incrementAndGet();

            if (leftResource.askAndLockResource(id) && rightResource.askAndLockResource(id)) {

                if (leftResource.getUsedById() != id || !leftResource.isInUse() || rightResource.getUsedById() != id || !rightResource.isInUse())
                    throw new RuntimeException("Resources are not allocated properly!!!");

                mealPortion--;
                countAllSuccessfulTries.incrementAndGet();
            }

            leftResource.releaseResource(id);
            rightResource.releaseResource(id);
        }

        System.out.println("DONE - Philosopher #" + id + " finished his/her meal! Plate check: " + mealPortion
                + ", eating tries: " + countTries + "\n\tDinner finished in: "
                + (System.currentTimeMillis() - startTime) + " msec, thread: " + Thread.currentThread());
    }


    public static long getAllTries() {
        return countAllTries.longValue();
    }

    public static long getAllSuccessfulTries() {
        return countAllSuccessfulTries.longValue();
    }


}
