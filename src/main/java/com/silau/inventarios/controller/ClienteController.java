package com.silau.inventarios.controller;

import com.silau.inventarios.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/client")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

}
