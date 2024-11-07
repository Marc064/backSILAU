package com.silau.inventarios.dto;

import com.silau.inventarios.model.ClienteModel;
import com.silau.inventarios.model.EmpresaModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    private long idCliente;
    private String nombre;
    private String empresa;
    private String correo;
    private String direccion;
    private String celular;
    private boolean estado;

    public static ClienteDTO fromModel(ClienteModel cliente, EmpresaModel empresa){

        ClienteDTO dto = new ClienteDTO();

        dto.idCliente = cliente.getIdCliente();
        dto.nombre = cliente.getNombre() + cliente.getApellido();
        dto.empresa = empresa.getNombre();
        dto.correo = cliente.getCorreo();
        dto.direccion = empresa.getDireccion();
        dto.celular = cliente.getCelular();
        dto.estado = cliente.isActivo();

        return dto;
    }



}
