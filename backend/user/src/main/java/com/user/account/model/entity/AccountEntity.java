package com.user.account.model.entity;

import com.user.account.model.entity_enum.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Account")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique = true)
    private Long userId;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String username;

    private String callNumber;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String microServices;

    public AccountEntity(Long userId, String firstName, String lastName, String username, String callNumber, String email, Role role, String microServices) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.callNumber = callNumber;
        this.email = email;
        this.role = role;
        this.microServices = microServices;
    }
}
