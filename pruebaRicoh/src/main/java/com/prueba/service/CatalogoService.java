package com.prueba.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.dto.ArticuloDTO;
import com.prueba.dto.CatalogoDTO;
import com.prueba.mappers.CatalogoMapper;
import com.prueba.model.Catalogo;
import com.prueba.persistence.CatalogoRepository;

@Service
public class CatalogoService {

	@Autowired
	private CatalogoRepository repository;

	@Autowired
	private CatalogoMapper catalogoMapper;

	@Autowired
	private ArticuloService articuloService;

	public List<CatalogoDTO> listAll() {
		return catalogoMapper.convertCatalogoListToDTO(repository.findAll());
	}

	public CatalogoDTO save(CatalogoDTO catalogoDTO) {
		if (catalogoDTO.getIdCatalogo() != null) {
			Catalogo catalogo = get(catalogoDTO.getIdCatalogo());
			catalogo.setListaArticulos(
					Stream.concat(articuloService.findByCatalogoIdCatalogo(catalogo.getIdCatalogo()).stream(),
							catalogo.getListaArticulos().stream()).distinct().collect(Collectors.toList()));
		}
		return catalogoMapper.convertCatalogoToDto(repository.save(catalogoMapper.convertCatalogoFromDto(catalogoDTO)));
	}

	public Catalogo get(Integer id) {
		return repository.findById(id).get();
	}

	public CatalogoDTO delete(Integer id) {
		CatalogoDTO catalogoBorrado = catalogoMapper.convertCatalogoToDto(this.get(id));
		repository.deleteById(id);
		return catalogoBorrado;
	
	}
}
