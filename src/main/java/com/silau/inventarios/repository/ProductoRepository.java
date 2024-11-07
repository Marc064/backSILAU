package com.silau.inventarios.repository;

import com.silau.inventarios.model.LineaProductoModel;
import com.silau.inventarios.model.ProductoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<ProductoModel, Long> {

    List<ProductoModel> findByLineaProducto(LineaProductoModel lineaProducto);

}
