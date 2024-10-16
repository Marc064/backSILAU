package com.silau.inventarios.controller;

import com.silau.inventarios.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/enterprise")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

}
