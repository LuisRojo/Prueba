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

import com.prueba.dto.ArticuloDTO;
import com.prueba.service.ArticuloService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/articulo")
@Api(value = "Api con operaciones CRUD sobre: artículos")
public class ArticuloController {

	@Autowired
	private ArticuloService articuloService;

	@GetMapping
	@ApiOperation(value = "Muestra la lista los articulos existentes")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se muestra la lista de artículos existentes en la base de datos") })
	public List<ArticuloDTO> list() {
		return articuloService.listAll();
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Borra un artículo pasado su id como parámetro en el caso de que exista en base de datos")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se borra el articulo indicado y se muestra por pantalla el articulo borrado"),
			@ApiResponse(code = 404, message = "El artículo con el id pasado por parámetro no se encuentra en base de datos") })
	public ResponseEntity<ArticuloDTO> delete(@PathVariable Integer id) {
		try {
			return new ResponseEntity<ArticuloDTO>(articuloService.delete(id), HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<ArticuloDTO>(HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping
	@ApiOperation(value = "A�ade art�culos a la base de datos", notes = "No es necesario establecer un ID ya que la base de datos lo hace por defecto")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Se añade el artículo a la base de datos"),
			@ApiResponse(code = 400, message = "La Request no es correcta, revise que no ha introducido un id de artículo y que el id del catálogo está presente en base de datos") })
	public ResponseEntity<List<ArticuloDTO>> addArticulo(@RequestBody List<ArticuloDTO> articuloDTO) {
		try {
			articuloDTO.forEach((ArticuloDTO art) -> articuloService.save(art));
		} catch (NoSuchElementException e) {
			return new ResponseEntity<List<ArticuloDTO>>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<ArticuloDTO>>(this.list(), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Actualiza un artículo existente en base datos", notes = "El id del catálogo debe de existir en base de datos")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se actualiza el articulo indicado y se muestra por pantalla el articulo actualizado"),
			@ApiResponse(code = 404, message = "El artículo con el id solicitado no se encuentra en base de datos") })
	public ResponseEntity<ArticuloDTO> updateArticulo(@RequestBody ArticuloDTO articulo) {
		try {
			return new ResponseEntity<ArticuloDTO>(articuloService.save(articulo), HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<ArticuloDTO>(HttpStatus.NOT_FOUND);
		}
	}

}
