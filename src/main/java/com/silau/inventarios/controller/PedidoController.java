package com.silau.inventarios.controller;

import com.silau.inventarios.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/order")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

}
