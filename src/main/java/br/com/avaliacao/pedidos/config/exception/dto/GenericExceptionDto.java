package br.com.avaliacao.pedidos.config.exception.dto;

public class GenericExceptionDto {

	private String message;

	public String getMessage() {
		return message;
	}

	public GenericExceptionDto(String message) {
		this.message = message;
	}
	
}
