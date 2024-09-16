package com.example.casino.lottery.data;

import org.springframework.data.annotation.Id;

public class Winner {
    @Id
    String id;
    String participantId;
    int prize;

    public Winner() {
    }

    public Winner(String participantId, int prize) {
        this.participantId = participantId;
        this.prize = prize;
    }

    public String getParticipantId() {
        return participantId;
    }

    public void setParticipantId(String participantId) {
        this.participantId = participantId;
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
                ", participantId='" + participantId + '\'' +
                ", prize=" + prize +
                '}';
    }
}
