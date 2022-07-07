package br.com.avaliacao.pedidos.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import br.com.avaliacao.pedidos.model.Cliente;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
@Sql("../reset-test-bd.sql")
public class ClientesRepositoryTest {

	@Autowired
	private ClientesRepository repository;
	
	@Test
	void consultarClienteExistentePeloId() {
		
		Long id = 1L;
		Cliente cliente = repository.findById(id).get();
		assertNotNull(cliente);
		assertEquals(cliente.getCodigo(), id);
		
	}

	void consultarClienteNaoExistentePeloId() {
		
		Long id = 50L;
		Cliente cliente = repository.findById(id).get();
		assertNull(cliente);
		
	}
}
