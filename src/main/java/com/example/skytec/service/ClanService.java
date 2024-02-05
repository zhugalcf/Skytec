package com.example.skytec.service;

import com.example.skytec.entity.Clan;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ClanService {

    private volatile static ClanService instance;

    private ClanService() {
    }

    public static ClanService getInstance() {
        if (instance == null) {
            synchronized (ClanService.class) {
                if (instance == null) {
                    instance = new ClanService();
                }
            }
        }
        return instance;
    }

    private Map<Long, Clan> clanMap = new ConcurrentHashMap<>();

    public Clan get(long clanId) {
        return clanMap.get(clanId);
    }

    public void addClan(Clan clan) {
        clanMap.put(clan.getId(), clan);
    }
}
