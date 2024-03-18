package com.user.account.model.entity;

import com.user.account.model.entity_enum.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class representing an account.
 */
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


    /**
     * Constructs an AccountEntity object with the given parameters.
     *
     * @param userId        The ID of the user associated with the account.
     * @param firstName     The first name of the account holder.
     * @param lastName      The last name of the account holder.
     * @param username      The username of the account.
     * @param callNumber    The call number associated with the account.
     * @param email         The email address associated with the account.
     * @param role          The role of the account.
     * @param microServices The microservices associated with the account.
     */
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
