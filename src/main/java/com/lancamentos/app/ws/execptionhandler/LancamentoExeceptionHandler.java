package com.lancamentos.app.ws.execptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class LancamentoExeceptionHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String mensagemUsuario = messageSource
				.getMessage("mensagem.invalida", null,LocaleContextHolder.getLocale());
		
		String mensagemDesenvolvidor = ex.getCause().getMessage();
		
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvidor, HttpStatus.BAD_REQUEST.value()));
		
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Erro> erros = criarLista(ex.getBindingResult(), HttpStatus.BAD_REQUEST.value());
		
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	private List<Erro> criarLista(BindingResult bindingResult, int status){
		List<Erro> erros = new ArrayList<>();
		
		for(FieldError fieldError : bindingResult.getFieldErrors()) {
			String mensagemUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String mensagemDesenvolvidor = fieldError.toString();
			erros.add(new Erro(mensagemUsuario, mensagemDesenvolvidor, status));
		}
		
		return erros;
	}
	
	public class Erro{
		
		private String mensagemUsuario;
		private String mensagemDesenvolvidor;
		private LocalDateTime dateTime;
		private int status;
		
		public Erro(String mensagemUsuario, String mensagemDesenvolvidor, int status) {
			this.status = status;
			dateTime = LocalDateTime.now();
			this.mensagemUsuario = mensagemUsuario;
			this.mensagemDesenvolvidor = mensagemDesenvolvidor;
			
		}
		
		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public String getMensagemUsuario() {
			return mensagemUsuario;
		}
		
		public void setMensagemUsuario(String mensagemUsuario) {
			this.mensagemUsuario = mensagemUsuario;
		}
		
		public LocalDateTime getDateTime() {
			return dateTime;
		}

		public void setDateTime(LocalDateTime dateTime) {
			this.dateTime = dateTime;
		}

		public String getMensagemDesenvolvidor() {
			return mensagemDesenvolvidor;
		}
		
		public void setMensagemDesenvolvidor(String mensagemDesenvolvidor) {
			this.mensagemDesenvolvidor = mensagemDesenvolvidor;
		}
	}
	
}
