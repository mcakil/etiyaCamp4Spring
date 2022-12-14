package com.etiya.northwind.business.requests.categoryRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SortCategoryRequest {

    private String sortField;
    private String sortType;
}
