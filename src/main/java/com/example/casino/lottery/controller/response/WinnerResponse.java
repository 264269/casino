package com.example.casino.lottery.controller.response;

import com.example.casino.lottery.data.Participant;
import com.example.casino.lottery.data.Winner;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

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

    public WinnerResponse(Participant participant, Winner winner) {
        this(participant, winner.getPrize());
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WinnerResponse that = (WinnerResponse) o;
        return prize == that.prize && Objects.equals(participant, that.participant);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(participant);
        result = 31 * result + prize;
        return result;
    }
}
