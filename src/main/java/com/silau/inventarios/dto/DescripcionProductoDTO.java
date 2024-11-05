package com.silau.inventarios.dto;

import com.silau.inventarios.model.ProductoModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DescripcionProductoDTO {

    private long idProducto;
    private String nombre;
    private String imagen;
    private String descripcion;
    private int cantidad;

    public static DescripcionProductoDTO fromModel(ProductoModel producto) {
        DescripcionProductoDTO dto = new DescripcionProductoDTO();

        dto.idProducto = producto.getIdProducto();
        dto.nombre = producto.getNombre();
        dto.imagen = producto.getImagen();
        dto.descripcion = producto.getDescripcion();
        dto.cantidad = producto.getCantidadExistente();

        return dto;
    }

}
