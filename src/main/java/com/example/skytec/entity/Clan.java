package com.example.skytec.entity;

import java.util.Objects;

public class Clan {
    private long id;
    private String name;
    private long gold;

    public Clan(long id, String name, long gold) {
        this.id = id;
        this.name = name;
        this.gold = gold;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Clan{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", gold=" + gold +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clan clan = (Clan) o;
        return id == clan.id && gold == clan.gold && Objects.equals(name, clan.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gold);
    }

    public long getGold() {
        return gold;
    }

    public void setGold(long gold) {
        this.gold = gold;
    }
}
