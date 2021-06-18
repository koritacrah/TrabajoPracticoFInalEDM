package ar.edu.unju.edm.service;

import java.util.ArrayList;

import ar.edu.unju.edm.model.PoIs;
import ar.edu.unju.edm.model.Turista;



public interface IPoIsService {
	public void guardarPoIs(PoIs poiGuardado);
	public PoIs crearPoI();
	public PoIs obtenerPoiNuevo();
	public PoIs obtenerUnPoi(String nombrePoi);
	public ArrayList<PoIs> obtenerTodosPoIs();
	public PoIs encontrarUnPoi(int codPoI) throws Exception;
	public void modificarPoI(PoIs PoIModificado) throws Exception;
	public void eliminarPoI(int codPoI) throws Exception;
	public void cambiarPoI( PoIs poModificado, PoIs PoIaModificar);
	public ArrayList<PoIs> obtenerMisPoIs(Turista turistaAutor);
}
