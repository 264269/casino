package com.example.casino.lottery.service;

import com.example.casino.lottery.controller.response.ParticipantListResponse;
import com.example.casino.lottery.controller.response.WinnerListResponse;
import com.example.casino.lottery.controller.response.WinnerResponse;
import com.example.casino.lottery.data.Participant;

public interface CasinoService {
    void addParticipant(Participant Participant);
    ParticipantListResponse getParticipantList();
    WinnerResponse getWinner();
    WinnerListResponse getWinnerList();
}
