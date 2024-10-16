package com.silau.inventarios.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "AMINISTRADOR")
@RequiredArgsConstructor
@AllArgsConstructor
public class AdministradorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADMINISTRADOR_SEQ")
    @SequenceGenerator(name = "ADMINISTRADOR_SEQ",sequenceName = "ADMINISTRADOR_SEQ", allocationSize = 1)
    @Column(name = "ID_ADMINISTRADOR")
    private long idAdministrador;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "usuario", nullable = false)
    private String usuario;

    @Column(name = "contraseña", nullable = false)
    private String contrasenaHash;

}
