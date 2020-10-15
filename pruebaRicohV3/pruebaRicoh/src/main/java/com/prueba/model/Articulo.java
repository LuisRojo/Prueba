package com.prueba.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "articulo")
public class Articulo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Articulo", insertable = false,updatable = false, nullable = false)
	private Integer idArticulo;

	@Column(name = "nombre", nullable = false,  updatable = true)
	private String nombre;

	@Column(name = "precio", nullable = false , updatable = true)
	private float precio;

	public Articulo() {
	}

	public Articulo(Integer id, String nombre, float precio) {
		this.idArticulo = id;
		this.nombre = nombre;
		this.precio = precio;
	}

	
	@ManyToOne
	@JoinColumn(name = "idCatalogo", nullable = true,updatable = true)
	private Catalogo catalogo;



	@JoinTable(name = "rel_articulos_pedidos", joinColumns = @JoinColumn(name = "fk_articulo"), inverseJoinColumns = @JoinColumn(name = "fk_pedido"))
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Pedido> pedidos;

	public Integer getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(Integer id) {
		this.idArticulo = id;
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

	public Catalogo getCatalogo() {
		return catalogo;
	}

	public void setCatalogo(Catalogo catalogo) {
		this.catalogo = catalogo;
	}

	@Override
	public String toString() {
		return "Articulo [idArticulo=" + idArticulo + ", nombre=" + nombre + ", precio=" + precio + ", catalogo="
				+ catalogo + ", pedidos=" + pedidos + "]";
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	
	
}


