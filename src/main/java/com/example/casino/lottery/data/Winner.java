package com.example.casino.lottery.data;

import org.springframework.data.annotation.Id;

public class Winner {
    @Id
    String id;
    int prize;

    public Winner() {
    }

    public Winner(String id, int prize) {
        this.id = id;
        this.prize = prize;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    @Override
    public String toString() {
        return "Winner{" +
                "id='" + id + '\'' +
                ", prize=" + prize +
                '}';
    }
}
