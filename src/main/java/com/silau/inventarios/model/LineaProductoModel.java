package com.silau.inventarios.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "LINEA_PRODUCTO")
@RequiredArgsConstructor
@AllArgsConstructor
public class LineaProductoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LINEA_PRODUCTO_SEQ")
    @SequenceGenerator(name = "LINEA_PRODUCTO_SEQ",sequenceName = "LINEA_PRODUCTO_SEQ", allocationSize = 1)
    @Column(name = "ID_LINEA_PRODUCTO")
    private long idPedido;

    @Column(name = "NOMBRE_LINEA")
    private String nombreLinea;

}
