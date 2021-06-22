package ar.edu.unju.edm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.model.PoIs;
import ar.edu.unju.edm.model.Turista;
import ar.edu.unju.edm.model.Valoracion;
@Repository
public interface IValoracionDAO extends CrudRepository <Valoracion, Integer> {
	
	public Optional<Valoracion> findByidValoracion(Integer idValoracion);
	@Query("from Valoracion v order by v.idValoracion")
	
	public List<Valoracion> findAllByPoiCreador(PoIs poiCreador);
	public List<Valoracion> findAllByTuristaCreador(Turista turistaCreador);
}
