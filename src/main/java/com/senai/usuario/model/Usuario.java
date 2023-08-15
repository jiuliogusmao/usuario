package com.senai.usuario.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "nome é obrigatório.")
	private String nome;
	
	@NotBlank(message = "e-mail é obrigatório.")
	private String email;
	
	@NotBlank(message = "senha é obrigatória.")
	private String senha;
	private String imagem;

}
