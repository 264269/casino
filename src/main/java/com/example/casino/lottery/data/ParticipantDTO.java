package com.example.casino.lottery.data;

public record ParticipantDTO(String name, int age, String city) {
    public ParticipantDTO(Participant participant) {
        this(participant.getName(), participant.getAge(), participant.getCity());
    }
}
