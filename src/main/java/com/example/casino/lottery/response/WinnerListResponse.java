package com.example.casino.lottery.response;

import com.example.casino.lottery.data.WinnerDTO;
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
