package com.example.casino.controller;

import com.example.casino.request.AddParticipantRequest;
import com.example.casino.response.ParticipantListResponse;
import com.example.casino.response.WinnerListResponse;
import com.example.casino.response.WinnerResponse;
import com.example.casino.service.CasinoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LotteryController {
    private CasinoService casinoService;

    public LotteryController(CasinoService casinoService) {
        this.casinoService = casinoService;
    }

    @PostMapping("/lottery/participant")
    public void registerParticipant(@RequestBody AddParticipantRequest request) {
        casinoService.addParticipant(
                request.getName(),
                request.getAge(),
                request.getCity()
        );
    }

    //done
    @GetMapping("/lottery/participant")
    public ResponseEntity<ParticipantListResponse> getParticipants() {
        return casinoService.getParticipantList();
    }

    //done
    @GetMapping("/lottery/start")
    public ResponseEntity<WinnerResponse> gamba() {
        return casinoService.gamba();
    }

    @GetMapping("/lottery/winners")
    public ResponseEntity<WinnerListResponse> getWinners() {
        return casinoService.getWinnerList();
    }
}
