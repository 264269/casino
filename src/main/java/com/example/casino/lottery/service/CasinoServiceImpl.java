package com.example.casino.lottery.service;

import com.example.casino.lottery.data.*;
import com.example.casino.lottery.response.ParticipantListResponse;
import com.example.casino.lottery.response.WinnerListResponse;
import com.example.casino.lottery.response.WinnerResponse;
import com.example.casino.random.service.RandomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CasinoServiceImpl implements CasinoService {
    @Autowired
    ParticipantRepository participantRepository;
    @Autowired
    WinnerRepository winnerRepository;
    @Autowired
    RandomService randomService;

    Logger logger = LoggerFactory.getLogger(CasinoServiceImpl.class);


    @Override
    public void addParticipant(String name, int age, String city) {
        //save participant
        participantRepository.save(new Participant(name, age, city));
    }

    @Override
    public ResponseEntity<ParticipantListResponse> getParticipantList() {
        //get all
        List<Participant> participants = participantRepository.findAll();

        //un_id all records (useless??)
        List<ParticipantDTO> participantsInfo = participants.stream().map(ParticipantDTO::new).toList();

        //create response
        ParticipantListResponse participantListResponse = new ParticipantListResponse(participantsInfo);

        return new ResponseEntity<>(participantListResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<WinnerResponse> gamba() {
        //check length
        List<Participant> participants = participantRepository.findAll();
        if (participants.size() < 2) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        //randomize prize & winner
        int prize = randomService.getOneRandomInt(1, 1000);
        int participantIndex = randomService.getOneRandomInt(0, participants.size() - 1);
        Participant winner = participants.get(participantIndex);

        //create response
        WinnerResponse winnerResponse = new WinnerResponse(winner, prize);

        winnerRepository.save(new Winner(winner.getId(), prize));

//        repository.deleteAll();

        return new ResponseEntity<>(winnerResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<WinnerListResponse> getWinnerList() {
        //get winners (id + prize)
        List<Winner> winnerList = winnerRepository.findAll();

        //get participants info
        Map<String, Participant> participantList =
                participantRepository
                        .findAllById(
                                winnerList
                                        .stream()
                                        .map(Winner::getId)
                                        .toList()
                        )
                        .stream()
                        .collect(Collectors.toMap(Participant::getId, p -> p));

//        logger.debug(
//                winnerList
//                        .stream()
//                        .map(p -> p.toString())
//                        .collect(Collectors.joining("\n", "Showing list of winners:\n", ""))
//        );
//
//        logger.debug(
//                participantList.values()
//                        .stream()
//                        .map(p -> p.toString())
//                        .collect(Collectors.joining("\n", "Showing list of participants:\n", ""))
//        );

//        create winner list
        List<WinnerDTO> winnerDTOList = winnerList
                .stream()
                .map(winner -> {
                    Participant participant = participantList.get(winner.getId());
                    return new WinnerDTO(participant, winner.getPrize());
                }).toList();

//        create response
        WinnerListResponse winnerListResponse = new WinnerListResponse(winnerDTOList);

//        return new ResponseEntity<>(winnerListResponse, HttpStatus.OK);
        return new ResponseEntity<>(winnerListResponse, HttpStatus.OK);
    }

//    public int getOneRandomInt(int min, int max) {
//        Integer result;
//        try {
//            RestClient restClient = RestClient.create();
//            String uriTemplate = "https://www.random.org/integers/" +
//                    "?num=%s" +
//                    "&min=%s" +
//                    "&max=%s" +
//                    "&col=%s" +
//                    "&base=%s" +
//                    "&format=%s" +
//                    "&rnd=%s";
//            String uriResult = String.format(uriTemplate, 1, min, max, 1, 10, "plain", "new");
//            URI uri = new URI(uriResult);
//            String requestResult =restClient.get().uri(uri).retrieve().body(String.class).trim();
//            result = Integer.parseInt(requestResult);
//        } catch (Exception e) {
//            System.out.printf(e.getMessage());
//            result = min + (int) (Math.random() * (max - min));
//        }
//        return result;
//    }
}
