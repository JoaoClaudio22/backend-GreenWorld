package br.com.greenworld.Green.World.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.greenworld.Green.World.model.Usuario;
import br.com.greenworld.Green.World.services.UsuarioService;

@RestController
@CrossOrigin("*")
@RequestMapping("/usuarios")
public class UsuarioController {
		
	@Autowired
	private UsuarioService usuarioService;
	
	
	@GetMapping()
	public ResponseEntity<List<Usuario>>  findAllUser(){		
		return ResponseEntity.status(200).body(usuarioService.findAllUsers());
	}
	
	
	@PostMapping()
	public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario) {
		return ResponseEntity.status(201).body(usuarioService.saveUsers(usuario));
	}
	



	
	
	
	
	

	
	
	
	
	
	
}
