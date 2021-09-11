package com.jr.starbux.entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonGetter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "order")
@Table(name = "sb_order")
@Entity
public class Order extends BaseModel implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date_time", nullable = false)
    private Instant dateTime;

    @Column(name = "client_id", nullable = false, length = 100)
    private Long clientId;
    
    @Column(name = "address", nullable = false, length = 300)
	private String address;

    @Column(name = "discount", nullable = false, length = 100)
    private double discount;
    
    @ManyToOne
	@MapsId("id")
	@JoinColumn(name = "client_id")
	private Client client;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDrink> order = new ArrayList<>();

    @Column(name = "active", columnDefinition = "bit default 0", nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean active;


    @PrePersist
    void preInsert() {
        if (this.dateTime == null)
            this.dateTime = Instant.now();
        if (this.active == null)
            this.active = true;


    }

    @Transient
    @JsonGetter(value = "total")
    public Double getTotal() {
        Double v1 = this.getOrder()
                .stream()
                .map(v -> v.getTotalToppings())
                .filter(Objects::nonNull).reduce(0.0, (a, b) -> a + b);

        Double v2 = this.getOrder()
                .stream()
                .map(v -> v.getDrinkUnitPrice())
                .filter(Objects::nonNull)
                .reduce(0.0, (a, b) -> a + b);

        return (v1 + v2);
    }
}