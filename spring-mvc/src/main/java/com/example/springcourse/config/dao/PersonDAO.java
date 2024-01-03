package com.example.springcourse.config.dao;

import com.example.springcourse.config.model.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private List<Person> people;
    private static int PEOPLE_COUNT = 0;

    {
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT, 25, "Alex", "alex@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT, 35, "Tom", "tom@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT, 11, "Bob", "bob@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT, 20, "Michael", "michael@gmail.com"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person updatedPerson) {
        var personToUpdate = show(id);
        personToUpdate.setName(updatedPerson.getName());
        personToUpdate.setEmail(updatedPerson.getEmail());
        personToUpdate.setAge(updatedPerson.getAge());
    }

    public void delete(int id) {
        people.removeIf(p -> p.getId() == id);
    }
}
