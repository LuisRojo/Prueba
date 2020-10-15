package com.prueba.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.model.Catalogo;

public interface CatalogoRepository extends JpaRepository<Catalogo, Integer> {

}
