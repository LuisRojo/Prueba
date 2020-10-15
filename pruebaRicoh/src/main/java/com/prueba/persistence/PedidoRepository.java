package com.prueba.persistence;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prueba.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

	@Modifying
	@Query(value = "insert into rel_articulos_pedidos (fk_articulo,fk_pedido) VALUES (:idArticulo,:idPedido)", nativeQuery = true)
	@Transactional
	void addArticuloToPedido(@Param("idArticulo") Integer idArticulo, @Param("idPedido") Integer idPedido);

}
