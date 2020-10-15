package com.prueba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.dto.ArticuloDTO;
import com.prueba.mappers.ArticuloMapper;
import com.prueba.model.Articulo;
import com.prueba.persistence.ArticuloRepository;

@Service("articuloService")
public class ArticuloService {

	@Autowired
	private ArticuloRepository repository;
	
	@Autowired
	private CatalogoService catalogoService;

	@Autowired
	private ArticuloMapper articuloMapper;
	
	

	public List<ArticuloDTO> listAll() {
		return articuloMapper.convertArticuloListToDTO(repository.findAll());
	}

	public ArticuloDTO save(ArticuloDTO articuloDTO) {
		if (articuloDTO.getIdArticulo() != null) {
			this.get(articuloDTO.getIdArticulo());
		}
		catalogoService.get(articuloDTO.getIdCatalogo());
		
		return articuloMapper.convertArticuloToDto(repository.save(articuloMapper.convertArticuloFromDto(articuloDTO)));
	}

	public ArticuloDTO get(Integer id) {
		return articuloMapper.convertArticuloToDto(repository.findById(id).get());
	}

	public ArticuloDTO delete(Integer id) {
		ArticuloDTO articuloBorrado = this.get(id);
		repository.deleteById(id);
		return articuloBorrado;
	}

	public List<Articulo> findByCatalogoIdCatalogo(Integer idCatalogo) {
		return repository.findByCatalogoIdCatalogo(idCatalogo);
	}

}
