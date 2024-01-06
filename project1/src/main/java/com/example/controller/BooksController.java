package com.example.controller;

import com.example.dao.BookDao;
import com.example.dao.PersonDao;
import com.example.model.Book;
import com.example.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDao bookDao;
    private final PersonDao personDao;

    @Autowired
    public BooksController(BookDao bookDao, PersonDao personDao) {
        this.bookDao = bookDao;
        this.personDao = personDao;
    }

    @GetMapping
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookDao.getAllBooks());
        return "books/allBooks";
    }

    @GetMapping("/{id}")
    public String getBookById(@PathVariable("id") int id, Model model) {
        var book = bookDao.getById(id);
        model.addAttribute("book", book);
        if(book.getPersonId() != null) {
            model.addAttribute("person", personDao.getById(book.getPersonId()));
        } else {
            model.addAttribute("person", null);
        }
        model.addAttribute("people", personDao.getAllPeople());
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


    @PatchMapping("/{id}/return")
    public String returnBook(@PathVariable("id") int id) {
        bookDao.returnBook(id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/reserve")
    public String reserveBook(@ModelAttribute("book") Book book) {
        bookDao.update(book);
        return "redirect:/books";
    }
}
