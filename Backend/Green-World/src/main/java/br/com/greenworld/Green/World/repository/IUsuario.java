package br.com.greenworld.Green.World.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.greenworld.Green.World.model.Usuario;


@Repository
public interface IUsuario extends JpaRepository<Usuario, Integer>{
	
	public UserDetails findByLogin(String email);
}
