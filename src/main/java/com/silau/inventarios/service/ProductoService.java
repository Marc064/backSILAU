package com.silau.inventarios.service;

import com.silau.inventarios.dto.DescripcionProductoDTO;
import com.silau.inventarios.dto.GuardarProductoDTO;
import com.silau.inventarios.dto.ProductoDTO;
import com.silau.inventarios.model.LineaProductoModel;
import com.silau.inventarios.model.ProductoModel;
import com.silau.inventarios.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private AdministradorService administradorService;

    @Autowired
    private LineaProductoService lineaProductoService;

    public List<ProductoDTO> findAll(){
        List<ProductoModel> productosAux = productoRepository.findAll();
        List<ProductoDTO> productos = new ArrayList<ProductoDTO>();

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

    public ProductoModel delete(long idProducto){
        ProductoModel modelo = findById(idProducto);
        productoRepository.delete(modelo);
        return modelo;
    }

}
