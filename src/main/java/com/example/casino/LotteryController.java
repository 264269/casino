package com.example.casino;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LotteryController {

    @Autowired
    private ParticipantRepository repository;

    @PostMapping("/lottery/participant")
    public void registerParticipant(
            @NonNull @RequestParam(value = "name") String name,
            @NonNull @RequestParam(value = "age") int age,
            @NonNull @RequestParam(value = "city") String city) {
        repository.save(new Participant(name, age, city));
    }

    @GetMapping("/lottery/participant")
    public ResponseEntity<ParticipantResponse> getParticipants() {
        //get all
        List<Participant> participants = repository.findAll();
        //un_id all records (useless??)
        List<ParticipantDTO> participantsInfo = participants.stream().map(ParticipantDTO::new).toList();
        ParticipantResponse participantResponse = new ParticipantResponse();
        participantResponse.setParticipantList(participantsInfo);
        return new ResponseEntity<>(participantResponse, HttpStatus.OK);
    }

    @GetMapping("/lottery/start")
    public ResponseEntity<WinnerResponse> gamba() {
        List<Participant> participants = repository.findAll();
        if (participants.size() < 2) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        int prize = (int) (Math.random() * 1000);
        Participant winner = participants.get((int) (Math.random() * participants.size()));
        WinnerResponse winnerResponse = new WinnerResponse();
        winnerResponse.setParticipant(winner);
        winnerResponse.setPrize(prize);

//        repository.deleteAll();

        return new ResponseEntity<>(winnerResponse, HttpStatus.OK);
    }

    public int getRandomFromRandomOrg() {
        return 0;
    }
}
