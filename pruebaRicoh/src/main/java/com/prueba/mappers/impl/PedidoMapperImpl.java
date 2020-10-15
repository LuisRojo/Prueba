package com.prueba.mappers.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prueba.dto.PedidoDTO;
import com.prueba.mappers.ArticuloMapper;
import com.prueba.mappers.PedidoMapper;
import com.prueba.model.Pedido;

@Component
public class PedidoMapperImpl implements PedidoMapper {

	@Autowired
	ArticuloMapper articuloMapper;
	
	@Override
	public PedidoDTO convertPedidoToDto(Pedido pedido) {
		if (pedido == null)
			return null;
		PedidoDTO pedidoDTO = new PedidoDTO();
		pedidoDTO.setIdPedido(pedido.getIdPedido());
		if(pedido.getListaArticulos() != null) {
			pedidoDTO.setListaArticulos(articuloMapper.convertArticuloListToDTO(pedido.getListaArticulos()));
		}else {
			pedidoDTO.setListaArticulos(Collections.emptyList());
		}
		return pedidoDTO;
	}

	@Override
	public Pedido convertPedidoFromDto(PedidoDTO pedidoDTO) {
		if (pedidoDTO == null)
			return null;
		Pedido pedido = new Pedido();
		pedido.setIdPedido(pedidoDTO.getIdPedido());
		return pedido;
	}
	
	@Override
	public List<PedidoDTO> convertPedidoListToDTO(List<Pedido> listaPedidos) {
		return listaPedidos.stream().map(this::convertPedidoToDto).collect(Collectors.toList());
	}

}
