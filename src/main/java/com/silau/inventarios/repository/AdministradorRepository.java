package com.silau.inventarios.repository;


import com.silau.inventarios.model.AdministradorModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradorRepository  extends JpaRepository<AdministradorModel, Long> {
}
