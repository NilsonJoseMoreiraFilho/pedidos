package br.com.avaliacao.pedidos.business;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.avaliacao.pedidos.controller.form.PedidoForm;
import br.com.avaliacao.pedidos.exception.GenericException;
import br.com.avaliacao.pedidos.model.Pedido;
import br.com.avaliacao.pedidos.repository.ClientesRepository;
import br.com.avaliacao.pedidos.repository.PedidosRepository;

@Component
public class PedidosBusiness {
	
	@Autowired
	private PedidosRepository pedidosRepository;
	
	@Autowired
	private ClientesRepository clientesRepository;

	public List<Pedido> consultaPedidos(Long numeroControle, LocalDate dataCadastro) {
		
		List<Pedido> pedidos = null;
		
		if (numeroControle != null) {
			pedidos =  pedidosRepository.findByNumeroControle(numeroControle);
		}else if (dataCadastro != null) {
			pedidos =  pedidosRepository.findByDataCadastro(dataCadastro);
		} else {
			pedidos = pedidosRepository.findAll();
		}
		
		return pedidos;
		
	}
	
	public List<Pedido> cadastrarPedidos(List<Pedido> pedidos) throws Exception {
		
		validaPedidos(pedidos);
		
		pedidos = atualizaValoresTotais(pedidos);
		
		pedidosRepository.saveAll(pedidos);
		
		return pedidos;
		
	}
	
	public void verificaClientesPedidos(List<PedidoForm> pedidosForm) throws Exception {	
		List<Long> clientesNaoCadastrados = new ArrayList<Long>();
		
		pedidosForm.forEach(p -> {
			if (!clientesRepository.existsById(p.getCodigoCliente())) {
				clientesNaoCadastrados.add(p.getCodigoCliente());
			}
		});
		
		if (clientesNaoCadastrados.size() > 0) {
			throw new GenericException("Clientes não cadastrados:" + clientesNaoCadastrados.toString());
		}
		
	}
	
	private void validaPedidos(List<Pedido> pedidos) throws Exception {
		
		validaNumeroControle(pedidos);
		
		verificaQuantidadePedidos(pedidos);
	}

	private void validaNumeroControle(List<Pedido> pedidos) throws Exception{
		
		List<Long> numerosControle = new ArrayList<Long>();
		ArrayList<Long> controlesDuplicados = new ArrayList<Long>();
		ArrayList<Long> controlesJaCadastrados = new ArrayList<Long>();
		
		pedidos.forEach(p -> {
			
			if (numerosControle.contains(p.getNumeroControle())) {
				controlesDuplicados.add(p.getNumeroControle());
			}
			
			numerosControle.add(p.getNumeroControle());
			
			if (pedidosRepository.existsById(p.getNumeroControle())) {
				controlesJaCadastrados.add(p.getNumeroControle());
			}
			
		});
		
		
		if(!controlesDuplicados.isEmpty()) {
			throw new GenericException("Os números de controle " + controlesDuplicados + " foram enviados duplicados. É necessário enviar um número de controle único para cada pedido.");
		}
		
		if (!controlesJaCadastrados.isEmpty()) {
			throw new GenericException("Os números de controle " + controlesJaCadastrados + " já estão cadastrados na base");
		}
	}

	private List<Pedido> atualizaValoresTotais(List<Pedido> pedidos) {
		
		pedidos.forEach(p -> {
			if(p.getQuantidadeProdutos() >= 10) {
				p.setValorTotal(calculaValorTotal(p.getValorUnitario(), new BigDecimal(p.getQuantidadeProdutos()), new BigDecimal("0.9")));
			} else if (p.getQuantidadeProdutos() > 5) {
				p.setValorTotal(calculaValorTotal(p.getValorUnitario(), new BigDecimal(p.getQuantidadeProdutos()), new BigDecimal("0.95")));
			} else {
				p.setValorTotal(calculaValorTotal(p.getValorUnitario(), new BigDecimal(p.getQuantidadeProdutos()), new BigDecimal("1")));
			}
		});
		
		return pedidos;
		
	}
	
	private BigDecimal calculaValorTotal(BigDecimal valorUnitario, BigDecimal quantidade, BigDecimal percentual) {
		return (valorUnitario.multiply(quantidade)).multiply(percentual);
	}

	private void verificaQuantidadePedidos(List<Pedido> pedidos) throws Exception {

		if (pedidos.size() > 10) {
			throw new GenericException("Devem ser informados no máximo 10 pedidos por requisição");
		}
		
	}

	
}
