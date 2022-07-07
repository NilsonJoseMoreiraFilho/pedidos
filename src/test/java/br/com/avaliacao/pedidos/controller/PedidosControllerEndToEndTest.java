package br.com.avaliacao.pedidos.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.avaliacao.pedidos.model.Cliente;
import br.com.avaliacao.pedidos.model.Pedido;
import br.com.avaliacao.pedidos.repository.PedidosRepository;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
@Sql("../reset-test-bd.sql")
class PedidosControllerEndToEndTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private PedidosRepository pedidosRepository;
	
	@Test
	void deveriaRetornar200ParaConsultaSemFiltro() throws Exception {
		URI uri = new URI("/pedidos");
		
		Pedido pedido = new Pedido(
				1L, 
				LocalDate.now(), 
				"Produto teste", 
				new BigDecimal("100"), 
				2, 
				new Cliente(1L, "Cliente Teste"), 
				new BigDecimal("200"));
		
		pedidosRepository.save(pedido);
		
		mockMvc
		.perform(MockMvcRequestBuilders.get(uri))
		.andExpect(
				MockMvcResultMatchers
				.status().is(200));
		
	}
	
	@Test
	void deveriaRetornar204ParaConsultaComFiltroNumeroControleInexistente() throws Exception {
		URI uri = new URI("/pedidos?numeroControle=70");
		
		mockMvc
		.perform(MockMvcRequestBuilders.get(uri))
		.andExpect(
				MockMvcResultMatchers
				.status().is(204));
		
	}
	
	@Test
	void deveriaRetornar200ParaConsultaComFiltroNumeroControleExistente() throws Exception {
		URI uri = new URI("/pedidos?numeroControle=99");
		
		Pedido pedido = new Pedido(
				99L, 
				LocalDate.now(), 
				"Produto teste", 
				new BigDecimal("100"), 
				2, 
				new Cliente(1L, "Cliente Teste"), 
				new BigDecimal("200"));
		
		pedidosRepository.save(pedido);
		
		mockMvc
		.perform(MockMvcRequestBuilders.get(uri))
		.andExpect(
				MockMvcResultMatchers
				.status().is(200));
		
	}
	
	@Test
	void deveriaRetornar204ParaConsultaComFiltroDataCadastroInexistente() throws Exception {
		URI uri = new URI("/pedidos?dataCadastro=2020-04-04");
		
		mockMvc
		.perform(MockMvcRequestBuilders.get(uri))
		.andExpect(
				MockMvcResultMatchers
				.status().is(204));
		
	}
	
	@Test
	void deveriaRetornar200ParaConsultaComFiltroDataCadastroExistente() throws Exception {
		URI uri = new URI("/pedidos?dataCadastro="+LocalDate.now().toString());
		
		Pedido pedido = new Pedido(
				99L, 
				LocalDate.now(), 
				"Produto teste", 
				new BigDecimal("100"), 
				2, 
				new Cliente(1L, "Cliente Teste"), 
				new BigDecimal("200"));
		
		pedidosRepository.save(pedido);
		
		mockMvc
		.perform(MockMvcRequestBuilders.get(uri))
		.andExpect(
				MockMvcResultMatchers
				.status().is(200));
		
	}

}
