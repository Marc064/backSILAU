package com.silau.inventarios.dto;

import com.silau.inventarios.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;
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
    private Date fechaEntrega;
    private double total;
    private String estadoPedido;
    private String estadoProducto;
    private DetallePedidoDTO productoPedido;

    public static PedidosDTO fromModels (PedidoModel pedido, EmpresaModel empresa, ClienteModel cliente, ProductoModel producto, DetallePedidoModel detallePedido) {
        PedidosDTO dto = new PedidosDTO();

        dto.idPedido = pedido.getIdPedido();
        dto.empresa = empresa.getNombre();
        dto.cliente = cliente.getNombre();
        dto.fechaIngreso = pedido.getFechaIngreso();
        dto.fechaEntrega = pedido.getFechaEntrega();
        dto.total = pedido.getTotal();
        dto.estadoPedido = pedido.getEstado();
        dto.estadoProducto = producto.getEstado();
        dto.productoPedido = new DetallePedidoDTO(new ProductoDTO(producto.getIdProducto(), producto.getNombre(), producto.getImagen(), producto.getPrecio(), producto.getTamano(), producto.getCantidadExistente(), producto.getDescripcion(), producto.getEstado(), producto.getLineaProducto().getNombreLinea()), detallePedido.getCantidad(), detallePedido.getCantidad()*detallePedido.getProducto().getPrecio());

        return dto;
    }

}
