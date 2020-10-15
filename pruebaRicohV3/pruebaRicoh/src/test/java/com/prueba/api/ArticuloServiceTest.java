package com.prueba.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.prueba.dto.ArticuloDTO;
import com.prueba.mappers.ArticuloMapper;
import com.prueba.model.Articulo;
import com.prueba.model.Catalogo;
import com.prueba.persistence.ArticuloRepository;
import com.prueba.service.ArticuloService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticuloServiceTest {

	@Mock
	private ArticuloService articuloService;
	
	@Autowired
	private ArticuloMapper articuloMapper;
	
	@Mock
	private ArticuloRepository articuloRepository;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(articuloService);
	}
	
	@Test
	void addArticulo() {
		ArticuloDTO articuloDTO = new ArticuloDTO("Dante", 500, 1);
		Articulo articuloMock = articuloMapper.convertArticuloFromDto(articuloDTO);
		articuloMock.setIdArticulo(1);	
		Mockito.when(articuloService.save(articuloDTO)).thenReturn(articuloMapper.convertArticuloToDto(articuloMock));
		ArticuloDTO articuloDevuelto = articuloService.save(articuloDTO);
		assertEquals(articuloMock.getIdArticulo(), articuloDevuelto.getIdArticulo());
		assertEquals(articuloMock.getNombre(), articuloDevuelto.getNombre());
		assertEquals(articuloMock.getPrecio(), articuloDevuelto.getPrecio());
	}
	
	@Test
	void findArticulo() {
		int id = 2;
		ArticuloDTO articuloMock = new ArticuloDTO("pedro",15,1);
		articuloMock.setIdArticulo(id);
		Mockito.when(articuloService.get(id)).thenReturn(articuloMock);
		Articulo articulo = articuloMapper.convertArticuloFromDto(articuloService.get(id));
		assertEquals(articuloMock.getIdArticulo(), articulo.getIdArticulo());
		assertEquals(articuloMock.getNombre(), articulo.getNombre());
		assertEquals(articuloMock.getPrecio(), articulo.getPrecio());
		assertEquals(articuloMock.getIdCatalogo(), articulo.getCatalogo().getIdCatalogo());
	}
	
	@Test
	void deleteArticulo() {
		int id = 3;
		ArticuloDTO articuloMock = new ArticuloDTO("luis",30,2);
		articuloMock.setIdArticulo(id);
		Mockito.when(articuloService.delete(id)).thenReturn(articuloMock);
		ArticuloDTO articuloBorrado = articuloService.delete(id);
		assertEquals(articuloMock.getIdArticulo(), articuloBorrado.getIdArticulo());
		assertEquals(articuloMock.getNombre(), articuloBorrado.getNombre());
		assertEquals(articuloMock.getPrecio(), articuloBorrado.getPrecio());
		assertEquals(articuloMock.getIdCatalogo(), articuloBorrado.getIdCatalogo());
	}
	
	@Test
	void updateArticulo() {
		int id = 4;
		Articulo articuloUpdated = new Articulo(id,"modificado",27);
		articuloUpdated.setCatalogo(new Catalogo(3, "deportes", Collections.emptyList()));
		//Envio por vista
		ArticuloDTO articuloMockUpdated = new ArticuloDTO("modificado",27,3);
		articuloMockUpdated.setIdArticulo(id);
		//Esta en BD
		ArticuloDTO articuloMockBefore = new ArticuloDTO("previo",33,4);
		articuloMockBefore.setIdArticulo(id);
		
		Mockito.when(articuloService.get(id)).thenReturn(articuloMockBefore);
		Mockito.when(articuloService.save(articuloMockUpdated)).thenReturn(articuloMapper.convertArticuloToDto(articuloUpdated));
		
		ArticuloDTO articuloModificado = articuloService.save(articuloMockUpdated);
		assertEquals(articuloMockUpdated.getIdArticulo(), articuloModificado.getIdArticulo());
		assertEquals(articuloMockUpdated.getNombre(), articuloModificado.getNombre());
		assertEquals(articuloMockUpdated.getPrecio(), articuloModificado.getPrecio());
	}

}
