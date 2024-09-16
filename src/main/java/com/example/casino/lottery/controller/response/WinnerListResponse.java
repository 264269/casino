package com.example.casino.lottery.controller.response;

import com.example.casino.lottery.controller.WinnerDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WinnerListResponse {
    @JsonProperty(value = "winners", required = true)
    List<WinnerDTO> winnerList;

    public WinnerListResponse() {
    }

    public WinnerListResponse(List<WinnerDTO> winnerList) {
        this.winnerList = winnerList;
    }

    public List<WinnerDTO> getWinnerList() {
        return winnerList;
    }

    public void setWinnerList(List<WinnerDTO> winnerList) {
        this.winnerList = winnerList;
    }
}
