package com.etiya.northwind.business.concretes;
import com.etiya.northwind.business.abstracts.OrderDetailsService;
import com.etiya.northwind.business.responses.orderDetails.OrderDetailsListResponse;
import com.etiya.northwind.dataAccess.abstracts.OrderDetailsRepository;
import com.etiya.northwind.entities.concretes.OrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailsManager implements OrderDetailsService {
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public OrderDetailsManager(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }

    @Override
    public List<OrderDetailsListResponse> getAll() {
        List<OrderDetails> result = this.orderDetailsRepository.findAll();
        List<OrderDetailsListResponse> response = new ArrayList<OrderDetailsListResponse>();

        for(OrderDetails orderDetails : result) {
            OrderDetailsListResponse responseOrderDetails = new OrderDetailsListResponse();
            responseOrderDetails.setOrderId(orderDetails.getOrderDetailsId().getOrder().getOrderId());
            responseOrderDetails.setProductName(orderDetails.getOrderDetailsId().getProduct().getProductName());
            responseOrderDetails.setDiscount(orderDetails.getDiscount());
            responseOrderDetails.setQuantity(orderDetails.getQuantity());
            responseOrderDetails.setUnitPrice(orderDetails.getUnitPrice());
            response.add(responseOrderDetails);
        }

        return response;
    }
}

