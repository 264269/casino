package com.example.casino;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ParticipantResponse {

    @JsonProperty(value = "participants", required = true)
    List<ParticipantDTO> participantList;

    public ParticipantResponse() {
    }

    public List<ParticipantDTO> getParticipantList() {
        return participantList;
    }

    public void setParticipantList(List<ParticipantDTO> participantList) {
        this.participantList = participantList;
    }
}