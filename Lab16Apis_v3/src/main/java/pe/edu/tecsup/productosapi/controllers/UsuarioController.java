package pe.edu.tecsup.productosapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.tecsup.productosapi.entities.Usuario;
import pe.edu.tecsup.productosapi.services.UsuarioService;

@RestController
@RequestMapping("/auth")
public class UsuarioController {
	
	@Autowired
	  public UsuarioService usuarioService;
	
	@PostMapping("/usuario/crear")
	public ResponseEntity<?>agregarUsuario(@RequestBody Usuario usuario){
		usuarioService.save(usuario);
	  return new ResponseEntity<Void>(HttpStatus.CREATED);
	  }
	
	
	
	@RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
	public ResponseEntity<?>agregarUsuario2(@RequestBody Usuario usuario) {
		usuarioService.save(usuario);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	  }
	
	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<String> eliminar(@PathVariable Long id) {	
		usuarioService.deleteById(id);	
		return ResponseEntity.ok().body("Registro eliminado");
	}
	
	@GetMapping("/usuarios/{id}")
	public Usuario obtener(@PathVariable Long id) {
		Usuario usuarios = usuarioService.findById(id);
		return usuarios;
	}
	
	@PostMapping("login")
	public Usuario login(@RequestParam String correo, @RequestParam String clave) throws Exception{
	
		Usuario usuario = usuarioService.findByUsernameAndPassword(correo, clave);
		
		if(usuario == null)
			throw new Exception("Usuario y/o clave invalido");
		return usuario;
	}
	
	
}
