package com.example.skytec.service;

import com.example.skytec.dao.TransactionDao;
import com.example.skytec.entity.ActionType;
import com.example.skytec.entity.Clan;
import com.example.skytec.entity.Transaction;

import java.util.concurrent.locks.ReadWriteLock;

public class TaskService {
    private static TaskService instance;
    private ReadWriteLock lock = Lock.getInstance();
    private TransactionDao transactionDao = TransactionDao.getInstance();

    private TaskService() {
    }

    public static TaskService getInstance() {
        if (instance == null) {
            synchronized (TaskService.class) {
                if (instance == null) {
                    instance = new TaskService();
                }
            }
        }
        return instance;
    }

    public void completeTask(long clanId, long taskId, int gold) {
        Clan clan = ClanService.getInstance().get(clanId);
        if (clan != null) {
            lock.writeLock().lock();
            try {
                long currentGold = clan.getGold();
                long newGold = currentGold + gold;
                clan.setGold(newGold);

                transactionDao.save(Transaction.builder()
                        .clanId(clanId)
                        .initiatorId(taskId)
                        .oldGold(currentGold)
                        .newGold(newGold)
                        .actionType(ActionType.TASK_COMPLETE)
                        .build());

                ClanService.getInstance().addClan(clan);
            } finally {
                lock.writeLock().unlock();
            }
        }
    }
}
