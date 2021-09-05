package com.jr.starbux.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.Hibernate;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
@Embeddable
public class OrderDrinkToppingId implements Serializable {
    private static final long serialVersionUID = 8770512007649082166L;
    @Column(name = "order_drink_order_id", nullable = false)
    private Integer orderDrinkOrderId;
    @Column(name = "order_drink_drink_id", nullable = false)
    private Integer orderDrinkDrinkId;
    @Column(name = "topping_id", nullable = false)
    private Integer toppingId;

    @Override
    public int hashCode() {
        return Objects.hash(orderDrinkOrderId, toppingId, orderDrinkDrinkId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrderDrinkToppingId entity = (OrderDrinkToppingId) o;
        return Objects.equals(this.orderDrinkOrderId, entity.orderDrinkOrderId) &&
                Objects.equals(this.toppingId, entity.toppingId) &&
                Objects.equals(this.orderDrinkDrinkId, entity.orderDrinkDrinkId);
    }
}