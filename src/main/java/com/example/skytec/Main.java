package com.example.skytec;

import com.example.skytec.config.ConnectionManager;
import com.example.skytec.entity.Clan;
import com.example.skytec.service.ClanService;
import com.example.skytec.service.DonateService;
import com.example.skytec.service.TaskService;

import java.sql.Driver;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Clan name = new Clan(1L, "name", 0);
        ClanService.getInstance().addClan(name);
        for (int i = 0; i < 100; i++) {
            new Thread(()-> TaskService.getInstance().completeTask(1L,1L,100)).start();
            new Thread(()-> DonateService.getInstance().addGoldToClan(1L,1L,100)).start();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(ClanService.getInstance().get(1L).getGold());
    }
}
