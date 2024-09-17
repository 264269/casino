package com.example.casino.lottery.data;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

public class Participant {
    @Id
    private String id;
    @NotNull
    private String name;
    @NotNull
    private int age;
    @NotNull
    private String city;

    public Participant() {
    }

    public Participant(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
