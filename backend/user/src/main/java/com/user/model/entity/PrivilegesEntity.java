package com.user.model.entity;

import com.user.model.entity_enum.ServicesPrivileges;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Privileges")
public class PrivilegesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private Long accountId;

    private String servicesPrivileges;

    private String systemPrivileges;

    private String dataPrivileges;

    public PrivilegesEntity(Long accountId, String servicesPrivileges, String systemPrivileges, String dataPrivileges) {
        this.accountId = accountId;
        this.servicesPrivileges = servicesPrivileges;
        this.systemPrivileges = systemPrivileges;
        this.dataPrivileges = dataPrivileges;
    }
}
