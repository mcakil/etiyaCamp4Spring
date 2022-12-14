package com.etiya.northwind.business.requests.customerRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCustomerRequest {

    @NotNull
    private String customerId;

    @NotNull
    private String companyName;

    private String contactName;

    private String contactTitle;
}
