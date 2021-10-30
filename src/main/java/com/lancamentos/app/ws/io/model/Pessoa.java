package com.lancamentos.app.ws.io.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "pessoas")
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true, length = 30)
	private String pessoaCodigo;
	
	@NotNull
	@Size(min = 3, max = 30)
	@NotEmpty
	private String nome;
	
	@NotNull
	@Size(min = 3, max = 15)
	@NotEmpty
	private String apelido;
	
	@Embedded
	private Endereco endereco;
	
	@NotNull
	private boolean ativo;
	
	public boolean isInativo() {
		return !ativo;
	}
}

















