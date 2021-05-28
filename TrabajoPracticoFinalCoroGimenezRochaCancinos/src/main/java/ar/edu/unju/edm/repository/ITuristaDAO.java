package ar.edu.unju.edm.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.model.Turista;

@Repository
public interface ITuristaDAO extends CrudRepository <Turista, Integer>{

	public Optional<Turista> findByidTurista(int idTurista);
}
