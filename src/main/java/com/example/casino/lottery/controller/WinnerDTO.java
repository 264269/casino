package com.example.casino.lottery.controller;

import com.example.casino.lottery.data.Participant;

public record WinnerDTO(String name, int age, String city, int prize) {
    public WinnerDTO(Participant participant, int prize) {
        this(participant.getName(), participant.getAge(), participant.getCity(), prize);
    }
}

//public record WinnerDTO(ParticipantDTO participantDTO, int prize) { }
