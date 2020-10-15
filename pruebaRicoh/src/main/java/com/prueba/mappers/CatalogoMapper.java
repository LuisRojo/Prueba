package com.prueba.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.prueba.dto.CatalogoDTO;
import com.prueba.model.Catalogo;

@Mapper(componentModel = "spring")
public interface CatalogoMapper {

	CatalogoDTO convertCatalogoToDto(Catalogo catalogo);

	Catalogo convertCatalogoFromDto(CatalogoDTO catalogoDTO);
	
	List<CatalogoDTO> convertCatalogoListToDTO(List<Catalogo> listaCatalogos);
	
}
