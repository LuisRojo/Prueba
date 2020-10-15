package com.prueba.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.prueba.dto.PedidoDTO;
import com.prueba.model.Pedido;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

	PedidoDTO convertPedidoToDto(Pedido pedido);

	Pedido convertPedidoFromDto(PedidoDTO pedidoDTO);
	
	List<PedidoDTO> convertPedidoListToDTO(List<Pedido> listaPedidos);

	
}
