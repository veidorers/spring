package com.example.dao;

import com.example.mappers.BookRowMapper;
import com.example.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getAllBooks() {
        return jdbcTemplate.query("SELECT * FROM Book", new BookRowMapper());
    }

    public Book getById(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id = ?", new BookRowMapper(), id)
                .stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(name, author, year) VALUES (?, ?, ?)",
                book.getName(), book.getAuthor(), book.getYear());
    }

    public void update(Book book) {
        jdbcTemplate.update("UPDATE Book SET name = ?, author = ?, year = ? WHERE id = ?",
                book.getName(), book.getAuthor(), book.getYear(), book.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id = ?", id);
    }
}
