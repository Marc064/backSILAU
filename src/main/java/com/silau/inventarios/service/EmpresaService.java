package com.silau.inventarios.service;

import com.silau.inventarios.model.ClienteModel;
import com.silau.inventarios.model.EmpresaModel;
import com.silau.inventarios.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    public EmpresaModel findByCliente(ClienteModel cliente) {
        return empresaRepository.findByCliente(cliente);
    }

    public ClienteModel findByEmpresa(long idEmpresa){
        EmpresaModel empresa = empresaRepository.findById(idEmpresa).orElse(null);

        if(empresa == null){
            return null;
        }

        return empresa.getCliente();
    }

}
