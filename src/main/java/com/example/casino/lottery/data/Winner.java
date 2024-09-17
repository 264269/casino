package com.example.casino.lottery.data;

import org.bson.codecs.pojo.IdGenerators;
import org.springframework.data.annotation.Id;

import java.util.Objects;

public class Winner {
    @Id
    String id = IdGenerators.STRING_ID_GENERATOR.generate();
    String participantId;
    int prize;

    public Winner() {
    }

    public Winner(String participantId, int prize) {
        this.participantId = participantId;
        this.prize = prize;
    }

    public Winner(Participant participant, int prize) {
        this(participant.getId(), prize);
    }

    public String getParticipantId() {
        return participantId;
    }

    public void setParticipantId(String participantId) {
        this.participantId = participantId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    @Override
    public String toString() {
        return "Winner{" +
                "id='" + id + '\'' +
                ", participantId='" + participantId + '\'' +
                ", prize=" + prize +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Winner winner = (Winner) o;
        return prize == winner.prize && Objects.equals(id, winner.id) && Objects.equals(participantId, winner.participantId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(participantId);
        result = 31 * result + prize;
        return result;
    }
}
