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
public class ProductoDTO {

    private long idProducto;
    private String nombre;
    private String imagen;

    public static ProductoDTO fromModel(ProductoModel producto) {
        ProductoDTO dto = new ProductoDTO();

        dto.idProducto = producto.getIdProducto();
        dto.nombre = producto.getNombre();
        dto.imagen = producto.getImagen();

        return dto;
    }

}
