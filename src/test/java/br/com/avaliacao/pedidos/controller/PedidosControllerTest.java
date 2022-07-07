package br.com.avaliacao.pedidos.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.avaliacao.pedidos.business.FacadeBusiness;
import br.com.avaliacao.pedidos.controller.dto.DtoFactory;
import br.com.avaliacao.pedidos.model.ModelFactory;
import br.com.avaliacao.pedidos.model.Pedido;

@WebMvcTest(PedidosController.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
class PedidosControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private FacadeBusiness facadeBusiness;
	
	@MockBean
	private ModelFactory modelFactory;
	
	@MockBean
	private DtoFactory dtoFactory;
	
	@Test
	void deveriaRetornar204ParaConsultaQueRetornaListaVazia() throws Exception {
		
		Long numeroControle = 1L;
		
		List<Pedido> pedidosMock = criaPedidos();
		
		Mockito.when(facadeBusiness.consultaPedidos(ArgumentMatchers.eq(numeroControle), ArgumentMatchers.eq(null))).thenReturn(pedidosMock);
		
		URI uri = new URI("/pedidos?numeroControle=200");
		mockMvc
		.perform(MockMvcRequestBuilders.get(uri))
		.andExpect(
				MockMvcResultMatchers.status().is(204)
				);
		
		
	}

	private List<Pedido> criaPedidos() {
		List<Pedido> pedidosMock = new ArrayList<Pedido>();
		return pedidosMock;
	}

}
