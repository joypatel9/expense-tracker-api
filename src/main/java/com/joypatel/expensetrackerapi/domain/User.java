package com.joypatel.expensetrackerapi.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "et_user")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue
    private Integer userId;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
