package com.silau.inventarios.dto;

import com.silau.inventarios.model.ClienteModel;
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

    public static ClienteDTO fromModel(ClienteModel cliente){

        ClienteDTO dto = new ClienteDTO();

        dto.idCliente = cliente.getIdCliente();
        dto.nombre = cliente.getNombre() + cliente.getApellido();
        dto.empresa = cliente.getEmpresa();
        dto.correo = cliente.getCorreo();
        dto.direccion = cliente.getDireccion();
        dto.celular = cliente.getCelular();
        dto.estado = cliente.isActivo();

        return dto;
    }

}
