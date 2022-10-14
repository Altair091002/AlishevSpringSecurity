package com.example.springsecurity.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "person")
@Data
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    @Size(min = 2, message = "Имя должно быть больше 2 символов")
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

//    public Person(){}
}
