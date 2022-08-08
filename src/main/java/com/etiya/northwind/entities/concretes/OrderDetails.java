package com.etiya.northwind.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Table(name="order_details")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails{

    @EmbeddedId
    private OrderDetailsId orderDetailsId;

    @Column(name="unit_price")
    private double unitPrice;

    @Column(name="quantity")
    private int quantity;

    @Column(name="discount")
    private double discount;
}
