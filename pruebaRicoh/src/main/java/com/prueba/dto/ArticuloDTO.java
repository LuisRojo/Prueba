package com.prueba.dto;

public class ArticuloDTO {

	private Integer idArticulo;
	
	private String nombre;

	private float precio;

	private Integer idCatalogo;

	public ArticuloDTO() {
	}

	public ArticuloDTO(String nombre, float precio, int idCatalogo) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.idCatalogo = idCatalogo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public Integer getIdCatalogo() {
		return idCatalogo;
	}

	public void setIdCatalogo(Integer idCatalogo) {
		this.idCatalogo = idCatalogo;
	}

	public Integer getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(Integer idArticulo) {
		this.idArticulo = idArticulo;
	}

}
