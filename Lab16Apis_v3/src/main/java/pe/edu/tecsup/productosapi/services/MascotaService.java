package pe.edu.tecsup.productosapi.services;

import java.util.List;

import pe.edu.tecsup.productosapi.entities.Mascota;

public interface MascotaService {
	
    public List<Mascota> findAll();
	
	public Mascota findById(Long id);
	
	public void save(Mascota mascota);
	
	public void deleteById(Long id);


}
