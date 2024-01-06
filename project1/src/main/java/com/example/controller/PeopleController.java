package com.example.controller;

import com.example.dao.PersonDao;
import com.example.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDao personDao;

    @Autowired
    public PeopleController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping
    public String getAllPeople(Model model) {
        model.addAttribute("people", personDao.getAllPeople());
        return "people/allPeople";
    }

    @GetMapping("/{id}")
    public String getPersonById(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDao.getById(id));
        return "people/onePerson";
    }

    @GetMapping("/new")
    public String createPage(@ModelAttribute("person") Person person) {
        return "people/createPersonPage";
    }

    @PostMapping
    public String add(@ModelAttribute("person") Person person) {
        personDao.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDao.getById(id));
        return "people/editPersonPage";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person) {
        personDao.update(person);
        return "redirect:/people/" + person.getId();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDao.delete(id);
        return "redirect:/people";
    }
}
