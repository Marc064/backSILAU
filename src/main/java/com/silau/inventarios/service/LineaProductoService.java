package com.silau.inventarios.service;

import com.silau.inventarios.model.LineaProductoModel;
import com.silau.inventarios.model.ProductoModel;
import com.silau.inventarios.repository.LineaProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineaProductoService {

    @Autowired
    private LineaProductoRepository lineaProductoRepository;

    public List<LineaProductoModel> findAll() {
        return lineaProductoRepository.findAll();
    }


    public LineaProductoModel findById(long id) {
        return lineaProductoRepository.findById(id).orElse(null);
    }

    public LineaProductoModel findByNombre(String nombre) {
        return lineaProductoRepository.findByNombreLineaContainingIgnoreCase(nombre);
    }

    public LineaProductoModel save(LineaProductoModel lineaProductoModel) {
        LineaProductoModel lineaSave = new LineaProductoModel();
        lineaSave.setNombreLinea(lineaProductoModel.getNombreLinea());
        return lineaProductoRepository.save(lineaSave);
    }


    public LineaProductoModel delete(long idLineaProducto) {
        LineaProductoModel lineaDelete = findById(idLineaProducto);
        lineaProductoRepository.delete(lineaDelete);
        return lineaDelete;
    }

}
