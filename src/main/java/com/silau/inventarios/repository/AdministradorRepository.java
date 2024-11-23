package com.silau.inventarios.repository;


import com.silau.inventarios.model.AdministradorModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministradorRepository  extends JpaRepository<AdministradorModel, Long> {
    Optional<AdministradorModel> findByUsuario(String usuario);
}
