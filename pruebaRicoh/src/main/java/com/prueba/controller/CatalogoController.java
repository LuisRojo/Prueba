package com.prueba.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.dto.CatalogoDTO;
import com.prueba.service.CatalogoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/catalogo")
@Api(value = "Api con operaciones CRUD sobre: catalogos")
public class CatalogoController {

	@Autowired
	private CatalogoService catalogoService;

	@GetMapping
	@ApiOperation(value = "Muestra la lista de catalogos existentes con sus artículos")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se muestra la lista de catálogos existentes en la base de datos") })
	public List<CatalogoDTO> list() {
		return catalogoService.listAll();
	}

	@PostMapping
	@ApiOperation(value = "Crea un catalogo de productos", notes = "No es necesario establecer un ID ya que la base de datos lo hace por defecto")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Se añade el catálogo a la base de datos") })
	public ResponseEntity<List<CatalogoDTO>> addCatalogo(@RequestBody List<CatalogoDTO> catalogoDTO) {
		catalogoDTO.forEach((CatalogoDTO art) -> catalogoService.save(art));
		return new ResponseEntity<List<CatalogoDTO>>(this.list(), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Actualiza un catálogo existente en base datos")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se actualiza el catálogo indicado y se muestra por pantalla el articulo actualizado"),
			@ApiResponse(code = 404, message = "El catálogo con el id solicitado no se encuentra en base de datos") })
	public ResponseEntity<CatalogoDTO> updateCatalogo(@RequestBody CatalogoDTO catalogoDTO) {
		try {
			return new ResponseEntity<CatalogoDTO>(catalogoService.save(catalogoDTO), HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<CatalogoDTO>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Borra un catálogo pasado su id como parámetro en el caso de que exista en base de datos")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se borra el catálogo indicado y se muestra por pantalla el catálogo borrado"),
			@ApiResponse(code = 404, message = "El catálogo con el id pasado por parámetro no se encuentra en base de datos") })
	public ResponseEntity<CatalogoDTO> delete(@PathVariable Integer id) {
		try {
			return new ResponseEntity<CatalogoDTO>(catalogoService.delete(id), HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<CatalogoDTO>(HttpStatus.NOT_FOUND);
		}
	}
}
