package com.lancamentos.app.ws.io.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true, length = 30)
	private String userCodigo;
	
	@NotBlank
	@Column(nullable = false, length = 30)
	private String apelido;
	
	@NotBlank
	@Column(nullable = false, length = 30)
	private String nome;
	
	@NotBlank
	@Column(nullable = false, unique = true, length = 75)
	private String email;
	
	@NotBlank
	@Column(nullable = false, length = 130, name = "encryptedPassword")
	private String password;
	
	@Column(nullable = false)
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;
	
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(name = "users_groups", 
	joinColumns = @JoinColumn(name = "users_id"),
	inverseJoinColumns = @JoinColumn(name = "groups_id"))
	private List<Grupo> grupo;

}


















