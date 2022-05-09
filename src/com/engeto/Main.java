package com.engeto;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static final int NUMBER_OF_PHILOSOPHERS = 10;
    public static final int MEAL_PORTIONS = 1_000_000;

    public static void main(String[] args) {

        List<Resource> resources = new ArrayList<>();
        for (int i = 0;i < NUMBER_OF_PHILOSOPHERS; i++) {
            resources.add(new Resource());
        }

        List<Philosopher> philosophers = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_PHILOSOPHERS; i++) {
                philosophers.add(new Philosopher(i+1, MEAL_PORTIONS, resources.get(i),
                        resources.get((i+1) % NUMBER_OF_PHILOSOPHERS)));
        }


        List<Thread> threadList = new ArrayList<>();
        for (Philosopher philosopher : philosophers) {
            Thread thread = new Thread(philosopher);
            threadList.add(thread);
            thread.start();
        }


        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("======== all " + threadList.size() + " threads finished =================================================================");
        System.out.println("CHECKS - all eating tries: " + Philosopher.getAllTries() + ", all successful eating tries: " + Philosopher.getAllSuccessfulTries()
                +  " (should be " + (NUMBER_OF_PHILOSOPHERS * MEAL_PORTIONS)+")");

    }
}
