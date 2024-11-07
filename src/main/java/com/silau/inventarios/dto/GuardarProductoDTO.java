package com.silau.inventarios.dto;

import com.silau.inventarios.model.AdministradorModel;
import com.silau.inventarios.model.ProductoModel;
import com.silau.inventarios.service.AdministradorService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GuardarProductoDTO {

    private long idAdministrador;
    private String nombre;
    private String lineaProducto;
    private double precio;
    private String tamano;
    private int cantidad;
    private String estado;
    private String imagen;
    private String descripcion;


    public static ProductoModel toModel(GuardarProductoDTO dto) {
        ProductoModel model = new ProductoModel();

        model.setNombre(dto.getNombre());
        model.setLineaProducto(dto.getLineaProducto());
        model.setPrecio(dto.getPrecio());
        model.setTamano(dto.getTamano());
        model.setCantidadExistente(dto.getCantidad());
        model.setEstado(dto.getEstado());
        model.setImagen(dto.getImagen());
        model.setDescripcion(dto.getDescripcion());

        return model;
    }

}
