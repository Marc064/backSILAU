package com.silau.inventarios.service;

import com.silau.inventarios.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministradorService {

    @Autowired
    AdministradorRepository administradorRepository;

}
