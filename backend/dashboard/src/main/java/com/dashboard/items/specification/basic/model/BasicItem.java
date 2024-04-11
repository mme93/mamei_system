package com.dashboard.items.specification.basic.model;

import com.dashboard.items.item.model.Item;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "basic_item")
public class BasicItem extends Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;



    public BasicItem(String name, String description, Long userId, Integer id) {
        super(name, description, userId);
        this.id = id;
    }

    public BasicItem(String name, String description, Long userId) {
        super(name, description, userId);
    }
}
