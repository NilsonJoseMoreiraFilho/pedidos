package br.com.avaliacao.pedidos.controller.form;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PedidoForm {

	@NotNull
	private Long numeroControle;

	private LocalDate dataCadastro;

	@NotNull @NotEmpty
	private String nomeProduto;

	@NotNull 
	private BigDecimal valorUnitario;

	private Integer quantidadeProdutos;

	@NotNull
	private Long codigoCliente;
	
	public Long getNumeroControle() {
		return numeroControle;
	}
	public void setNumeroControle(Long numeroControle) {
		this.numeroControle = numeroControle;
	}
	public LocalDate getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public Integer getQuantidadeProdutos() {
		return quantidadeProdutos;
	}
	public void setQuantidadeProdutos(Integer quantidadeProdutos) {
		this.quantidadeProdutos = quantidadeProdutos;
	}
	public Long getCodigoCliente() {
		return codigoCliente;
	}
	public void setCodigoCliente(Long codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	
}
