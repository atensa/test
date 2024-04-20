package com.example.pagination_mvc_thyml.repositories;

import com.example.pagination_mvc_thyml.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends  JpaRepository<Person, Long> {
    List<Person> findPersonByNameLikeIgnoreCase(String keyword);
}
