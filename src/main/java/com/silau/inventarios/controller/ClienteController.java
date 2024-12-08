package com.silau.inventarios.controller;

import com.silau.inventarios.dto.ClienteDTO;
import com.silau.inventarios.dto.ClienteEmpresaDTO;
import com.silau.inventarios.model.ClienteModel;
import com.silau.inventarios.responses.ResponseHandler;
import com.silau.inventarios.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/client")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/")
    public ResponseEntity<Object> findAll(){
        try{
            List<ClienteDTO> result = clienteService.findAll();
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/{idClient}")
    public ResponseEntity<Object> findClient(@PathVariable Long idClient){
        try{
            ClienteDTO result = clienteService.findById(idClient);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PostMapping("/{idAdministrador}")
    public ResponseEntity<Object> save(@PathVariable long idAdministrador, @RequestBody ClienteEmpresaDTO dto){
        try{
            ClienteModel result = clienteService.save(dto, idAdministrador);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PutMapping("/{idCliente}")
    public ResponseEntity<Object> update(@PathVariable long idCliente, @RequestBody ClienteEmpresaDTO dto){
        try{
            ClienteModel result = clienteService.update(dto, idCliente);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @DeleteMapping("/{idCliente}")
    public ResponseEntity<Object> delete(@PathVariable long idCliente){
        try{
            ClienteModel result = clienteService.delete(idCliente);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

}
