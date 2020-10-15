package com.prueba.dto;

import java.util.List;

public class CatalogoDTO {

	private Integer idCatalogo;

	private String categoria;

	private List<ArticuloDTO> listaArticulos;

	public CatalogoDTO() {
	}

	public CatalogoDTO(Integer idCatalogo, String categoria, List<ArticuloDTO> listaArticulos) {
		super();
		this.idCatalogo = idCatalogo;
		this.categoria = categoria;
		this.listaArticulos = listaArticulos;
	}

	public Integer getIdCatalogo() {
		return idCatalogo;
	}

	public void setIdCatalogo(Integer idCatalogo) {
		this.idCatalogo = idCatalogo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public List<ArticuloDTO> getListaArticulos() {
		return listaArticulos;
	}

	public void setListaArticulos(List<ArticuloDTO> listaArticulos) {
		this.listaArticulos = listaArticulos;
	}

}
