package com.silau.inventarios.repository;

import com.silau.inventarios.model.ClienteModel;
import com.silau.inventarios.model.EmpresaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<EmpresaModel, Long> {

    EmpresaModel findByCliente(ClienteModel cliente);

}
