package com.silau.inventarios.repository;

import com.silau.inventarios.model.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<PedidoModel, Long> {
}
