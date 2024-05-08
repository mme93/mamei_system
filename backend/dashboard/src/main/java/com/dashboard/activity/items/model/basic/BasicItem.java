package com.dashboard.activity.items.model.basic;


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
@Table(name = "basic_item")
public class BasicItem {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;


    private String schemeName;

    private String name;

    private String description;

    private String icon;

    public BasicItem(String schemeName, String name, String description, String icon) {
        this.schemeName = schemeName;
        this.name = name;
        this.description = description;
        this.icon = icon;
    }
}
