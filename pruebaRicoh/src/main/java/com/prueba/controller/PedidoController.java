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
import com.prueba.dto.PedidoDTO;
import com.prueba.service.PedidoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/pedido")
@Api(value = "Api con operaciones CRUD sobre: pedidos")
public class PedidoController {


	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping
	@ApiOperation(value = "Muestra la lista de pedidos")
	public List<PedidoDTO> list() {
		return pedidoService.listAll();
	}
	
	@PostMapping
	@ApiOperation(value = "Crea un nuevo pedido")
	public ResponseEntity<PedidoDTO> addPedido(@RequestBody PedidoDTO pedidoDTO) {
		 return new ResponseEntity<PedidoDTO>(pedidoService.save(pedidoDTO), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Actualiza un pedido existente")
	public ResponseEntity<PedidoDTO> updatePedido(@RequestBody PedidoDTO pedidoDTO) {
		try {
			return new ResponseEntity<PedidoDTO>(pedidoService.save(pedidoDTO), HttpStatus.OK);
		} catch(NoSuchElementException e) {
			return new ResponseEntity<PedidoDTO>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/{id}")
	@ApiOperation(value = "Añade articulos a un pedido existente")
	public ResponseEntity<PedidoDTO> addArticulosToPedido(@PathVariable Integer id,@RequestBody ArticuloDTO articuloDTO) {
		try {
			return new ResponseEntity<PedidoDTO>(pedidoService.addArticulosToPedido(id,articuloDTO),HttpStatus.OK);
		} catch(NoSuchElementException e) {
			return new ResponseEntity<PedidoDTO>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Borra un pedido existente pasado por identificador")
	public void delete(@PathVariable Integer id) {
		 pedidoService.delete(id);
	}
}
