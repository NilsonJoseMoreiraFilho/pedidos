package br.com.avaliacao.pedidos.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.avaliacao.pedidos.controller.form.PedidoForm;
import br.com.avaliacao.pedidos.repository.ClientesRepository;

@Component
public class ModelFactory {
	
	@Autowired
	private ClientesRepository clientesRepository;

	public List<Pedido> getModel(List<PedidoForm> pedidosForm) {
		
		List<Pedido> pedidos = new ArrayList<Pedido>();
				
				pedidosForm.forEach(p -> {
					
					//Preenchendo valores opcionais
					p.setDataCadastro(p.getDataCadastro() != null ? p.getDataCadastro() : LocalDate.now());
					p.setQuantidadeProdutos(p.getQuantidadeProdutos() != null ? p.getQuantidadeProdutos() : 1);
					
					pedidos.add(new Pedido(p,clientesRepository.findById(p.getCodigoCliente()).get()));
				});
		
		return pedidos;
	}
	
}
