package com.silau.inventarios.controller;

import com.silau.inventarios.dto.DescripcionProductoDTO;
import com.silau.inventarios.dto.GuardarProductoDTO;
import com.silau.inventarios.dto.ProductoDTO;
import com.silau.inventarios.model.ProductoModel;
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

    @GetMapping("/product-line/{nombre}")
    public ResponseEntity<Object> findByNombreLinea(@PathVariable String nombre){
        try{
            List<DescripcionProductoDTO> result = productoService.findByNombreLinea(nombre);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable long id){
        try{
            ProductoModel result = productoService.findById(id);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Object> addProducto(@RequestBody GuardarProductoDTO guardarProductoDTO) {

        try {
            ProductoModel result = productoService.save(guardarProductoDTO);

            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }


    @PutMapping("/update")
    public ResponseEntity<Object> updateProducto(@RequestBody GuardarProductoDTO dto) {
        try {
            ProductoModel result = productoService.update(dto);

            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProducto(@PathVariable long id){
        try{
            ProductoModel result = productoService.delete(id);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

}
