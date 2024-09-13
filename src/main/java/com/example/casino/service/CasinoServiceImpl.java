package com.example.casino.service;

import com.example.casino.data.*;
import com.example.casino.response.ParticipantListResponse;
import com.example.casino.response.WinnerListResponse;
import com.example.casino.response.WinnerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class CasinoServiceImpl implements CasinoService {
    @Autowired
    ParticipantRepository participantRepository;
    @Autowired
    WinnerRepository winnerRepository;

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
        int prize = getCasinoInteger();
        int participantIndex = getCasinoWinner(participants.size());
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
        List<Participant> participantList
                = participantRepository.findAllById(
                        winnerList.stream().map(Winner::getId).toList()
        );

        for (Winner w : winnerList) {
            System.out.println(w.getId());
        }

        for (Participant p : participantList) {
            System.out.println(p.getId());
        }

        //create winner list
        List<WinnerDTO> winnerDTOList = new ArrayList<>();
        for (Participant p : participantList) {
            Winner winner = winnerList.stream().filter(x -> x.getId().equals(p.getId())).toList().get(0);
            winnerDTOList.add(
                    new WinnerDTO(
                            p,
                            winner.getPrize()
                    )
            );
        }
//        for (Participant p : participantList) {
//            winnerDTOList.add(
//                    new WinnerDTO(
//                            p,
//                            winnerList.stream().filter(x -> x.getId() == p.getId()).toList().get(0).getPrize()
//                    )
//            );
//        }

        //create response
        WinnerListResponse winnerListResponse = new WinnerListResponse(winnerDTOList);

        return new ResponseEntity<>(winnerListResponse, HttpStatus.OK);
    }

    public int getCasinoWinner(int participants) {
        return getRandomDecimalInteger(1, 0, participants - 1);
    }

    public int getCasinoInteger() {
        return getRandomDecimalInteger(1, 1, 1000);
    }

    public int getRandomDecimalInteger(
            int num
            ,int min
            ,int max
        ) {
        Integer result;
        try {
            RestClient restClient = RestClient.create();
            String uriTemplate = "https://www.random.org/integers/" +
                    "?num=%s" +
                    "&min=%s" +
                    "&max=%s" +
                    "&col=%s" +
                    "&base=%s" +
                    "&format=%s" +
                    "&rnd=%s";
            String uriResult = String.format(uriTemplate, num, min, max, 1, 10, "plain", "new");
            URI uri = new URI(uriResult);
            String requestResult =restClient.get().uri(uri).retrieve().body(String.class).trim();
            result = Integer.parseInt(requestResult);
        } catch (Exception e) {
            System.out.printf(e.getMessage());
            result = min + (int) (Math.random() * (max - min));
        }
        return result;
    }
}
