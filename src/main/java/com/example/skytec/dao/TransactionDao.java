package com.example.skytec.dao;


import com.example.skytec.config.ConnectionManager;
import com.example.skytec.entity.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionDao {

    private static TransactionDao instance;
    private static final String DELETE_SQL = """ 
            DELETE FROM TRANSACTIONS WHERE ID = ?
            """;
    private static final String SAVE_SQL = """ 
            INSERT INTO TRANSACTIONS(initiator_id, clan_id, action_type, old_gold, new_gold)
            VALUES ( ?,?,?,?,? ); 
            """;


    private TransactionDao() {

    }

    public Transaction save(Transaction transaction) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, transaction.getInitiatorId());
            preparedStatement.setLong(2, transaction.getClanId());
            preparedStatement.setString(3, transaction.getActionType().name());
            preparedStatement.setLong(4, transaction.getOldGold());
            preparedStatement.setLong(5, transaction.getNewGold());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                transaction.setId(generatedKeys.getLong(1));
                transaction.setCreatedAt(generatedKeys.getTimestamp("created_at").toLocalDateTime());
            }

            return transaction;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(Long id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static TransactionDao getInstance() {
        if (instance == null) {
            synchronized (TransactionDao.class) {
                if (instance == null) {
                    instance = new TransactionDao();
                }
            }
        }
        return instance;
    }
}
