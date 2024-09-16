package com.example.casino.lottery.response;

import com.example.casino.lottery.data.Participant;
import com.example.casino.lottery.data.ParticipantDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WinnerResponse {
    @JsonProperty(value = "winner")
    ParticipantDTO participant;

    int prize;

    public WinnerResponse() { }

    public WinnerResponse(ParticipantDTO participant, int prize) {
        this.participant = participant;
        this.prize = prize;
    }

    public WinnerResponse(Participant participant, int prize) {
        this(new ParticipantDTO(participant), prize);
    }

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
