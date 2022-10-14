package com.example.springsecurity.services;

import com.example.springsecurity.models.Person;
import com.example.springsecurity.repositories.PeopleRepository;
import com.example.springsecurity.security.PersonDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {
    private final PeopleRepository peopleRepository;

    public PersonDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public Optional<Person> findUserByUsername(String username){
        return peopleRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = peopleRepository.findByUsername(username);

        if (person.isEmpty()) throw new UsernameNotFoundException("User not found");

        return new PersonDetails(person.get());

//        Get principal:
//        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
