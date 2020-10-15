package com.prueba.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "catalogo")
public class Catalogo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Catalogo", updatable = false, nullable = false)
	private Integer idCatalogo;

	@Column(name = "categoria")
	private String categoria;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "idCatalogo")
	private List<Articulo> listaArticulos;

	public Integer getIdCatalogo() {
		return idCatalogo;
	}

	public void setIdCatalogo(Integer id) {
		this.idCatalogo = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public List<Articulo> getListaArticulos() {
		return listaArticulos;
	}

	public void setListaArticulos(List<Articulo> listaArticulos) {
		this.listaArticulos = listaArticulos;
	}

	@Override
	public String toString() {
		return "Catalogo [id=" + idCatalogo + ", categoria=" + categoria + ", listaArticulos=" + listaArticulos + "]";
	}

	public Catalogo(Integer idCatalogo, String categoria, List<Articulo> listaArticulos) {
		super();
		this.idCatalogo = idCatalogo;
		this.categoria = categoria;
		this.listaArticulos = listaArticulos;
	}

	public Catalogo() {}

}
