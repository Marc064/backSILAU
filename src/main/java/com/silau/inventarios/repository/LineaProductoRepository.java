package com.silau.inventarios.repository;

import com.silau.inventarios.model.LineaProductoModel;
import com.silau.inventarios.model.ProductoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LineaProductoRepository extends JpaRepository<LineaProductoModel, Long>, JpaSpecificationExecutor<LineaProductoModel> {

    LineaProductoModel findByNombreLineaContainingIgnoreCase(String nombre);
    LineaProductoModel findLineaProductoModelByProductoId(ProductoModel producto);

}
