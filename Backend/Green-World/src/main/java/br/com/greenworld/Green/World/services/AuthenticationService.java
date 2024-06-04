package br.com.greenworld.Green.World.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.greenworld.Green.World.repository.IUsuario;


@Service
public class AuthenticationService implements UserDetailsService {
	
	@Autowired
	private IUsuario usuarioDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return usuarioDao.findByLogin(username);
	}

}
