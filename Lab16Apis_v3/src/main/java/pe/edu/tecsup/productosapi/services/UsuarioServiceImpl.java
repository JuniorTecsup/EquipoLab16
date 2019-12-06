package pe.edu.tecsup.productosapi.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.tecsup.productosapi.entities.Usuario;
import pe.edu.tecsup.productosapi.repositories.UsuarioRepository;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public Usuario findByUsernameAndPassword(String correo, String clave) {
		return usuarioRepository.findByUsernameAndPassword(correo, clave);
	}
	
	@Override
	public Usuario findByUsername(String correo) {
		return usuarioRepository.findByUsername(correo);
	}
	
	@Override
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioRepository.findAll();
	}

	@Override
	public Usuario findById(Long id) {
		return usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No existe registro"));
	}

	@Override
	public void save(Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	@Override
	public void deleteById(Long id) {
		usuarioRepository.deleteById(id);
	}

}
