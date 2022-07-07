package br.com.avaliacao.pedidos.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.xml.bind.annotation.XmlRootElement;

import br.com.avaliacao.pedidos.model.Pedido;

@XmlRootElement
public class PedidoDto {

	private Long numeroControle;
	private LocalDate dataCadastro;
	private String nomeProduto;
	private BigDecimal valorUnitario;
	private int quantidadeProdutos;
	private Long codigoCliente;
	private BigDecimal valorTotal;
	
	public PedidoDto() {
	}
	
	public PedidoDto(Pedido pedidos) {
		this.numeroControle = pedidos.getNumeroControle();
		this.dataCadastro = pedidos.getDataCadastro();
		this.nomeProduto = pedidos.getNomeProduto();
		this.valorUnitario = pedidos.getValorUnitario();
		this.quantidadeProdutos = pedidos.getQuantidadeProdutos();
		this.codigoCliente = pedidos.getCliente().getCodigo();
		this.valorTotal = pedidos.getValorTotal();
		
	}
	
	public Long getNumeroControle() {
		return numeroControle;
	}
	public LocalDate getDataCadastro() {
		return dataCadastro;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public int getQuantidadeProdutos() {
		return quantidadeProdutos;
	}
	public Long getCodigoCliente() {
		return codigoCliente;
	}
	
}
