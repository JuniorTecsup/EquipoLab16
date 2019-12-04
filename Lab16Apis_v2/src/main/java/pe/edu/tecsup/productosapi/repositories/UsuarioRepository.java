package pe.edu.tecsup.productosapi.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import pe.edu.tecsup.productosapi.entities.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	@Override
	List<Usuario> findAll();
}
