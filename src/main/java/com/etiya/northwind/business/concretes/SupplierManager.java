package com.etiya.northwind.business.concretes;

import com.etiya.northwind.business.abstracts.SupplierService;
import com.etiya.northwind.business.responses.suppliers.SupplierListResponse;
import com.etiya.northwind.dataAccess.abstracts.SupplierRepository;
import com.etiya.northwind.entities.concretes.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierManager implements SupplierService {
    private SupplierRepository supplierRepository;

    @Autowired
    public SupplierManager(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<SupplierListResponse> getAll() {
        List<Supplier> result = this.supplierRepository.findAll();
        List<SupplierListResponse> response = new ArrayList<SupplierListResponse>();

        for(Supplier supplier : result) {
            SupplierListResponse responseSupplier = new SupplierListResponse();
            responseSupplier.setSupplierId(supplier.getSupplierId());
            responseSupplier.setCompanyName(supplier.getCompanyName());
            responseSupplier.setContactTitle(supplier.getContactTitle());
            responseSupplier.setContactName(supplier.getContactName());

            response.add(responseSupplier);
        }

        return response;
    }
}

