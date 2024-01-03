package com.example.springcourse.config.dao;

import com.example.springcourse.config.model.Person;
import org.postgresql.Driver;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/alishev_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Person> index() {
        List<Person> people = new ArrayList<>();

        try {
            var statement = connection.createStatement();
            String SQL = "SELECT * FROM Person";

            var resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                var person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));

                people.add(person);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return people;
    }

    public Person show(int id) {
        return null;
    }

    public void save(Person person) {
        try {
            var statement = connection.createStatement();
            String SQL = "INSERT INTO Person(id, name, age, email) VALUES (%s, '%s', %s, '%s')"
                    .formatted(person.getId(), person.getName(), person.getAge(), person.getEmail());

            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(int id, Person updatedPerson) {
    }

    public void delete(int id) {
    }
}
