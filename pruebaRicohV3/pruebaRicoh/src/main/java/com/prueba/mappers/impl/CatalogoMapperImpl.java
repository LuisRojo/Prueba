package com.prueba.mappers.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prueba.dto.CatalogoDTO;
import com.prueba.mappers.ArticuloMapper;
import com.prueba.mappers.CatalogoMapper;
import com.prueba.model.Catalogo;

@Component
public class CatalogoMapperImpl implements CatalogoMapper {

	@Autowired
	ArticuloMapper articuloMapper;

	@Override
	public CatalogoDTO convertCatalogoToDto(Catalogo catalogo) {
		if (catalogo == null)
			return null;
		CatalogoDTO catalogoDTO = new CatalogoDTO();
		catalogoDTO.setIdCatalogo(catalogo.getIdCatalogo());
		catalogoDTO.setCategoria(catalogo.getCategoria());
		if (catalogo.getListaArticulos() != null) {
			catalogoDTO.setListaArticulos(articuloMapper.convertArticuloListToDTO(catalogo.getListaArticulos()));
		} else {
			catalogoDTO.setListaArticulos(Collections.emptyList());
		}
		return catalogoDTO;
	}

	@Override
	public Catalogo convertCatalogoFromDto(CatalogoDTO catalogoDTO) {
		if (catalogoDTO == null)
			return null;
		Catalogo catalogo = new Catalogo();
		catalogo.setIdCatalogo(catalogoDTO.getIdCatalogo());
		catalogo.setCategoria(catalogoDTO.getCategoria());
		return catalogo;
	}

	@Override
	public List<CatalogoDTO> convertCatalogoListToDTO(List<Catalogo> listaCatalogos) {
		return listaCatalogos.stream().map(this::convertCatalogoToDto).collect(Collectors.toList());
	}

}
