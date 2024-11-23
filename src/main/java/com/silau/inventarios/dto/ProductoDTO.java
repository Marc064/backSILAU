package com.silau.inventarios.dto;

import com.silau.inventarios.model.ProductoModel;
import com.silau.inventarios.utils.FileUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {

    private long idProducto;
    private String nombre;
    private MultipartFile imagen;

    public static ProductoDTO fromModel(ProductoModel producto) throws IOException {
        ProductoDTO dto = new ProductoDTO();

        MultipartFile imagenProducto = FileUtils.convertFileToMultipartFile(producto.getImagen());

        dto.idProducto = producto.getIdProducto();
        dto.nombre = producto.getNombre();
        dto.imagen = imagenProducto;

        return dto;
    }

}
