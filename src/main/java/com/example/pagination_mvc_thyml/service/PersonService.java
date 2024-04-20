package com.example.pagination_mvc_thyml.service;

import com.example.pagination_mvc_thyml.entities.Person;
import com.example.pagination_mvc_thyml.repositories.PersonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service

public class PersonService {


    private final PersonRepository personRepository;


    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


  public Page<Person> getAllPeople(int page, int size){
        return personRepository.findAll(PageRequest.of(page, size));
    }
    public List<Person> getPeopleByName(String keyword){
        return personRepository.findPersonByNameLikeIgnoreCase(keyword);
    }
    public void  addPerson (Person person){
        personRepository.save(person);
    }

    public void  removePerson (Person person) {

            if (personRepository.existsById(person.getCin()))
                personRepository.delete(person);

    }
    public void  updatePerson (Person person){
        personRepository.save(person);
    }
    public Person getPersonById(Long cin){
        return personRepository.findById(cin).orElseThrow();
    }
    public boolean personExists(Long cin){
          return personRepository.existsById(cin);
    }

}
