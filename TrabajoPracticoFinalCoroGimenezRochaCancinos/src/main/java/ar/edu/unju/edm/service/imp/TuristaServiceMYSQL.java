package ar.edu.unju.edm.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Turista;
import ar.edu.unju.edm.repository.ITuristaDAO;
import ar.edu.unju.edm.service.ITuristaService;

@Service
@Qualifier("implementacionMYSQLturista")

public class TuristaServiceMYSQL implements ITuristaService {

	@Autowired
	Turista unTurista;
	@Autowired
	ITuristaDAO iTuristaDAO;
	@Override
	public void guardarTurista(Turista turistaGuardado) {
		// TODO Auto-generated method stub
		iTuristaDAO.save(turistaGuardado);
	}
	@Override
	public Turista crearTurista() {
		// TODO Auto-generated method stub
		return unTurista;
	}
	@Override
	public Turista encontrarUnTurista(int idTurista) throws Exception {
		// TODO Auto-generated method stub
	    return iTuristaDAO.findByidTurista(idTurista).orElseThrow(()->new Exception("El turista NO existe"));
	}
	@Override
	public void modificarTurista(Turista turistaModificado) throws Exception {
		// TODO Auto-generated method stub
		Turista turistaAModificar = iTuristaDAO.findByidTurista(turistaModificado.getIdTurista()).orElseThrow(()->new Exception("El Turista no fue encontrado"));
		cambiarTurista(turistaModificado, turistaAModificar);
		iTuristaDAO.save(turistaAModificar);
	}
	private void cambiarTurista(Turista turistaModificado, Turista turistaAModificar) {
		// TODO Auto-generated method stub
		turistaAModificar.setNombre(turistaModificado.getNombre());
		turistaAModificar.setApellido(turistaModificado.getApellido());
		turistaAModificar.setEmail(turistaModificado.getEmail());
		turistaAModificar.setPaís(turistaModificado.getPaís());
	}
	@Override
	public void eliminarTurista(int idTurista) throws Exception {
		// TODO Auto-generated method stub
		Turista turistaEliminar = iTuristaDAO.findByidTurista(idTurista).orElseThrow(()->new Exception("El Turista no fue encontrado"));
		iTuristaDAO.delete(turistaEliminar);
	}
	
}