package com.example.skytec.service;

import com.example.skytec.dao.TransactionDao;
import com.example.skytec.entity.ActionType;
import com.example.skytec.entity.Clan;
import com.example.skytec.entity.Transaction;

import java.util.concurrent.locks.ReadWriteLock;

public class DonateService {

    private volatile static DonateService instance;
    private ReadWriteLock lock = Lock.getInstance();
    private TransactionDao transactionDao = TransactionDao.getInstance();

    private DonateService() {
    }

    public static DonateService getInstance() {
        if (instance == null) {
            synchronized (DonateService.class) {
                if (instance == null) {
                    instance = new DonateService();
                }
            }
        }
        return instance;
    }

    public void addGoldToClan(long userId, long clanId, int gold) {
        Clan clan = ClanService.getInstance().get(clanId);
        if (clan != null) {
            lock.writeLock().lock();
            try {
                long currentGold = clan.getGold();
                long newGold = currentGold + gold;
                clan.setGold(newGold);

                transactionDao.save(Transaction.builder()
                                .clanId(clanId)
                                .initiatorId(userId)
                                .oldGold(currentGold)
                                .newGold(newGold)
                                .actionType(ActionType.DONATE)
                        .build());

                ClanService.getInstance().addClan(clan);
            } finally {
                lock.writeLock().unlock();
            }
        }
    }
}
