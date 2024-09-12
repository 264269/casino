package com.example.casino;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WinnerResponse {
    @JsonProperty(value = "winner", required = true)
    ParticipantDTO participant;

    @JsonProperty(value = "prize", required = true)
    int prize;

    public WinnerResponse() { }

    public ParticipantDTO getParticipant() {
        return participant;
    }

    public void setParticipant(ParticipantDTO participant) {
        this.participant = participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = new ParticipantDTO(participant);
    }

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }
}
