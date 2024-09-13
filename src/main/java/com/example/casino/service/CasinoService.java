package com.example.casino.service;

import com.example.casino.response.ParticipantListResponse;
import com.example.casino.response.WinnerListResponse;
import com.example.casino.response.WinnerResponse;
import org.springframework.http.ResponseEntity;

public interface CasinoService {
    void addParticipant(String name, int age, String city);
    ResponseEntity<ParticipantListResponse> getParticipantList();
    ResponseEntity<WinnerResponse> gamba();
    ResponseEntity<WinnerListResponse> getWinnerList();
}
