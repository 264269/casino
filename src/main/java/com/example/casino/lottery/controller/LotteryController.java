package com.example.casino.lottery.controller;

import com.example.casino.lottery.controller.response.ParticipantListResponse;
import com.example.casino.lottery.controller.response.WinnerListResponse;
import com.example.casino.lottery.controller.response.WinnerResponse;
import com.example.casino.lottery.data.Participant;
import com.example.casino.lottery.service.CasinoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(path = "/lottery")
public class LotteryController {
    private CasinoService casinoService;

    Logger logger = LoggerFactory.getLogger(LotteryController.class);

    public LotteryController(CasinoService casinoService) {
        this.casinoService = casinoService;
    }

    @PostMapping("/participant")
    public ResponseEntity<Void> registerParticipant(@Valid @RequestBody Participant participant) {
        casinoService.addParticipant(participant);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //done
    @GetMapping("/participant")
    public ResponseEntity<ParticipantListResponse> getParticipants() {
        ParticipantListResponse participantList = casinoService.getParticipantListResponse();
        if (participantList == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(participantList, HttpStatus.OK);
    }

    //done
    @GetMapping("/start")
    public ResponseEntity<WinnerResponse> getWinner() {
        WinnerResponse winner = casinoService.getWinnerResponse();
        if (winner == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(winner, HttpStatus.OK);
    }

    @GetMapping("/winners")
    public ResponseEntity<WinnerListResponse> getWinners() {
        WinnerListResponse winnerList = casinoService.getWinnerListResponse();
        if (winnerList == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(winnerList, HttpStatus.OK);
    }
}
