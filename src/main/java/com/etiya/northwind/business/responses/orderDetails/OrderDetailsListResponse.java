package com.etiya.northwind.business.responses.orderDetails;

import com.etiya.northwind.entities.concretes.Order;
import com.etiya.northwind.entities.concretes.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsListResponse {
    private int orderId;
    private String productName;
    private double unitPrice;
    private int quantity;
    private double discount;

}
