package com.example.springcourse.config.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Person {
    private int id;
    @Min(value = 0, message = "Age must be greater than 0")
    private int age;
    @NotEmpty(message = "Name must not be empty")
    @Size(min = 2, max = 30, message = "Name size must be between 2 and 30")
    private String name;

    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Email is not well-formed")
    private String email;

    public Person() {
    }

    public Person(int id, int age, String name, String email) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
