package com.silau.inventarios.service;

import com.silau.inventarios.dto.DescripcionProductoDTO;
import com.silau.inventarios.dto.ProductoDTO;
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

    public List<ProductoDTO> findAll(){
        List<ProductoModel> productosAux = productoRepository.findAll();
        List<ProductoDTO> productos = new ArrayList<ProductoDTO>();

        for (ProductoModel productoAux : productosAux) {
            ProductoDTO producto = ProductoDTO.fromModel(productoAux);
            productos.add(producto);
        }

        return productos;
    }

    public List<DescripcionProductoDTO> findByNombre(String nombre){
        List<ProductoModel> productosAux = productoRepository.findByNombreContainingIgnoreCase(nombre);
        List<DescripcionProductoDTO> productos = new ArrayList<>();

        for (ProductoModel productoAux : productosAux) {
            DescripcionProductoDTO producto = DescripcionProductoDTO.fromModel(productoAux);
            productos.add(producto);
        }

        return productos;
    }

}
