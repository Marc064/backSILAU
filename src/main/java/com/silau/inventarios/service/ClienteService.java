package com.silau.inventarios.service;

import com.silau.inventarios.dto.ClienteDTO;
import com.silau.inventarios.model.ClienteModel;
import com.silau.inventarios.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteDTO> findAll(){
        List<ClienteModel> clientesAux = clienteRepository.findAll();
        List<ClienteDTO> clientes = new ArrayList<>();

        for (ClienteModel cliente : clientesAux) {
            ClienteDTO clienteDTO = ClienteDTO.fromModel(cliente);
            clientes.add(clienteDTO);
        }

        return clientes;
    }

}
