package br.com.greenworld.Green.World.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.greenworld.Green.World.model.Relatorio;

@Repository
public interface IRelatorio extends JpaRepository<Relatorio, Long>{
	
}