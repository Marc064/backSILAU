package com.silau.inventarios.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CLIENTE")
@RequiredArgsConstructor
@AllArgsConstructor
public class ClienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLIENTE_SEQ")
    @SequenceGenerator(name = "CLIENTE_SEQ",sequenceName = "CLIENTE_SEQ", allocationSize = 1)
    @Column(name = "ID_CLIENTE")
    private long idCliente;

    @ManyToOne
    @JoinColumn(name = "ID_ADMINISTRADOR", nullable = false)
    private AdministradorModel administrador;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "APELLIDO", nullable = false)
    private String apellido;

    @Column(name = "CELULAR", nullable = false)
    private String celular;

    @Column(name = "CORREO", nullable = false)
    private String correo;

    @Column(name = "ACTIVO", nullable = false)
    private boolean activo;

}
