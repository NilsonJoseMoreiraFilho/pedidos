package br.com.avaliacao.pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.avaliacao.pedidos.model.Cliente;

public interface ClientesRepository extends JpaRepository<Cliente, Long>{

}
