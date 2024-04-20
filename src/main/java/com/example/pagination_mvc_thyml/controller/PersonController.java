package com.example.pagination_mvc_thyml.controller;

import com.example.pagination_mvc_thyml.entities.Person;
import com.example.pagination_mvc_thyml.service.PersonService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }
@GetMapping
public  String peopleList(Model model, @RequestParam(name="page",defaultValue = "0") int page,
                          @RequestParam(name="size",defaultValue = "5") int size){
    Page<Person> personPage= personService.getAllPeople(page,size);
  if(personPage.isEmpty()) personPage= personService.getAllPeople(--page,size);

        model.addAttribute(
                "people", personPage.getContent());

    model.addAttribute("current",page);
    model.addAttribute("pages",new int[personPage.getTotalPages()]);
     return "index";
}


    @GetMapping("/add")
    public  String formperson(Model model){
        model.addAttribute("user", new Person());
        return "add";
    }

    @PostMapping("/add")
    public  String addperson(Model model,@ModelAttribute("user") Person person){
        System.out.println("add personne");
        personService.addPerson(person);
        return "redirect:/people";
    }

    @GetMapping("/delete/{id}")
    public  String deletePerson(Model model,@PathVariable("id") Long cin,
                                @RequestParam(value = "page",defaultValue = "0")int page){

        try {
            if (personService.personExists(cin)) {
                Person p = personService.getPersonById(cin);
                personService.removePerson(p);
            }
        }
        catch(NoSuchElementException nse){
            System.out.println(nse.getMessage());
        }

        return "redirect:/people?page="+page;
    }

    @GetMapping("/edit/{id}")
    public  String editPerson(Model model,@PathVariable("id") Long cin,
                              @RequestParam("page") int page){
        Person p = personService.getPersonById(cin);
        model.addAttribute("person",p);
        model.addAttribute("page",page);

        return "form";
    }
    @PostMapping("/edit")
    public  String editPerson(@ModelAttribute Person person, Model model,
                              @RequestParam("page") int page){

        personService.updatePerson(person);

        return "redirect:/people?page="+page;
    }

}
