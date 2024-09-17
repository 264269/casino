package com.example.casino.lottery.service;

import com.example.casino.lottery.data.*;
import com.example.casino.lottery.controller.response.ParticipantListResponse;
import com.example.casino.lottery.controller.response.WinnerListResponse;
import com.example.casino.lottery.controller.response.WinnerResponse;
import com.example.casino.random.service.RandomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class  CasinoServiceImpl implements CasinoService {
    @Autowired
    ParticipantRepository participantRepository;
    @Autowired
    WinnerRepository winnerRepository;
    @Autowired
    RandomService randomService;

    Logger logger = LoggerFactory.getLogger(CasinoServiceImpl.class);


    @Override
    public void addParticipant(Participant participant) {
        //save participant
        participantRepository.save(participant);
    }

    @Override
    public ParticipantListResponse getParticipantListResponse() {
        //get all
        List<Participant> participantList = participantRepository.findAll();

        //create response
        ParticipantListResponse response = new ParticipantListResponse(participantList);

        return response;
    }

    @Override
    public WinnerResponse getWinnerResponse() {
        //check length
        List<Participant> participantList = participantRepository.findAll();
        if (participantList.size() < 2) {
            return null;
        }

        //randomize prize & winner
        int prize = randomService.getOneRandomInt(1, 1000);
        int participantIndex = randomService.getOneRandomInt(0, participantList.size() - 1);
        Participant participantWinner = participantList.get(participantIndex);

        //create response
        WinnerResponse response = new WinnerResponse(participantWinner, prize);

        winnerRepository.save(new Winner(participantWinner.getId(), prize));

        return response;
    }

    @Override
    public WinnerListResponse getWinnerListResponse() {
        //get winners (id + prize)
        List<Winner> winnerList = winnerRepository.findAll();

        //get participants info
        Map<String, Participant> participantMap =
                participantRepository
                        .findAllById(
                                winnerList
                                        .stream()
                                        .map(Winner::getParticipantId)
                                        .toList()
                        )
                        .stream()
                        .collect(Collectors.toMap(Participant::getId, p -> p));

//        create winner list
        List<WinnerResponse> winnerResponseList
                = winnerList
                .stream()
                .map(w -> {
                    Participant p = participantMap.get(w.getParticipantId());
                    return new WinnerResponse(p, w.getPrize());
                }).toList();

//        create response
        WinnerListResponse response = new WinnerListResponse(winnerResponseList);

//        return new ResponseEntity<>(winnerListResponse, HttpStatus.OK);
        return response;
    }
}
