package com.dashboard.activity.items.model.value.typ;

import com.dashboard.activity.items.model.EItemValueTyp;
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
@Table(name = "item_value_typ")
public class ItemValueTyp {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EItemValueTyp eItemValueTyp;

    public ItemValueTyp(EItemValueTyp eItemValueTyp) {
        this.eItemValueTyp = eItemValueTyp;
    }
}
