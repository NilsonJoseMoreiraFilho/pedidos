package br.com.avaliacao.pedidos.business;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import br.com.avaliacao.pedidos.model.Pedido;
import br.com.avaliacao.pedidos.repository.PedidosRepository;


@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
class PedidosBusinessTest {
	
	
	@MockBean
	private PedidosRepository pedidosRepository;
	
	@Autowired
	private PedidosBusiness pedidosBusiness;

	@Test
	void retornarPedidosFiltrandoPorNumeroControle() {
		
		Long numeroControle = 1L;
		
		List<Pedido> pedidosMock = criaPedidos();
		
		Mockito.when(pedidosRepository.findByNumeroControle(ArgumentMatchers.eq(numeroControle))).thenReturn(pedidosMock);
		
		List<Pedido> pedidos = pedidosBusiness.consultaPedidos(numeroControle, null);
		
		assertNotNull(pedidos);
		
	}
	
	@Test
	void retornarPedidosFiltrandoPorDataCadastro() {
		
		LocalDate dataCadastro = LocalDate.now();
		
		List<Pedido> pedidosMock = criaPedidos();
		
		Mockito.when(pedidosRepository.findByDataCadastro(ArgumentMatchers.eq(dataCadastro))).thenReturn(pedidosMock);
		
		List<Pedido> pedidos = pedidosBusiness.consultaPedidos(null, dataCadastro);
		
		assertNotNull(pedidos);
		
	}
	
	@Test
	void retornarPedidosSemFiltros() {
		
		List<Pedido> pedidosMock = criaPedidos();
		
		Mockito.when(pedidosRepository.findAll()).thenReturn(pedidosMock);
		
		List<Pedido> pedidos = pedidosBusiness.consultaPedidos(null, null);
		
		assertNotNull(pedidos);
		
	}

	private List<Pedido> criaPedidos() {
		List<Pedido> pedidos = new ArrayList<Pedido>();
		return pedidos;
	}

}
