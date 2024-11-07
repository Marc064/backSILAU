package com.silau.inventarios.dto;

import com.silau.inventarios.model.ProductoModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GuardarProductoDTO {

    private long idProducto;
    private long idAdministrador;
    private String nombre;
    private long idLineaProducto;
    private double precio;
    private String tamano;
    private int cantidad;
    private String estado;
    private String imagen;
    private String descripcion;


    public static ProductoModel toModel(GuardarProductoDTO dto) {
        ProductoModel model = new ProductoModel();

        model.setNombre(dto.getNombre());
        model.setPrecio(dto.getPrecio());
        model.setTamano(dto.getTamano());
        model.setCantidadExistente(dto.getCantidad());
        model.setEstado(dto.getEstado());
        model.setImagen(dto.getImagen());
        model.setDescripcion(dto.getDescripcion());

        return model;
    }

    public static ProductoModel toModelSave(GuardarProductoDTO dto, ProductoModel model) {
        model.setNombre(dto.getNombre());
        model.setPrecio(dto.getPrecio());
        model.setTamano(dto.getTamano());
        model.setCantidadExistente(dto.getCantidad());
        model.setEstado(dto.getEstado());
        model.setImagen(dto.getImagen());
        model.setDescripcion(dto.getDescripcion());

        return model;
    }

}
