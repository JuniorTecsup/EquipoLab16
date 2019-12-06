package pe.edu.tecsup.productosapi.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import pe.edu.tecsup.productosapi.entities.Mascota;
import pe.edu.tecsup.productosapi.services.MascotaService;

@RestController
public class MascotaController {
	
private static final Logger logger = LoggerFactory.getLogger(MascotaController.class);
	
	@Value("${app.storage.path}")
	private String STORAGEPATH;
	
	@Autowired
	private MascotaService mascotaService;
	
	@GetMapping("/mascotas")
	public List<Mascota> mascotas() {
		logger.info("call mascotas");
		
		List<Mascota> mascotas = mascotaService.findAll();
		logger.info("mascotas: " + mascotas);
		
		return mascotas;
	}
	
	@GetMapping("/mascotas/{id}")
	public Mascota obtener(@PathVariable Long id) {
		
		Mascota mascota = mascotaService.findById(id);
			
		return mascota;
	}
	
	
	@PostMapping("/mascotas")
	public Mascota crear(@RequestParam(name="foto", required=false) MultipartFile foto, @RequestParam("nombres") String nombres, @RequestParam("raza") String raza, @RequestParam("edad") String edad, @RequestParam("usuario_id") String usuario_id) throws Exception {
		logger.info("call crear(" + nombres + ", " + raza + ", " + edad + ", " + usuario_id + ", " + foto + ")");
		
		Mascota mascota = new Mascota();
		mascota.setNombres(nombres);
		mascota.setRaza(raza);
		mascota.setEdad(edad);
		mascota.setUsuario_id(usuario_id);
		
		
		if (foto != null && !foto.isEmpty()) {
			String filename = System.currentTimeMillis() + foto.getOriginalFilename().substring(foto.getOriginalFilename().lastIndexOf("."));
			mascota.setFoto(filename);
			if(Files.notExists(Paths.get(STORAGEPATH))){
		        Files.createDirectories(Paths.get(STORAGEPATH));
		    }
			Files.copy(foto.getInputStream(), Paths.get(STORAGEPATH).resolve(filename));
		}
		
		mascotaService.save(mascota);
		
		return mascota;
	}
	
	@DeleteMapping("/mascota/eliminar/{id}")
    public ResponseEntity<?> deleteEnfermedad(@PathVariable Long id) {
       
		mascotaService.deleteById(id);

        return ResponseEntity.ok().build();
    }

	
	
	@PostMapping("mascotas/crear/sinfoto")
	public ResponseEntity<?>agregarEnfermedad(@RequestBody Mascota mascota){
		mascotaService.save(mascota);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@GetMapping("/mascotas/images/{filename:.+}")//parametro imagen / foto
	public ResponseEntity<Resource> files(@PathVariable String filename) throws Exception{
		logger.info("call images: " + filename);
		
		Path path = Paths.get(STORAGEPATH).resolve(filename);
		logger.info("Path: " + path);
		
		if(!Files.exists(path)) {
			return ResponseEntity.notFound().build();
		}
		
		Resource resource = new UrlResource(path.toUri());
		logger.info("Resource: " + resource);
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\""+resource.getFilename()+"\"")
				.header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(Paths.get(STORAGEPATH).resolve(filename)))
				.header(HttpHeaders.CONTENT_LENGTH, String.valueOf(resource.contentLength()))
				.body(resource);
	}


}
