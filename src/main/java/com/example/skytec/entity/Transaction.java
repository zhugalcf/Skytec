package com.example.skytec.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction {

    private Long id;
    private Long initiatorId;
    private Long clanId;
    private ActionType actionType;
    private Long oldGold;
    private Long newGold;
    private LocalDateTime createdAt;

    public Transaction(Long id, Long initiatorId, Long clanId, ActionType actionType,
                       Long oldGold, Long newGold, LocalDateTime createdAt) {
        this.id = id;
        this.initiatorId = initiatorId;
        this.clanId = clanId;
        this.actionType = actionType;
        this.oldGold = oldGold;
        this.newGold = newGold;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInitiatorId() {
        return initiatorId;
    }

    public void setInitiatorId(Long initiatorId) {
        this.initiatorId = initiatorId;
    }

    public Long getClanId() {
        return clanId;
    }

    public void setClanId(Long clanId) {
        this.clanId = clanId;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public Long getOldGold() {
        return oldGold;
    }

    public void setOldGold(Long oldGold) {
        this.oldGold = oldGold;
    }

    public Long getNewGold() {
        return newGold;
    }

    public void setNewGold(Long newGold) {
        this.newGold = newGold;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Transaction() {
    }

    public static TransactionBuilder builder() {
        return new TransactionBuilder();
    }

    public static class TransactionBuilder {
        private Long id;
        private Long initiatorId;
        private Long clanId;
        private ActionType actionType;
        private Long oldGold;
        private Long newGold;
        private LocalDateTime createdAt;

        public TransactionBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public TransactionBuilder initiatorId(Long initiatorId) {
            this.initiatorId = initiatorId;
            return this;
        }

        public TransactionBuilder clanId(Long clanId) {
            this.clanId = clanId;
            return this;
        }

        public TransactionBuilder actionType(ActionType actionType) {
            this.actionType = actionType;
            return this;
        }

        public TransactionBuilder oldGold(Long oldGold) {
            this.oldGold = oldGold;
            return this;
        }

        public TransactionBuilder newGold(Long newGold) {
            this.newGold = newGold;
            return this;
        }

        public TransactionBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Transaction build() {
            return new Transaction(id, initiatorId, clanId, actionType, oldGold, newGold, createdAt);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) && Objects.equals(initiatorId, that.initiatorId) && Objects.equals(clanId, that.clanId) && actionType == that.actionType && Objects.equals(oldGold, that.oldGold) && Objects.equals(newGold, that.newGold) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, initiatorId, clanId, actionType, oldGold, newGold, createdAt);
    }
}
