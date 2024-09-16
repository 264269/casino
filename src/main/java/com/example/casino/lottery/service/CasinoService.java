package com.example.casino.lottery.service;

import com.example.casino.lottery.controller.response.ParticipantListResponse;
import com.example.casino.lottery.controller.response.WinnerListResponse;
import com.example.casino.lottery.controller.response.WinnerResponse;
import org.springframework.http.ResponseEntity;

public interface CasinoService {
    void addParticipant(String name, int age, String city);
    ResponseEntity<ParticipantListResponse> getParticipantList();
    ResponseEntity<WinnerResponse> gamba();
    ResponseEntity<WinnerListResponse> getWinnerList();
}
