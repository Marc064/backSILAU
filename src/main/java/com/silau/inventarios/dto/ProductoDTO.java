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
    private double precio;
    private String tamano;
    private int cantidadExistente;
    private String descripcion;
    private String estado;
    private String linea_producto;

    public static ProductoDTO fromModel(ProductoModel producto){
        ProductoDTO dto = new ProductoDTO();

        dto.idProducto = producto.getIdProducto();
        dto.nombre = producto.getNombre();
        dto.imagen = producto.getImagen();
        dto.precio = producto.getPrecio();
        dto.tamano = producto.getTamano();
        dto.cantidadExistente = producto.getCantidadExistente();
        dto.descripcion = producto.getDescripcion();
        dto.estado = producto.getEstado();
        dto.linea_producto = producto.getLineaProducto().getNombreLinea();


        return dto;
    }

}
