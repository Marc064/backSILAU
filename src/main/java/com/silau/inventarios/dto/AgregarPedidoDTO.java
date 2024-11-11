package com.silau.inventarios.dto;

import com.silau.inventarios.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgregarPedidoDTO {

    private long idEmpresa;
    private Date fechaIngreso;
    private Date fechaEntrega;
    private String numeroPedido;
    private String estado;
    private String detalle;
    private CantidadProductoDTO Producto;


    public static PedidoModel toModelPedido(AgregarPedidoDTO dto, ClienteModel cliente, AdministradorModel administrador, double total) {
        PedidoModel pedido = new PedidoModel();

        pedido.setAdministrador(administrador);
        pedido.setCliente(cliente);
        pedido.setDetalles(dto.getDetalle());
        pedido.setFechaEntrega(dto.getFechaEntrega());
        pedido.setFechaIngreso(dto.getFechaIngreso());
        pedido.setEstado(dto.getEstado());
        pedido.setTotal(total);

        return pedido;
    }

    public static DetallePedidoModel toDetallePedido(int cantidad, PedidoModel pedido, ProductoModel producto) {
        DetallePedidoModel detalle = new DetallePedidoModel();

        detalle.setPedido(pedido);
        detalle.setProducto(producto);
        detalle.setCantidad(cantidad);

        return detalle;
    }

    public static PedidoModel saveModelPedido(AgregarPedidoDTO dto, ClienteModel cliente, PedidoModel pedido, double total) {

        pedido.setCliente(cliente);
        pedido.setDetalles(dto.getDetalle());
        pedido.setFechaEntrega(dto.getFechaEntrega());
        pedido.setFechaIngreso(dto.getFechaIngreso());
        pedido.setEstado(dto.getEstado());
        pedido.setTotal(total);

        return pedido;
    }

    public static DetallePedidoModel saveDetallePedido(int cantidad, PedidoModel pedido, ProductoModel producto, DetallePedidoModel detalle) {

        detalle.setPedido(pedido);
        detalle.setProducto(producto);
        detalle.setCantidad(cantidad);

        return detalle;
    }



}
