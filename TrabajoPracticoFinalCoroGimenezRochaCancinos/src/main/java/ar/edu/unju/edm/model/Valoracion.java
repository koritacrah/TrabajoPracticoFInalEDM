package ar.edu.unju.edm.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


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
	private Integer unaValoracion;
	@Column
	private String comentario;
	@ManyToOne
	@JoinColumn(name="idTurista")
	private Turista turistaCreador;
	@ManyToOne
	@JoinColumn(name="codPoI")
	private PoIs poiCreador;
	
	
	
	

	public Integer getIdValoracion() {
		return idValoracion;
	}
	public void setIdValoracion(Integer idValoracion) {
		this.idValoracion = idValoracion;
	}
	
	public Integer getUnaValoracion() {
		return unaValoracion;
	}
	public void setUnaValoracion(Integer unaValoracion) {
		this.unaValoracion = unaValoracion;
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

	

	
	
	
	
}
