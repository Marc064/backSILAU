package com.silau.inventarios.dto;

import com.silau.inventarios.model.AdministradorModel;
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
public class ClienteEmpresaDTO {

    private String nombreCliente;
    private String apellidoCliente;
    private String celularCliente;
    private String correoCliente;
    private String nombreEmpresa;
    private String codigoEmpresa;
    private String direccionEmpresa;

    public static ClienteModel toClienteModel(ClienteEmpresaDTO dto, AdministradorModel administrador) {
        ClienteModel clienteModel = new ClienteModel();

        clienteModel.setNombre(dto.getNombreCliente());
        clienteModel.setApellido(dto.getApellidoCliente());
        clienteModel.setCorreo(dto.getCorreoCliente());
        clienteModel.setActivo(true);
        clienteModel.setCelular(dto.getCelularCliente());
        clienteModel.setAdministrador(administrador);

        return clienteModel;
    }

    public static ClienteModel toClienteModelUpdate(ClienteEmpresaDTO dto, ClienteModel clienteModel) {

        clienteModel.setNombre(dto.getNombreCliente());
        clienteModel.setApellido(dto.getApellidoCliente());
        clienteModel.setCorreo(dto.getCorreoCliente());
        clienteModel.setActivo(true);
        clienteModel.setCelular(dto.getCelularCliente());

        return clienteModel;
    }

    public static EmpresaModel toEmpresaModel(ClienteEmpresaDTO dto, ClienteModel cliente){
        EmpresaModel empresaModel = new EmpresaModel();

        empresaModel.setNombre(dto.getNombreEmpresa());
        empresaModel.setCodigo(dto.getCodigoEmpresa());
        empresaModel.setDireccion(dto.getDireccionEmpresa());
        empresaModel.setCliente(cliente);

        return empresaModel;
    }

    public static EmpresaModel toEmpresaModelUpdate(ClienteEmpresaDTO dto, EmpresaModel empresaModel){

        empresaModel.setNombre(dto.getNombreEmpresa());
        empresaModel.setCodigo(dto.getCodigoEmpresa());
        empresaModel.setDireccion(dto.getDireccionEmpresa());

        return empresaModel;
    }


}
