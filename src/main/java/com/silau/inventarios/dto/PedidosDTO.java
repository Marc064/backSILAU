package com.silau.inventarios.dto;

import com.silau.inventarios.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidosDTO {

    private long idPedido;
    private String empresa;
    private String cliente;
    private Date fechaIngreso;
    private double total;
    private String estadoPedido;
    private String estadoProducto;

    public static PedidosDTO fromModels (PedidoModel pedido, EmpresaModel empresa, ClienteModel cliente, ProductoModel producto) {
        PedidosDTO dto = new PedidosDTO();

        dto.idPedido = pedido.getIdPedido();
        dto.empresa = empresa.getNombre();
        dto.cliente = cliente.getNombre();
        dto.fechaIngreso = pedido.getFechaIngreso();
        dto.total = pedido.getTotal();
        dto.estadoPedido = pedido.getEstado();
        dto.estadoProducto = producto.getEstado();

        return dto;
    }

}
