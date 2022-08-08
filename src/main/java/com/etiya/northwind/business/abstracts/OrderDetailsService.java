package com.etiya.northwind.business.abstracts;

import com.etiya.northwind.business.responses.orderDetails.OrderDetailsListResponse;

import java.util.List;

public interface OrderDetailsService {
    List<OrderDetailsListResponse> getAll();
}
