package com.etiya.northwind.api.controllers;

import com.etiya.northwind.business.abstracts.OrderDetailsService;
import com.etiya.northwind.business.abstracts.OrderService;
import com.etiya.northwind.business.responses.orderDetails.OrderDetailsListResponse;
import com.etiya.northwind.business.responses.orders.OrderListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orderDetails")
public class OrderDetailsController {
    private OrderDetailsService orderDetailsService;

    @Autowired
    public OrderDetailsController(OrderDetailsService orderDetailsService) {
        this.orderDetailsService = orderDetailsService;
    }

    @GetMapping("/getall")
    public List<OrderDetailsListResponse> getAll(){
        return this.orderDetailsService.getAll();
    }
}
