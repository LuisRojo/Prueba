package com.prueba.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.prueba.dto.ArticuloDTO;
import com.prueba.model.Articulo;

@Mapper(componentModel = "spring")
public interface ArticuloMapper {

	ArticuloDTO convertArticuloToDto(Articulo articulo);

	Articulo convertArticuloFromDto(ArticuloDTO articuloDTO);
	
	List<ArticuloDTO> convertArticuloListToDTO(List<Articulo> listaArticulos);

}
