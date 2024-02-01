package com.example.skytec.service;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Lock extends ReentrantReadWriteLock {
    private static Lock instance;

    private Lock() {
    }

    public static Lock getInstance() {
        if (instance == null) {
            synchronized (Lock.class) {
                if (instance == null) {
                    instance = new Lock();
                }
            }
        }
        return instance;
    }
}
