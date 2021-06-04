package ar.edu.unju.edm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table (name="PoIs")
@Component
public class PoIs {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer codPoI;
	@Column
	private String email;
	@Column
	private String nombre;
	@Column
	private String descripcion;
	@Column
	private String etiqueta;
	@Column
	private String Sitio_web;
	@Column
	private String calle;
	@Column
	private int NumeroCasa;
	@Column
	private String barrio;
	@Column
	private String localidad;
	@Column
	private int localizacionLatitud;
	@Column
	private int localizacionLongitud;
	@Column
	private int media;

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEtiqueta() {
		return etiqueta;
	}
	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}
	public String getSitio_web() {
		return Sitio_web;
	}
	public void setSitio_web(String sitio_web) {
		Sitio_web = sitio_web;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public int getNumeroCasa() {
		return NumeroCasa;
	}
	public void setNumeroCasa(int numeroCasa) {
		NumeroCasa = numeroCasa;
	}
	public String getBarrio() {
		return barrio;
	}
	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
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
	public int getMedia() {
		return media;
	}
	public void setMedia(int media) {
		this.media = media;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codPoI == null) ? 0 : codPoI.hashCode());
		result = prime * result + NumeroCasa;
		result = prime * result + ((Sitio_web == null) ? 0 : Sitio_web.hashCode());
		result = prime * result + ((barrio == null) ? 0 : barrio.hashCode());
		result = prime * result + ((calle == null) ? 0 : calle.hashCode());
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((etiqueta == null) ? 0 : etiqueta.hashCode());
		result = prime * result + ((localidad == null) ? 0 : localidad.hashCode());
		result = prime * result + localizacionLatitud;
		result = prime * result + localizacionLongitud;
		result = prime * result + media;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}
	public Integer getCodPoI() {
		return codPoI;
	}
	public void setCodPoI(Integer codPoI) {
		this.codPoI = codPoI;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PoIs other = (PoIs) obj;
		if (codPoI == null) {
			if (other.codPoI != null)
				return false;
		} else if (!codPoI.equals(other.codPoI))
			return false;
		if (NumeroCasa != other.NumeroCasa)
			return false;
		if (Sitio_web == null) {
			if (other.Sitio_web != null)
				return false;
		} else if (!Sitio_web.equals(other.Sitio_web))
			return false;
		if (barrio == null) {
			if (other.barrio != null)
				return false;
		} else if (!barrio.equals(other.barrio))
			return false;
		if (calle == null) {
			if (other.calle != null)
				return false;
		} else if (!calle.equals(other.calle))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (etiqueta == null) {
			if (other.etiqueta != null)
				return false;
		} else if (!etiqueta.equals(other.etiqueta))
			return false;
		if (localidad == null) {
			if (other.localidad != null)
				return false;
		} else if (!localidad.equals(other.localidad))
			return false;
		if (localizacionLatitud != other.localizacionLatitud)
			return false;
		if (localizacionLongitud != other.localizacionLongitud)
			return false;
		if (media != other.media)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PoIs [codPoI=" + codPoI + ", email=" + email + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", etiqueta=" + etiqueta + ", Sitio_web=" + Sitio_web + ", calle=" + calle + ", NumeroCasa="
				+ NumeroCasa + ", barrio=" + barrio + ", localidad=" + localidad + ", localizacionLatitud="
				+ localizacionLatitud + ", localizacionLongitud=" + localizacionLongitud + ", media=" + media + "]";
	}
	
	}
	