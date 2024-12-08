package com.silau.inventarios.dto;

import com.silau.inventarios.model.LineaProductoModel;
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
    private String linea;
    private String tamano;
    private double precio;
    private int cantidad;
    private String estado;


    public static ProductoDTO fromModel(ProductoModel producto, LineaProductoModel lineaProducto){
        ProductoDTO dto = new ProductoDTO();

        dto.idProducto = producto.getIdProducto();
        dto.nombre = producto.getNombre();
        dto.imagen = producto.getImagen();
        dto.linea = lineaProducto.getNombreLinea();
        dto.tamano = producto.getTamano();
        dto.precio = producto.getPrecio();
        dto.cantidad = producto.getCantidadExistente();
        dto.estado = producto.getEstado();

        return dto;
    }

}
