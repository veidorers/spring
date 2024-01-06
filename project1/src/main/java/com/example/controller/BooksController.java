package com.example.controller;

import com.example.dao.BookDao;
import com.example.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDao bookDao;

    @Autowired
    public BooksController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @GetMapping
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookDao.getAllBooks());
        return "books/allBooks";
    }

    @GetMapping("/{id}")
    public String getBookById(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDao.getById(id));
        return "books/oneBook";
    }

    @GetMapping("/new")
    public String createPage(@ModelAttribute("book") Book book) {
        return "books/createBookPage";
    }

    @PostMapping
    public String add(@ModelAttribute("book") Book book) {
        bookDao.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDao.getById(id));
        return "books/editBookPage";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") Book book) {
        bookDao.update(book);
        return "redirect:/books/" + book.getId();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDao.delete(id);
        return "redirect:/books";
    }
}
