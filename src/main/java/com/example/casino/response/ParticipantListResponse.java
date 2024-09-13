package com.example.casino.response;

import com.example.casino.data.ParticipantDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ParticipantListResponse {

    @JsonProperty(value = "participants", required = true)
    List<ParticipantDTO> participantList;

    public ParticipantListResponse() {
    }

    public ParticipantListResponse(List<ParticipantDTO> participantList) {
        this.participantList = participantList;
    }

    public List<ParticipantDTO> getParticipantList() {
        return participantList;
    }

    public void setParticipantList(List<ParticipantDTO> participantList) {
        this.participantList = participantList;
    }
}