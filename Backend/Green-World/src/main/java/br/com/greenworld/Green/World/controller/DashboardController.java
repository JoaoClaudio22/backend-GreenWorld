package br.com.greenworld.Green.World.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.greenworld.Green.World.DTO.UsuarioDTO;
import br.com.greenworld.Green.World.model.Relatorio;
import br.com.greenworld.Green.World.model.Residencia;
import br.com.greenworld.Green.World.model.Usuario;
import br.com.greenworld.Green.World.repository.IResidencia;
import br.com.greenworld.Green.World.repository.IUsuario;
import br.com.greenworld.Green.World.services.ResidenciaService;

@RestController
@CrossOrigin("*")
@RequestMapping("/dashboard")
public class DashboardController {
	
	@Autowired
	private IUsuario usuarioDao;
	
	@Autowired
	private IResidencia residenciaDao;
	
	@Autowired
	private ResidenciaService residenciaService;
	
	@GetMapping()
	public ResponseEntity<UserDetails> findUserById(@RequestBody UsuarioDTO usuario){
	    String login = usuario.login();
	    
	    
	    UserDetails userFound = usuarioDao.findByLogin(login);

	    if (userFound == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	    }

	    return ResponseEntity.ok(userFound);
	}
	
	

	@PostMapping("/cadastrar-residencia")
	public ResponseEntity<Residencia> cadastrarResidencia(@RequestBody Residencia residencia,@RequestParam String login){
		Usuario userOwner = (Usuario) usuarioDao.findByLogin(login);
		
		if(userOwner == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}else {
			
			Residencia newResidencia = residenciaService.cadastrarResidencia(residencia, login);
			if(newResidencia == null) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
			return ResponseEntity.status(HttpStatus.CREATED).body(newResidencia);
	
			}
	}
	
	
	@GetMapping("/residencias")
	public ResponseEntity<List<Residencia>> findAllResidencias(@RequestParam String login){
		return ResponseEntity.ok((List<Residencia>)residenciaService.findResidenciaByLogin(login)) ;
	}
	
	@GetMapping("/residencias/{id}")
	public ResponseEntity<Optional<Residencia>> findResidenciaById(@PathVariable Long id) {
	    Optional<Residencia> residencia = residenciaDao.findById(id);
	    if (residencia != null) {
	        return ResponseEntity.ok(residencia);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	  @PutMapping("/residencias/{id}")
	    public ResponseEntity<Residencia> updateResidencia(@PathVariable Long id, @RequestBody Residencia residenciaDetails) {
	        Optional<Residencia> residenciaOptional = residenciaDao.findById(id);

	        if (residenciaOptional.isPresent()) {
	            Residencia residencia = residenciaOptional.get();
	            residencia.setRua(residenciaDetails.getRua());
	            residencia.setBairro(residenciaDetails.getBairro());
	            residencia.setCidade(residenciaDetails.getCidade());
	            residencia.setEstado(residenciaDetails.getEstado());
	            residencia.setQntdMoradores(residenciaDetails.getQntdMoradores());
	            residencia.setAreaTelhado(residenciaDetails.getAreaTelhado());

	            Residencia updatedResidencia = residenciaDao.save(residencia);
	            return ResponseEntity.ok(updatedResidencia);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	
	  @DeleteMapping("/residencias/deletar={id}")
	    public ResponseEntity<Void> deleteResidenciaById(@PathVariable Long id) {
	        Optional<Residencia> residencia = residenciaDao.findById(id);
	        if (residencia.isPresent()) {
	            residenciaDao.deleteById(id);
	            return ResponseEntity.noContent().build();
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	

	@GetMapping("/relatorio")
	public ResponseEntity<List<Relatorio>> gerarRelatorio(@RequestParam String login) {
	    UserDetails userFound = usuarioDao.findByLogin(login);
	    
	    if (userFound == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    }
	    
	    Usuario userOwner = (Usuario) userFound;
	    
	    List<Residencia> residenciaList = userOwner.getResidenciaList();
	    
	    if (residenciaList == null || residenciaList.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    }
	    
	    List<Relatorio> relatorioList = new ArrayList<>();
	    
	    for (Residencia residencia : residenciaList) {
	        relatorioList.add(residencia.getRelatorio());
	    }
	    
	    return ResponseEntity.ok(relatorioList);
	}
	
	
	



	
	
}
