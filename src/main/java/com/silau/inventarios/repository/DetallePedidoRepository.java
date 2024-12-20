package com.silau.inventarios.repository;

import com.silau.inventarios.model.DetallePedidoModel;
import com.silau.inventarios.model.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetallePedidoRepository extends JpaRepository<DetallePedidoModel, Long> {

    DetallePedidoModel findByPedido(PedidoModel pedido);

}
