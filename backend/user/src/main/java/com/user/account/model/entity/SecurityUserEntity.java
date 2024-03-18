package com.user.account.model.entity;

import com.user.account.model.entity_enum.UserCollection;
import jakarta.persistence.*;
import lombok.*;

/**
 * Entity class representing a security user.
 */
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "security_user")
public class SecurityUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserCollection userCollection;

    /**
     * Constructs a SecurityUserEntity object with the given parameters.
     *
     * @param username       The username of the security user.
     * @param password       The password of the security user.
     * @param userCollection The collection to which the user belongs.
     */
    public SecurityUserEntity(String username, String password, UserCollection userCollection) {
        this.username = username;
        this.password = password;
        this.userCollection = userCollection;
    }
}
