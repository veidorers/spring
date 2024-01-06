package com.example.model;

public class Person {
    private Integer id;
    private String fio;
    private int birthYear;

    public Person() {
    }

    public Person(Integer id, String fio, int birthYear) {
        this.id = id;
        this.fio = fio;
        this.birthYear = birthYear;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
}
