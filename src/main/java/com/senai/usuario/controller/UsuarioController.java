package com.senai.usuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.senai.usuario.model.Usuario;
import com.senai.usuario.repository.UsuarioRepository;

import jakarta.validation.Valid;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("/")
	public String paginaPrincipal() {
		return "index";
	}
	
	@GetMapping("/usuarios")
	public String listarUsuarios(Model model) {
		List<Usuario> usuarios = usuarioRepository.findAll();
		model.addAttribute("usuarios", usuarios);
		return "usuarios";
	}
	
	@GetMapping("/cadastrarUsuario")
	public String paginaAdicionarUsuario(Usuario usuario) {
		return "adicionar_usuario";
	}
	
	@PostMapping("/adicionarUsuario")
	public String adicionarUsuario(@Valid Usuario usuario, 
			Errors erros, BindingResult result, Model model) {
		if(result.hasErrors() || (null != erros && erros.getErrorCount() > 0)) {
			return "adicionar_usuario";
		}
		usuarioRepository.save(usuario);
		return "redirect:/usuario";
	}
	
	@GetMapping("/editar/{id}")
	public String paginaAtualizarUsuario(
			@PathVariable("id") long id, Model model) {
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> 
				new IllegalArgumentException("Identificador do usuario é inválido" + id));
		
		model.addAttribute("usuario", usuario);
		return "editar_usuario";
	}
	
	@PostMapping("/atualizar/{id}")
	public String atualizarProduto(@PathVariable("id") long id,
			@Valid Usuario usuario, BindingResult result,
			Model model) {
		if(result.hasErrors()) {
			usuario.setId(id);
			return "editar_usuario";
		}
		usuarioRepository.save(usuario);
		return "redirect:/usuarios";
	}
	
}
