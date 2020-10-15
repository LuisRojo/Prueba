package com.prueba.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.model.Articulo;

public interface ArticuloRepository extends JpaRepository<Articulo, Integer> {

	List<Articulo> findByCatalogoIdCatalogo(Integer id);
	
}
