package pe.edu.tecsup.productosapi.services;

import java.util.List;

import pe.edu.tecsup.productosapi.entities.Usuario;

public interface UsuarioService {
	
Usuario findByUsernameAndPassword(String correo, String clave);	

    Usuario findByUsername(String correo);

    public List<Usuario> findAll();
	
	public Usuario findById(Long id);
	
	public void save(Usuario usuario);
	
	public void deleteById(Long id);
	
	
}
