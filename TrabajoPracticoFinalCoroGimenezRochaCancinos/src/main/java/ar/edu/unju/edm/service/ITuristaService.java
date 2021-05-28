package ar.edu.unju.edm.service;

import ar.edu.unju.edm.model.Turista;


public interface ITuristaService {

	public void guardarTurista(Turista turistaGuardado);
	public Turista crearTurista();
	public Turista encontrarUnTurista(int idTurista) throws Exception;
	public void modificarTurista(Turista turistaModificado) throws Exception;
	public void eliminarTurista(int idTurista) throws Exception;

}
