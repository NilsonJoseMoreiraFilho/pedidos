package br.com.avaliacao.pedidos.config.exception;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.avaliacao.pedidos.config.exception.dto.FormExceptionDto;
import br.com.avaliacao.pedidos.config.exception.dto.GenericExceptionDto;
import br.com.avaliacao.pedidos.exception.EntradaVaziaException;
import br.com.avaliacao.pedidos.exception.GenericException;

@RestControllerAdvice
public class ExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
	public List<FormExceptionDto> handleValidationException(MethodArgumentNotValidException exception) {
		
		List<FormExceptionDto> dto = new ArrayList<FormExceptionDto>();
		
		exception.getBindingResult().getFieldErrors().forEach( e -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			dto.add(new FormExceptionDto(e.getField(), mensagem));
		});
		
		return dto;
		
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@org.springframework.web.bind.annotation.ExceptionHandler(EntradaVaziaException.class)
	public GenericExceptionDto handleEntradaVaziaException(Exception exception) {
		return new GenericExceptionDto(exception.getMessage());
	}
	
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@org.springframework.web.bind.annotation.ExceptionHandler(GenericException.class)
	public GenericExceptionDto handleGenericException(Exception exception) {
		return new GenericExceptionDto(exception.getMessage());
	}
	
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
	public GenericExceptionDto handleValidationException(ConstraintViolationException exception) {
		return new GenericExceptionDto(exception.getLocalizedMessage());
	}
	
}
