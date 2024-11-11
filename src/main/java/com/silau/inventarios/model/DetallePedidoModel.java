package com.silau.inventarios.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "DETALLE_PEDIDO")
@RequiredArgsConstructor
@AllArgsConstructor
public class DetallePedidoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DETALLE_PEDIDO_SEQ")
    @SequenceGenerator(name = "DETALLE_PEDIDO_SEQ",sequenceName = "DETALLE_PEDIDO_SEQ", allocationSize = 1)
    @Column(name = "ID_DETALLE_PEDIDO")
    private long idDetallePedido;

    @ManyToOne
    @JoinColumn(name = "ID_PEDIDO", nullable = false)
    private PedidoModel pedido;

    @ManyToOne
    @JoinColumn(name = "ID_PRODUCTO", nullable = false)
    private ProductoModel producto;

    @Column(name = "CANTIDAD", nullable = false)
    private int cantidad;

}
