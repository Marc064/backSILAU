package com.silau.inventarios.controller;

import com.silau.inventarios.dto.AgregarPedidoDTO;
import com.silau.inventarios.dto.CantidadProductoDTO;
import com.silau.inventarios.dto.PedidosDTO;
import com.silau.inventarios.model.PedidoModel;
import com.silau.inventarios.model.ProductoModel;
import com.silau.inventarios.responses.ResponseHandler;
import com.silau.inventarios.service.PedidoService;
import com.silau.inventarios.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("/order")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private ProductoService productoService;

    @GetMapping("/")
    public ResponseEntity<Object> findAll() {
        try {
            List<PedidosDTO> result = pedidoService.findAll();
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PostMapping("/save/{idAdministrador}")
    public ResponseEntity<Object> save(@RequestBody AgregarPedidoDTO agregarPedido, @PathVariable long idAdministrador) {
        try{
            boolean stock;

                stock = productoService.existenciasProducto(agregarPedido.getProducto().getIdProducto(), agregarPedido.getProducto().getCantidad());

                if (!stock) {
                    ProductoModel productoError = productoService.findById(agregarPedido.getProducto().getIdProducto());
                    return ResponseHandler.generateResponse("Error", HttpStatus.BAD_REQUEST,
                            "Cantidad insuficiente para el producto: " + productoError.getNombre());
                }


            AgregarPedidoDTO result = pedidoService.save(agregarPedido, idAdministrador);

            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PutMapping("/update/{idPedido}")
    public ResponseEntity<Object> update(@RequestBody AgregarPedidoDTO agregarPedido, @PathVariable long idPedido) {
        try{

            boolean stock;

            stock = productoService.existenciasProducto(agregarPedido.getProducto().getIdProducto(), agregarPedido.getProducto().getCantidad());

            if (!stock) {
                ProductoModel productoError = productoService.findById(agregarPedido.getProducto().getIdProducto());
                return ResponseHandler.generateResponse("Error", HttpStatus.BAD_REQUEST,
                        "Cantidad insuficiente para el producto: " + productoError.getNombre());
            }


            AgregarPedidoDTO result = pedidoService.update(idPedido, agregarPedido);

            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @DeleteMapping("/delete/{idPedido}")
    public ResponseEntity<Object> delete(@PathVariable long idPedido) {
        try{
            PedidoModel result = pedidoService.delete(idPedido);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

}
