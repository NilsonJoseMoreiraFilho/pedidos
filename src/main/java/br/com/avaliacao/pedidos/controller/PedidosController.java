package br.com.avaliacao.pedidos.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.avaliacao.pedidos.business.FacadeBusiness;
import br.com.avaliacao.pedidos.controller.dto.DtoFactory;
import br.com.avaliacao.pedidos.controller.dto.PedidoDto;
import br.com.avaliacao.pedidos.controller.form.PedidoForm;
import br.com.avaliacao.pedidos.exception.EntradaVaziaException;
import br.com.avaliacao.pedidos.model.ModelFactory;
import br.com.avaliacao.pedidos.model.Pedido;

@Validated
@RestController
@RequestMapping("/pedidos")
public class PedidosController {

	@Autowired
	private FacadeBusiness facadeBusiness;
	
	@Autowired
	private ModelFactory modelFactory;
	
	@Autowired
	private DtoFactory dtoFactory;
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<PedidoDto>> consultaPedidos(Long numeroControle, String dataCadastro) {
		
		LocalDate dataCadastroLocalDate = dataCadastro != null ? LocalDate.parse(dataCadastro): null;
		
		List<Pedido> pedidos = facadeBusiness.consultaPedidos(numeroControle, dataCadastroLocalDate);
		
		if(pedidos != null && pedidos.size() > 0) {
			return ResponseEntity.ok(dtoFactory.getDto(pedidos));
		} else {
			return ResponseEntity.noContent().build();
		}
		
	}
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Transactional
	public ResponseEntity<List<PedidoDto>> cadastrarPedidos(@RequestBody  List<@Valid PedidoForm> pedidosForm, UriComponentsBuilder uriBuilder) throws Exception {

		validaEntradaVazia(pedidosForm);
		
		facadeBusiness.verificaClientesPedidos(pedidosForm);
		
		List<Pedido> pedidos = modelFactory.getModel(pedidosForm);
		
		pedidos = facadeBusiness.cadastrarPedidos(pedidos);
		
		URI uri = uriBuilder.path("/pedidos").build().toUri();
		return ResponseEntity.created(uri).body(dtoFactory.getDto(pedidos));
	}

	private void validaEntradaVazia(List<PedidoForm> pedidosForm) throws EntradaVaziaException {
			if (pedidosForm == null || pedidosForm.isEmpty()) 
				throw new EntradaVaziaException("É necessário informar ao menos um pedido");
	}	
	
	
}
