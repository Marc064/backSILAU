package com.silau.inventarios.service;

import com.silau.inventarios.dto.AgregarPedidoDTO;
import com.silau.inventarios.dto.PedidosDTO;
import com.silau.inventarios.model.*;
import com.silau.inventarios.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private DetallePedidoService detallePedidoService;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private AdministradorService administradorService;

    @Autowired
    private ProductoService productoService;


    public List<PedidosDTO> findAll(){
        List<PedidoModel> pedidos = pedidoRepository.findAll();
        List<PedidosDTO> pedidosDTO = new ArrayList<>();
        for (PedidoModel pedidoModel : pedidos) {
            DetallePedidoModel detallePedido = detallePedidoService.findByPedido(pedidoModel);
            EmpresaModel empresa = empresaService.findByCliente(pedidoModel.getCliente());
            PedidosDTO pedido = PedidosDTO.fromModels(pedidoModel, empresa, pedidoModel.getCliente(), detallePedido.getProducto(), detallePedido);
            pedidosDTO.add(pedido);
        }

        return pedidosDTO;
    }

    public AgregarPedidoDTO save(AgregarPedidoDTO agregarPedidoDTO, long idAdministrador) {

        AdministradorModel administrador = administradorService.findById(idAdministrador);
        ClienteModel cliente = empresaService.findByEmpresa(agregarPedidoDTO.getIdEmpresa());
        ProductoModel producto = productoService.findById(agregarPedidoDTO.getProducto().getIdProducto());
        int cantida = agregarPedidoDTO.getProducto().getCantidad();
        double total = producto.getPrecio()*cantida;

        PedidoModel pedidoModel = AgregarPedidoDTO.toModelPedido(agregarPedidoDTO, cliente, administrador, total);

        PedidoModel pedidoGuardado= pedidoRepository.save(pedidoModel);

            DetallePedidoModel detalle = AgregarPedidoDTO.toDetallePedido(cantida, pedidoGuardado, producto);
            detallePedidoService.save(detalle);

        return agregarPedidoDTO;
    }

    public AgregarPedidoDTO update(long idPedido, AgregarPedidoDTO pedidoNuevo){
        PedidoModel pedidoModel = pedidoRepository.findById(idPedido).get();
        DetallePedidoModel detallePedido = detallePedidoService.findByPedido(pedidoModel);
        ClienteModel cliente = empresaService.findByEmpresa(pedidoNuevo.getIdEmpresa());
        ProductoModel producto = productoService.findById(pedidoNuevo.getProducto().getIdProducto());

        int cantida = pedidoNuevo.getProducto().getCantidad();
        double total = detallePedido.getProducto().getPrecio()*cantida;

        PedidoModel pedidoGuardar = AgregarPedidoDTO.saveModelPedido(pedidoNuevo, cliente, pedidoModel, total);
        pedidoRepository.save(pedidoGuardar);

        DetallePedidoModel detallePedidoSave = AgregarPedidoDTO.saveDetallePedido(cantida, pedidoGuardar, producto, detallePedido);
        detallePedidoService.save(detallePedidoSave);


        return pedidoNuevo;
    }

    public PedidoModel delete(long idPedido){

        PedidoModel pedido = pedidoRepository.findById(idPedido).orElse(null);

        pedidoRepository.delete(pedido);

        return pedido;
    }


}
