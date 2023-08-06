package com.login.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String password;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private String callNumber;

    private String roll;

    private String company;

    private String info;

    public UserEntity(String password, String username, String firstName, String lastName, String email, String callNumber, String roll, String company, String info) {
        this.password = password;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.callNumber = callNumber;
        this.roll = roll;
        this.company = company;
        this.info = info;
    }
}
