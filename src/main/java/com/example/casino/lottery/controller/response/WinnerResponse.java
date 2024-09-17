package com.example.casino.lottery.controller.response;

import com.example.casino.lottery.data.Participant;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WinnerResponse {
    @JsonProperty(value = "winner")
    Participant participant;
    int prize;

    public WinnerResponse() {
    }

    public WinnerResponse(Participant participant, int prize) {
        this.participant = participant;
        this.prize = prize;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    @Override
    public String toString() {
        return "WinnerResponse{" +
                "participant=" + participant +
                ", prize=" + prize +
                '}';
    }
}
