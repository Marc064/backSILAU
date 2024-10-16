package com.silau.inventarios.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "PRODUCTO")
@RequiredArgsConstructor
@AllArgsConstructor
public class ProductoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCTO_SEQ")
    @SequenceGenerator(name = "PRODUCTO_SEQ",sequenceName = "PRODUCTO_SEQ", allocationSize = 1)
    @Column(name = "ID_PRODUCTO")
    private long idProducto;

    @ManyToOne
    @JoinColumn(name = "ID_ADMINISTRADOR", nullable = false)
    private AdministradorModel administrador;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "LINEA_PRODUCTO", nullable = false)
    private String lineaProducto;

    @Column(name = "PRECIO", nullable = false)
    private Double precio;

    @Column(name = "TAMAÑO", nullable = false)
    private Double tamano;

    @Column(name = "CANTIDAD_EXISTENTE", nullable = false)
    private int cantidadExistente;

    @Column(name = "IMAGEN", nullable = false)
    private String imagen;

    @Column(name = "DESCRIPCION", nullable = false)
    private String descripcion;

    @Column(name = "ESTADO", nullable = false)
    private String estado;

}
