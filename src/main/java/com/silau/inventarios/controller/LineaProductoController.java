package com.silau.inventarios.controller;

import com.silau.inventarios.model.LineaProductoModel;
import com.silau.inventarios.responses.ResponseHandler;
import com.silau.inventarios.service.LineaProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/product-line")
public class LineaProductoController {

    @Autowired
    private LineaProductoService lineaProductoService;

    @GetMapping("/")
    public ResponseEntity<Object> findAll(){
        try{
            List<LineaProductoModel> result = lineaProductoService.findAll();
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody LineaProductoModel lineaProducto){
        try{
            LineaProductoModel result = lineaProductoService.save(lineaProducto);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @DeleteMapping("/{idLineaProducto}")
    public ResponseEntity<Object> delete(@PathVariable long idLineaProducto){
        try{
            LineaProductoModel result = lineaProductoService.delete(idLineaProducto);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

}
