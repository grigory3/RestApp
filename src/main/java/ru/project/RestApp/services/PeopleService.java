package ru.project.RestApp.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.project.RestApp.models.Person;
import ru.project.RestApp.repositories.PeopleRepository;
import ru.project.RestApp.util.PersonNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElseThrow(PersonNotFoundException::new);
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }
}
