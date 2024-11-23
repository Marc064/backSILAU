package com.silau.inventarios.service;

import com.silau.inventarios.dto.DescripcionProductoDTO;
import com.silau.inventarios.dto.GuardarProductoDTO;
import com.silau.inventarios.dto.ProductoDTO;
import com.silau.inventarios.model.LineaProductoModel;
import com.silau.inventarios.model.ProductoModel;
import com.silau.inventarios.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductoService {

    @Value("${app.image.upload-dir}")
    private String uploadDir;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private AdministradorService administradorService;

    @Autowired
    private LineaProductoService lineaProductoService;

    public List<ProductoDTO> findAll() throws IOException {
        List<ProductoModel> productosAux = productoRepository.findAll();
        List<ProductoDTO> productos = new ArrayList<>();

        for (ProductoModel productoAux : productosAux) {
            ProductoDTO producto = ProductoDTO.fromModel(productoAux);
            productos.add(producto);
        }

        return productos;
    }

    public ProductoModel findById(Long id){
        return productoRepository.findById(id).orElse(null);
    }

    public List<DescripcionProductoDTO> findByNombreLinea(String nombre){
        LineaProductoModel lineaProducto = lineaProductoService.findByNombre(nombre);
        List<ProductoModel> productosAux = productoRepository.findByLineaProducto(lineaProducto);
        List<DescripcionProductoDTO> productos = new ArrayList<>();

        for (ProductoModel productoAux : productosAux) {
            DescripcionProductoDTO producto = DescripcionProductoDTO.fromModel(productoAux);
            productos.add(producto);
        }

        return productos;
    }

    public ProductoModel save(GuardarProductoDTO producto){
        ProductoModel modelo = GuardarProductoDTO.toModel(producto);
        modelo.setLineaProducto(lineaProductoService.findById(producto.getIdLineaProducto()));
        modelo.setAdministrador(administradorService.findById(producto.getIdAdministrador()));
        return productoRepository.save(modelo);
    }

    public ProductoModel update(GuardarProductoDTO producto){
        ProductoModel modelo = findById(producto.getIdProducto());
        modelo = GuardarProductoDTO.toModelSave(producto, modelo);
        modelo.setLineaProducto(lineaProductoService.findById(producto.getIdLineaProducto()));
        return productoRepository.save(modelo);
    }

    public ProductoModel delete(long idProducto) throws IOException {
        ProductoModel modelo = findById(idProducto);
        deleteImagen(modelo);
        productoRepository.delete(modelo);
        return modelo;
    }

    public boolean existenciasProducto(long idProducto, int cantidad){
        ProductoModel producto = findById(idProducto);
        return producto.getCantidadExistente() <= cantidad;
    }


    public String saveImagen(MultipartFile file) throws IOException {
        // Generar un nombre único para evitar sobrescribir el archivo
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        // Crear la ruta completa donde se guardará la imagen
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);  // Crear el directorio si no existe
        }

        // Guardar el archivo en el directorio
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);

        // Generar la URL relativa de la imagen (esto dependerá de tu configuración)
        return "/images/" + fileName;  // Aquí generamos la URL relativa
    }


    public String updateImagen(ProductoModel producto, MultipartFile newFile) throws IOException {
        // Eliminar la imagen anterior si existe
        if (producto.getImagen() != null && !producto.getImagen().isEmpty()) {
            // Eliminar la imagen anterior del sistema de archivos
            String oldImagePath = uploadDir + producto.getImagen().substring("/images/".length());
            File oldImageFile = new File(oldImagePath);
            if (oldImageFile.exists()) {
                oldImageFile.delete();  // Eliminar la imagen anterior
            }
        }

        // Guardar la nueva imagen y obtener la URL
        return saveImagen(newFile);
    }


    public void deleteImagen(ProductoModel producto) throws IOException {
        // Verificar si el producto tiene una imagen asignada
        if (producto.getImagen() != null && !producto.getImagen().isEmpty()) {
            // Obtener la ruta completa de la imagen
            String imagePath = uploadDir + producto.getImagen().substring("/images/".length());
            File imageFile = new File(imagePath);

            // Si la imagen existe en el sistema de archivos, eliminarla
            if (imageFile.exists()) {
                imageFile.delete();
            }

            // Eliminar la imagen del registro del producto (dejarlo nulo o vacío)
            producto.setImagen(null);  // O podrías poner un string vacío ""
        }
    }
}
