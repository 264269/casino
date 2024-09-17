package com.example.casino.lottery.controller.response;

import com.example.casino.lottery.data.Winner;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WinnerListResponse {
    @JsonProperty(value = "winners", required = true)
    List<WinnerResponse> winnerList;

    public WinnerListResponse() {
    }

    public WinnerListResponse(List<WinnerResponse> winnerList) {
        this.winnerList = winnerList;
    }

    public List<WinnerResponse> getWinnerList() {
        return winnerList;
    }

    public void setWinnerList(List<WinnerResponse> winnerList) {
        this.winnerList = winnerList;
    }

    @Override
    public String toString() {
        return "WinnerListResponse{" +
                "winnerList=" + winnerList +
                '}';
    }
}
