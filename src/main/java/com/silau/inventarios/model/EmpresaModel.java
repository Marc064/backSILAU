package com.silau.inventarios.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "EMPRESA")
@RequiredArgsConstructor
@AllArgsConstructor
public class EmpresaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPRESA_SEQ")
    @SequenceGenerator(name = "EMPRESA_SEQ",sequenceName = "EMPRESA_SEQ", allocationSize = 1)
    @Column(name = "ID_EMPRESA")
    private long idEmpresa;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE", nullable = false)
    private ClienteModel cliente;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "CODIGO", nullable = false)
    private String codigo;

    @Column(name = "DIRECCION", nullable = false)
    private String direccion;

}
