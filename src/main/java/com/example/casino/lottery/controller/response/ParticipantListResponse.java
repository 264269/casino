package com.example.casino.lottery.controller.response;

import com.example.casino.lottery.data.Participant;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ParticipantListResponse {
    @JsonProperty(value = "participants", required = true)
    List<Participant> participantList;

    public ParticipantListResponse() {
    }

    public ParticipantListResponse(List<Participant> participantList) {
        this.participantList = participantList;
    }

    public List<Participant> getParticipantList() {
        return participantList;
    }

    public void setParticipantList(List<Participant> participantList) {
        this.participantList = participantList;
    }

    @Override
    public String toString() {
        return "ParticipantListResponse{" +
                "participantList=" + participantList +
                '}';
    }
}