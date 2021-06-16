package ar.edu.unju.edm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="Turistas")
public class Turista {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer idTurista;
	@Column
	private String email;
	@Column
	private String password;
	@Column
	private String nombre;
	@Column
	private String apellido;
	@Column
	private String país;
	@Column
	private int localizacionLatitud;
	@Column
	private int localizacionLongitud;
	@Column
	private int puntos;
	@Column
	private String perfil;
	
	public Turista() {
		// TODO Auto-generated constructor stub
	}

	public Integer getIdTurista() {
		return idTurista;
	}

	public void setIdTurista(Integer idTurista) {
		this.idTurista = idTurista;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getPaís() {
		return país;
	}

	public void setPaís(String país) {
		this.país = país;
	}

	public int getLocalizacionLatitud() {
		return localizacionLatitud;
	}

	public void setLocalizacionLatitud(int localizacionLatitud) {
		this.localizacionLatitud = localizacionLatitud;
	}

	public int getLocalizacionLongitud() {
		return localizacionLongitud;
	}

	public void setLocalizacionLongitud(int localizacionLongitud) {
		this.localizacionLongitud = localizacionLongitud;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	
	

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellido == null) ? 0 : apellido.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((idTurista == null) ? 0 : idTurista.hashCode());
		result = prime * result + localizacionLatitud;
		result = prime * result + localizacionLongitud;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((país == null) ? 0 : país.hashCode());
		result = prime * result + puntos;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Turista other = (Turista) obj;
		if (apellido == null) {
			if (other.apellido != null)
				return false;
		} else if (!apellido.equals(other.apellido))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (idTurista == null) {
			if (other.idTurista != null)
				return false;
		} else if (!idTurista.equals(other.idTurista))
			return false;
		if (localizacionLatitud != other.localizacionLatitud)
			return false;
		if (localizacionLongitud != other.localizacionLongitud)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (país == null) {
			if (other.país != null)
				return false;
		} else if (!país.equals(other.país))
			return false;
		if (puntos != other.puntos)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Turista [idTurista=" + idTurista + ", email=" + email + ", password=" + password + ", nombre=" + nombre
				+ ", apellido=" + apellido + ", país=" + país + ", localizacionLatitud=" + localizacionLatitud
				+ ", localizacionLongitud=" + localizacionLongitud + ", puntos=" + puntos + "]";
	}
	
	
}
