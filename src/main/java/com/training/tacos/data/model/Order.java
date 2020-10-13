package com.training.tacos.data.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
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
@Table(name = "Taco_Order")
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String deliveryName;
    private String deliveryStreet;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryZip;
    @Column(name = "cc_number")
    private String ccNumber;
    @Column(name = "cc_expiration")
    private String ccExpiration;
    @Column(name = "cc_cvv")
    private String ccCVV;
    private Timestamp placedAt;
    @ManyToMany
    @JoinTable(name = "Taco_Order_Tacos", joinColumns = @JoinColumn(name = "taco_order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "taco_id", referencedColumnName = "id"))
    private List<Taco> tacos;

    @PrePersist
    public void createdAt() {
        placedAt = new Timestamp(new Date().getTime());
    }
}
