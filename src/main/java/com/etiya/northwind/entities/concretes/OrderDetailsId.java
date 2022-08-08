package com.etiya.northwind.entities.concretes;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Embeddable
@Data
public class OrderDetailsId implements Serializable {

    @OneToOne(optional = false)
    @JoinColumn(name= "order_id")
    private Order order;

    @OneToOne(optional = false)
    @JoinColumn(name= "product_id")
    private Product product;
}
