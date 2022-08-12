package com.etiya.northwind.dataAccess.abstracts;

import com.etiya.northwind.entities.concretes.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemsRepository extends JpaRepository<CartItem, Integer> {
}
