package br.com.avaliacao.pedidos.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cliente {
	
	public Cliente() {
	}
	
	public Cliente(Long codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}
	
	@Id
	private Long codigo;
	private String nome;
	
	public Long getCodigo() {
		return codigo;
	}
	public String getNome() {
		return nome;
	}
	
}
