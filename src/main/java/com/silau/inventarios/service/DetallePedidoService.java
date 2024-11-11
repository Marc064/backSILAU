package com.silau.inventarios.service;

import com.silau.inventarios.dto.PedidosDTO;
import com.silau.inventarios.model.DetallePedidoModel;
import com.silau.inventarios.model.PedidoModel;
import com.silau.inventarios.repository.DetallePedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetallePedidoService {

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    public DetallePedidoModel findByPedido(PedidoModel pedido) {
        return detallePedidoRepository.findByPedido(pedido);
    }

    public DetallePedidoModel save(DetallePedidoModel detallePedidoModel) {
        return detallePedidoRepository.save(detallePedidoModel);
    }

}
