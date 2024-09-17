package com.example.casino.lottery.data;

import jakarta.validation.constraints.NotNull;
import org.bson.codecs.IdGenerator;
import org.bson.codecs.pojo.IdGenerators;
import org.springframework.data.annotation.Id;

import java.util.Objects;

public class Participant {
    @Id
    private String id = IdGenerators.STRING_ID_GENERATOR.generate();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Participant that = (Participant) o;
        return age == that.age && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + age;
        result = 31 * result + Objects.hashCode(city);
        return result;
    }
}
