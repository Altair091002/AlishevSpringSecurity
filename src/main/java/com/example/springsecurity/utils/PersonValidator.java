package com.example.springsecurity.utils;

import com.example.springsecurity.models.Person;
import com.example.springsecurity.services.PersonDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    private final PersonDetailsService personDetailsService;

    public PersonValidator(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (personDetailsService.findUserByUsername(person.getUsername()).isPresent())
            errors.rejectValue("username", "404", "Пользователь с таким username-ом уже существует");

    }
}
