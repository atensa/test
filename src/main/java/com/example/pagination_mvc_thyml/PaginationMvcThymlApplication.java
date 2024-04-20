package com.example.pagination_mvc_thyml;

import com.example.pagination_mvc_thyml.entities.Person;
import com.example.pagination_mvc_thyml.service.PersonService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaginationMvcThymlApplication implements CommandLineRunner {


    private final PersonService personService;

    public PaginationMvcThymlApplication(PersonService personService) {
        this.personService = personService;
    }

    public static void main(String[] args) {
        SpringApplication.run(PaginationMvcThymlApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        personService.addPerson(new Person(null,"abdel","aa@aaa.ma"));
        personService.addPerson(new Person(null,"atlas","at@aaa.ma"));
        personService.addPerson(new Person(null,"abou","abou@aaa.ma"));
        personService.addPerson(new Person(null,"ta","ta@aaa.ma"));

    }
}
