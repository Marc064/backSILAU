package com.silau.inventarios.repository;

import com.silau.inventarios.model.ProductoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<ProductoModel, Long> {
}
