package br.com.avaliacao.pedidos.config.exception.dto;

public class FormExceptionDto {

	private String campo;
	private String erro;
	
	public FormExceptionDto(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}

}
