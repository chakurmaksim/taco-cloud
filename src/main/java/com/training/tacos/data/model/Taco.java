package com.training.tacos.data.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Taco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(targetEntity = Ingredient.class)
    @JoinTable(name = "Taco_Ingredients", joinColumns = @JoinColumn(name = "taco_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "id"))
    private List<Ingredient> ingredients;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @ManyToMany(mappedBy = "tacos", fetch = FetchType.LAZY)
    private List<Order> orders;

    @PrePersist
    public void createdAt() {
        createdAt = new Timestamp(new Date().getTime());
    }
}
