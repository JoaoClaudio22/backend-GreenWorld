package br.com.greenworld.Green.World.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.greenworld.Green.World.model.Usuario;
import br.com.greenworld.Green.World.repository.IUsuario;

@Service
public class UsuarioService {
	
	@Autowired
	private IUsuario usuarioDao;	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public List<Usuario> findAllUsers(){
		return (List<Usuario>)usuarioDao.findAll() ;
	}
	
	
	public Usuario saveUsers(@RequestBody Usuario usuario) {
	    try {
	       
	        Usuario existingUser = (Usuario) usuarioDao.findByLogin(usuario.getLogin());

	        if (existingUser != null) {	           
	            throw new DataIntegrityViolationException("Usuário com o login '" + usuario.getLogin() + "' já está cadastrado.");
	        }
	   
	        var passwordEncoded = passwordEncoder.encode(usuario.getPassword());
	        usuario.setPassword(passwordEncoded);
	        return usuarioDao.save(usuario);
	        
	    } catch (DataIntegrityViolationException e) {	        
	        throw new DataIntegrityViolationException("Erro ao salvar o usuário: " + e.getMessage());
	        
	    } catch (Exception e) {
	    	
	        throw new RuntimeException("Erro interno ao salvar o usuário: " + e.getMessage());
	    }
	}

	
	public Optional<Usuario> findById(@RequestBody Usuario usuario) {
		return usuarioDao.findById(usuario.getId());
	}
	
	
}
