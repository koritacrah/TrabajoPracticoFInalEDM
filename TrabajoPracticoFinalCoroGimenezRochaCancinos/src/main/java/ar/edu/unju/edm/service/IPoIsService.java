package ar.edu.unju.edm.service;

import ar.edu.unju.edm.model.PoIs;

public interface IPoIsService {
	public void guardarPoIs(PoIs PoIGuardado);
	public PoIs crearPoI();
	public PoIs encontrarUnPoi(int codPoI) throws Exception;
	public void modificarPoI(PoIs PoIModificado) throws Exception;
	public void eliminarPoI(int codPoI) throws Exception;
	public void cambiarPoI( PoIs PoIModificado, PoIs PoIaModificar);

}
