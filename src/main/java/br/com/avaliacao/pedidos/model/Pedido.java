package br.com.avaliacao.pedidos.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.avaliacao.pedidos.controller.form.PedidoForm;

@Entity
public class Pedido {

	@Id
	private Long numeroControle;
	private LocalDate dataCadastro;
	private String nomeProduto;
	private BigDecimal valorUnitario;
	private int quantidadeProdutos;
	@ManyToOne
	private Cliente cliente;
	private BigDecimal valorTotal;
	
	public Pedido() {
	}

	public Pedido(PedidoForm pedidosForm, Cliente cliente) {
		this.numeroControle = pedidosForm.getNumeroControle();
		this.dataCadastro = pedidosForm.getDataCadastro() != null ? pedidosForm.getDataCadastro() : LocalDate.now();
		this.nomeProduto = pedidosForm.getNomeProduto();
		this.valorUnitario = pedidosForm.getValorUnitario();
		this.quantidadeProdutos = pedidosForm.getQuantidadeProdutos() != null ? pedidosForm.getQuantidadeProdutos() : 1;
		this.cliente = cliente;
	}
	
	
	
	public Pedido(Long numeroControle, LocalDate dataCadastro, String nomeProduto, BigDecimal valorUnitario,
			int quantidadeProdutos, Cliente cliente, BigDecimal valorTotal) {
		this.numeroControle = numeroControle;
		this.dataCadastro = dataCadastro;
		this.nomeProduto = nomeProduto;
		this.valorUnitario = valorUnitario;
		this.quantidadeProdutos = quantidadeProdutos;
		this.cliente = cliente;
		this.valorTotal = valorTotal;
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
	public int getQuantidadeProdutos() {
		return quantidadeProdutos;
	}
	public Cliente getCliente() {
		return cliente;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public void setNumeroControle(Long numeroControle) {
		this.numeroControle = numeroControle;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public void setQuantidadeProdutos(int quantidadeProdutos) {
		this.quantidadeProdutos = quantidadeProdutos;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}
