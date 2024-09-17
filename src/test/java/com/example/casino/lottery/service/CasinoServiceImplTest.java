package com.example.casino.lottery.service;

import com.example.casino.lottery.controller.response.WinnerResponse;
import com.example.casino.lottery.data.Participant;
import com.example.casino.lottery.data.ParticipantRepository;
import com.example.casino.lottery.data.Winner;
import com.example.casino.lottery.data.WinnerRepository;
import com.example.casino.random.service.RandomService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CasinoServiceImplTest {

    @Mock
    private ParticipantRepository participantRepository;

    @Mock
    private WinnerRepository winnerRepository;

    @Mock
    private RandomService randomService;

    @InjectMocks
    private CasinoServiceImpl casinoService;

//    private List<Participant> participantList;

    @BeforeEach
    void setUp() {
//        participantList = new ArrayList<>();
//        participantList.add(new Participant("Oleg", 25, "Moscow"));
//        participantList.add(new Participant("Lola", 22, "Saint-Petersburg"));
//        participantList.add(new Participant("Vanya", 34, "Krasnoyarsk"));
//        participantRepository.saveAll(participantList);
    }

    @AfterEach
    void resetRepository() {
        participantRepository.deleteAll();
    }

    @Test
    void addParticipant_savesParticipant() {
        Participant participant = new Participant("Anna", 18, "Kongo");
        casinoService.addParticipant(participant);
        Mockito.verify(participantRepository, Mockito.times(1)).save(participant);
    }

    @Test
    void getParticipantListResponse_returnParticipantListResponse() {
        // should it be tested? there's no server logic
        List<Participant> participantList = new ArrayList<>();
        participantList.add(new Participant("Oleg", 25, "Moscow"));
        participantList.add(new Participant("Lola", 22, "Saint-Petersburg"));
        participantList.add(new Participant("Vanya", 34, "Krasnoyarsk"));
        participantRepository.saveAll(participantList);
        Assertions.assertIterableEquals(participantList, participantRepository.findAll());
    }

    @Test
    void getWinnerResponse_lessThanTwoParticipants_returnNull() {
        Participant p1 = new Participant("Oleg", 25, "Moscow");
        Mockito.when(participantRepository.findAll()).thenReturn(List.of(p1));

        WinnerResponse winner = casinoService.getWinnerResponse();
        Assertions.assertNull(winner);
    }

    @Test
    void getWinnerResponse_twoOrMoreParticipants_returnAnyWinner() {
        Participant p1 = new Participant("Oleg", 25, "Moscow");
        Participant p2 = new Participant("Anna", 18, "Kongo");
        List<Participant> participantList = List.of(p1, p2);
        Mockito.when(participantRepository.findAll()).thenReturn(participantList);

        int i = 0;
        int prize = 500;
        Mockito.when(randomService.getOneRandomInt(0, participantList.size() - 1)).thenReturn(i);
        Mockito.when(randomService.getOneRandomInt(1, 1000)).thenReturn(prize);

        WinnerResponse actualWinner = casinoService.getWinnerResponse();
        WinnerResponse expectedWinner = new WinnerResponse(participantList.get(i), prize);
        Assertions.assertEquals(actualWinner, expectedWinner);
    }

    @Test
    void getWinnerListResponse() {
        List<WinnerResponse> winnerResponseList = new ArrayList<>();

        Participant p1 = new Participant("Oleg", 25, "Moscow");
        Winner w1 = new Winner(p1, 250);
        winnerResponseList.add(new WinnerResponse(p1, w1));

        Participant p2 = new Participant("Lola", 22, "Saint-Petersburg");
        Winner w2 = new Winner(p2, 500);
        winnerResponseList.add(new WinnerResponse(p2, w2));

        Participant p3 = new Participant("Vanya", 34, "Krasnoyarsk");
        Winner w3 = new Winner(p3, 750);
        winnerResponseList.add(new WinnerResponse(p3, w3));

//        Winner w4 = new Winner(p2, 1000);
//        winnerResponseList.add(new WinnerResponse(p2, w4));

        Mockito.when(winnerRepository.findAll()).thenReturn(List.of(w1, w2, w3));
        Mockito.when(participantRepository.findAll()).thenReturn(List.of(p1, p2, p3));

        Assertions.assertIterableEquals(winnerResponseList, casinoService.getWinnerListResponse().getWinnerList());
    }
}
