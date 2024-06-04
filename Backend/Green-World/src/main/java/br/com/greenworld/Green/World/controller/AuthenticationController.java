package br.com.greenworld.Green.World.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.greenworld.Green.World.DTO.AuthDTO;
import br.com.greenworld.Green.World.DTO.TokenDTO;
import br.com.greenworld.Green.World.model.Usuario;
import br.com.greenworld.Green.World.services.TokenService;

@RestController
@RequestMapping("/login")
@CrossOrigin("*")
public class AuthenticationController {
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<?> efetuarLogin(@RequestBody AuthDTO data){
		var token = new UsernamePasswordAuthenticationToken(data.login(), data.password());
		var auth = authenticationManager.authenticate(token);
		var tokenJWT = tokenService.createToken((Usuario) auth.getPrincipal());
		
		return ResponseEntity.ok(new TokenDTO(tokenJWT));
	}
	
	
	
}
