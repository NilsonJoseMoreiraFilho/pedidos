package br.com.avaliacao.pedidos.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.avaliacao.pedidos.model.Pedido;

public interface PedidosRepository extends JpaRepository<Pedido, Long>{

	List<Pedido> findByNumeroControle(Long numeroControle);

	List<Pedido> findByDataCadastro(LocalDate dataCadastro);

}
