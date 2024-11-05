package com.silau.inventarios.controller;

import com.silau.inventarios.dto.DescripcionProductoDTO;
import com.silau.inventarios.dto.ProductoDTO;
import com.silau.inventarios.responses.ResponseHandler;
import com.silau.inventarios.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/products")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/")
    public ResponseEntity<Object> findAll(){
        try{
            List<ProductoDTO> result = productoService.findAll();
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<Object> findByNombre(@PathVariable String nombre){
        try{
            List<DescripcionProductoDTO> result = productoService.findByNombre(nombre);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

}
