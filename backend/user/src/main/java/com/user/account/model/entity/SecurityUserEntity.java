package com.user.account.model.entity;

import com.user.account.model.entity_enum.UserCollection;
import jakarta.persistence.*;
import lombok.*;

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

    public SecurityUserEntity(String username, String password, UserCollection userCollection) {
        this.username = username;
        this.password = password;
        this.userCollection = userCollection;
    }
}
