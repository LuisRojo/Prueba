package com.prueba.mappers.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.prueba.dto.ArticuloDTO;
import com.prueba.mappers.ArticuloMapper;
import com.prueba.model.Articulo;
import com.prueba.model.Catalogo;

@Component
public class ArticuloMapperImpl implements ArticuloMapper {

	@Override
	public ArticuloDTO convertArticuloToDto(Articulo articulo) {
		if (articulo == null)
			return null;
		ArticuloDTO articuloDTO = new ArticuloDTO();
		articuloDTO.setIdArticulo(articulo.getIdArticulo());
		articuloDTO.setNombre(articulo.getNombre());
		articuloDTO.setPrecio(articulo.getPrecio());
		articuloDTO.setIdCatalogo(articulo.getCatalogo().getIdCatalogo());
		return articuloDTO;
	}

	@Override
	public Articulo convertArticuloFromDto(ArticuloDTO articuloDTO) {
		if (articuloDTO == null)
			return null;
		Articulo articulo = new Articulo();
		articulo.setIdArticulo(articuloDTO.getIdArticulo());
		articulo.setNombre(articuloDTO.getNombre());
		articulo.setPrecio(articuloDTO.getPrecio());
		Catalogo catalogo = new Catalogo();
		catalogo.setIdCatalogo(articuloDTO.getIdCatalogo());
		articulo.setCatalogo(catalogo);
		return articulo;
	}

	@Override
	public List<ArticuloDTO> convertArticuloListToDTO(List<Articulo> listaArticulos) {
		return listaArticulos.stream().map(this::convertArticuloToDto).collect(Collectors.toList());
	}

}
