package ar.edu.unju.edm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.model.PoIs;
import ar.edu.unju.edm.model.Turista;

@Repository
public interface IPoIsDAO extends CrudRepository <PoIs, Integer>{

	public Optional<PoIs> findBycodPoI(int CodPoI);
	@Query("from PoIs p order by p.codPoI")
	
	public List<PoIs> obtenerPoIs();
	public List<PoIs> findAllByTuristaAutor(Turista turistaAutor);
	

}
