package ar.edu.unju.edm.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.model.PoIs;

@Repository
public interface IPoIsDAO extends CrudRepository <PoIs, Integer>{

	public Optional<PoIs> findBycodPoI(int CodPoI);
}
