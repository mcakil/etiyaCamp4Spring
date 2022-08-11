package com.etiya.northwind.business.requests.orderRequests;

import com.etiya.northwind.business.requests.orderDetailRequests.CreateOrderDetailRequest;
import com.etiya.northwind.entities.concretes.OrderDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {

    @NotNull
    private int orderId;

    private String customerId;

    private int employeeId;

    private LocalDate orderDate;

    private List<CreateOrderDetailRequest> orderDetailRequests;
}
