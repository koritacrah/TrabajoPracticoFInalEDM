package ar.edu.unju.edm.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
@Entity  
@Table(name="Valoracion")

public class Valoracion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer idValoracion;
	@Column
	private int valoracion;
	@Column
	private String comentario;
	@ManyToOne
	@JoinColumn(name="idTurista")
	private Turista turistaCreador;
	@ManyToOne
	@JoinColumn(name="codPoI")
	private PoIs poiCreador;
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaComentario;
	
	
	

	public Integer getIdValoracion() {
		return idValoracion;
	}
	public void setIdValoracion(Integer idValoracion) {
		this.idValoracion = idValoracion;
	}
	public int getValoracion() {
		return valoracion;
	}
	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public Turista getTuristaCreador() {
		return turistaCreador;
	}
	public void setTuristaCreador(Turista turistaCreador) {
		this.turistaCreador = turistaCreador;
	}
	public PoIs getPoiCreador() {
		return poiCreador;
	}
	public void setPoiCreador(PoIs poiCreador) {
		this.poiCreador = poiCreador;
	}
	public LocalDate getFechaComentario() {
		return fechaComentario;
	}
	public void setFechaComentario(LocalDate fechaComentario) {
		this.fechaComentario = fechaComentario;
	}
	

	
	
	
	
}