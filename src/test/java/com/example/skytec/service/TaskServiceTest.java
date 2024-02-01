package com.example.skytec.service;

import com.example.skytec.entity.Clan;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    @Test
    void testCompleteTaskAndDonateInMultithreading() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(()-> ClanService.getInstance().addClan(new Clan(1L,"New Clan",0)));

        for (int i = 0; i < 1000; i++) {
            executorService.submit(()-> TaskService.getInstance().completeTask(1L,1L,100));
            executorService.submit(()-> DonateService.getInstance().addGoldToClan(1L,1L,100));
        }
        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
            executorService.shutdown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(200_000, ClanService.getInstance().get(1L).getGold());
    }
}