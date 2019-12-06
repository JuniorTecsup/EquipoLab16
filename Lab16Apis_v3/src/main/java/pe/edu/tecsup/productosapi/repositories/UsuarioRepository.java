package pe.edu.tecsup.productosapi.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import pe.edu.tecsup.productosapi.entities.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	/*@Override
	List<Usuario> findAll();*/
	
	@Query("SELECT u FROM Usuario u WHERE u.correo=:correo AND u.clave=:clave")
	Usuario findByUsernameAndPassword(@Param("correo") String correo, @Param("clave") String clave);

	@Query("SELECT u FROM Usuario u WHERE u.correo=:correo")
	Usuario findByUsername(@Param("correo") String correo);
}
