package com.silau.inventarios.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "PEDIDO")
@RequiredArgsConstructor
@AllArgsConstructor
public class PedidoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PEDIDO_SEQ")
    @SequenceGenerator(name = "PEDIDO_SEQ",sequenceName = "PEDIDO_SEQ", allocationSize = 1)
    @Column(name = "ID_PEDIDO")
    private long idPedido;

    @ManyToOne
    @JoinColumn(name = "ID_ADMINISTRADOR", nullable = false)
    private AdministradorModel administrador;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE", nullable = false)
    private ClienteModel cliente;

    @Column(name = "FECHA_INGRESO", nullable = false)
    private Date fechaIngreso;

    @Column(name = "FECHA_ENTREGA", nullable = true)
    private Date fechaEntrega;

    @Column(name = "DETALLES", nullable = true)
    private String detalles;

    @Column(name = "ESTADO", nullable = false)
    private String estado;

    @Column(name = "TOTAL", nullable = false)
    private double total;

}
