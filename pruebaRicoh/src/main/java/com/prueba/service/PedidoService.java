package com.prueba.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.dto.ArticuloDTO;
import com.prueba.dto.PedidoDTO;
import com.prueba.mappers.PedidoMapper;
import com.prueba.model.Pedido;
import com.prueba.persistence.PedidoRepository;

@Service
@Transactional
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	@Autowired
	private PedidoMapper pedidoMapper;



	public List<PedidoDTO> listAll() {
		return pedidoMapper.convertPedidoListToDTO(repository.findAll());
	}

	public PedidoDTO save(PedidoDTO pedidoDTO) {
		if (pedidoDTO.getIdPedido() != null) {
			Pedido pedido = get(pedidoDTO.getIdPedido());
			if (pedido == null) {
				throw new NoSuchElementException();
			}
			pedido.setListaArticulos(Stream.concat(this.get(pedido.getIdPedido()).getListaArticulos().stream(),
					pedido.getListaArticulos().stream()).distinct().collect(Collectors.toList()));
		}
		return pedidoMapper.convertPedidoToDto(repository.save(pedidoMapper.convertPedidoFromDto(pedidoDTO)));
	}

	public Pedido get(Integer id) {
		return repository.findById(id).get();
	}

	public void delete(Integer id) {
		repository.deleteById(id);
	}

	public PedidoDTO addArticulosToPedido(Integer id, ArticuloDTO articuloDTO) {
		Pedido pedido = this.get(id);
		if (pedido == null || articuloDTO.getIdArticulo() == null)
			throw new NoSuchElementException();

		repository.addArticuloToPedido(articuloDTO.getIdArticulo(), id);
		return pedidoMapper.convertPedidoToDto(this.get(id));

	}
}
