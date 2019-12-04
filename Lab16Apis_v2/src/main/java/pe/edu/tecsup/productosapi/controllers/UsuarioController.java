package pe.edu.tecsup.productosapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import pe.edu.tecsup.productosapi.entities.Usuario;
import pe.edu.tecsup.productosapi.services.UsuarioService;

@RestController
public class UsuarioController {
	
	@Autowired
	  public UsuarioService usuarioService;
	
	@GetMapping("/usuarios")
	public List<Usuario> usuarios() {	
		List<Usuario> usuarios = usuarioService.findAll();		
		return usuarios;
	}
	
	
	
	@PostMapping("/usuario/crear")
	public ResponseEntity<?>agregarUsuario(@RequestBody Usuario usuario){
		usuarioService.save(usuario);
	  return new ResponseEntity<Void>(HttpStatus.CREATED);
	  }
}
