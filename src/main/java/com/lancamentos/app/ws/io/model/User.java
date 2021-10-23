package com.lancamentos.app.ws.io.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	private Long codigo;
	
	@Column(nullable = false, unique = true, length = 30)
	private String userCodigo;
	
	@Column(nullable = false, length = 30)
	private String apelido;
	
	@Column(nullable = false, length = 30)
	private String nome;
	
	@Column(nullable = false, length = 130)
	private String encryptedPassword;
	
	@Column(nullable = false)
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;

}


















