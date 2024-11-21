package com.silau.inventarios.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<Object> addProducto(
            @RequestPart("producto") String productoJson,  // Recibe el JSON del producto
            @RequestPart("imagen") MultipartFile imagen) {  // Recibe el archivo de imagen

        try {
            // Convertimos el JSON recibido (productoJson) a un objeto DTO
            ObjectMapper objectMapper = new ObjectMapper();
            GuardarProductoDTO dto = objectMapper.readValue(productoJson, GuardarProductoDTO.class);

            // Procesar la imagen: Guardarla en el servidor o en un almacenamiento
            String imagenUrl = productoService.saveImagen(imagen);  // Método en el servicio que guarda la imagen
            dto.setImagen(imagenUrl);  // Establecer la URL de la imagen en el DTO

            // Guardar el producto en la base de datos
            ProductoModel result = productoService.save(dto);

            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }


    @PutMapping("/update")
    public ResponseEntity<Object> updateProducto(@RequestPart("producto") String productoJson,
                                                 @RequestPart(value = "imagen", required = false) MultipartFile imagen) {
        try {
            // Convertir el JSON del producto a un objeto DTO
            ObjectMapper objectMapper = new ObjectMapper();
            GuardarProductoDTO dto = objectMapper.readValue(productoJson, GuardarProductoDTO.class);

            // Buscar el producto existente por ID
            ProductoModel productoExistente = productoService.findById(dto.getIdProducto());

            // Si hay una nueva imagen, actualizarla
            String imagenUrl = productoExistente.getImagen();
            if (imagen != null) {
                // Actualizar la imagen (eliminando la anterior y guardando la nueva)
                imagenUrl = productoService.updateImagen(productoExistente, imagen);
            }

            // Actualizar el DTO con la nueva URL de la imagen
            dto.setImagen(imagenUrl);

            // Actualizar el producto con los nuevos datos
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
