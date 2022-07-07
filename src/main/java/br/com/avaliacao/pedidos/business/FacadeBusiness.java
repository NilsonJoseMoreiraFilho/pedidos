package br.com.avaliacao.pedidos.business;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.avaliacao.pedidos.controller.form.PedidoForm;
import br.com.avaliacao.pedidos.model.Pedido;

@Component
public class FacadeBusiness {

	@Autowired
	private PedidosBusiness pedidosBusiness;

	public List<Pedido> consultaPedidos(Long numeroControle, LocalDate dataCadastro) {
		return pedidosBusiness.consultaPedidos(numeroControle, dataCadastro);
	}
	
	public List<Pedido> cadastrarPedidos(List<Pedido> pedidos) throws Exception {
		return pedidosBusiness.cadastrarPedidos(pedidos);
	}
	
	public void verificaClientesPedidos(List<PedidoForm> pedidosForm) throws Exception {
		pedidosBusiness.verificaClientesPedidos(pedidosForm);
	}
	
}
