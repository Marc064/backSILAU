package com.silau.inventarios.repository;

import com.silau.inventarios.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {
}
