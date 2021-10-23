package com.lancamentos.app.ws.execptionhandler;

import java.time.LocalDateTime;

public class Erro {

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
