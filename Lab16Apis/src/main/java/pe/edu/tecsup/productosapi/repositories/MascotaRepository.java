package pe.edu.tecsup.productosapi.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import pe.edu.tecsup.productosapi.entities.Mascota;


public interface MascotaRepository extends CrudRepository<Mascota, Long> {
     
	@Override
	List<Mascota> findAll();

}
