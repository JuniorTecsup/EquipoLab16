package pe.edu.tecsup.productosapi.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "usuario") 
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombres;
	
	private String correo;
	
	private String clave;

    
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name=("usuario_id"),referencedColumnName="id")
    private List<Mascota> mascota=new ArrayList<>();
	
	/*public Long getId() {
		return id;
	}*/

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	
	public List<Mascota> getMascota() {
		return mascota;
	}
	
	public void setMascota(List<Mascota> mascota) {
		this.mascota = mascota;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombres=" + nombres + ", correo=" + correo + ", clave="
				+ clave + ", mascota=" + mascota + "]";
	}



}
