package com.silau.inventarios.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetallePedidoDTO {
    private ProductoDTO producto;
    private int cantidad;
    private double costo_total;
}
