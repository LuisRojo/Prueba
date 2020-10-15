package com.prueba.dto;

import java.util.List;

public class PedidoDTO {

	private Integer idPedido;

	private List<ArticuloDTO> listaArticulos;
	
	public PedidoDTO() {}

	public PedidoDTO(Integer idPedido, List<ArticuloDTO> listaArticulos) {
		super();
		this.idPedido = idPedido;
		this.listaArticulos = listaArticulos;
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public List<ArticuloDTO> getListaArticulos() {
		return listaArticulos;
	}

	public void setListaArticulos(List<ArticuloDTO> listaArticulos) {
		this.listaArticulos = listaArticulos;
	}
	
	
}
