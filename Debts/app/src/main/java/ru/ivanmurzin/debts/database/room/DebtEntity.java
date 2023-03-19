package ru.ivanmurzin.debts.database.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class DebtEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public int money;

    public DebtEntity(String name, int money) {
        this.name = name;
        this.money = money;
    }
    public void setMoney(int money) {
        this.money = money;
    }

    public void setName(String name) {
        this.name = name;
    }
}
