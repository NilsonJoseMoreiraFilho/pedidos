package br.com.avaliacao.pedidos.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.avaliacao.pedidos.model.Pedido;

@Component
public class DtoFactory {

	public PedidoDto getDto(Pedido pedido) {
		return new PedidoDto(pedido);
	}
	
	public List<PedidoDto> getDto(List<Pedido> pedidos) {
		return pedidos.stream().map(PedidoDto::new).collect(Collectors.toList());
	}
	
}
