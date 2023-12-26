package com.user.model.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "System_User")
public class SystemUserEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "username", nullable = false, unique = true)
    private String userName;
    @Column(name = "password", nullable = false)
    private String password;

    public SystemUserEntity(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
