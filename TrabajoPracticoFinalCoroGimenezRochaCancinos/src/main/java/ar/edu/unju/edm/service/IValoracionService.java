package ar.edu.unju.edm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Turista;
import ar.edu.unju.edm.model.Valoracion;

@Service
public interface IValoracionService {
	public Valoracion crearUnaValoracion();
	public void guardarValoracion (Valoracion unaValoracion);
	public void eliminarValoracion (Valoracion unaValoracion) throws Exception;
	public void modificarValoracion (Turista unaValoracion);
	public List<Valoracion> obtenerTodasValoracion();
	public Valoracion encontrarValoracionId(Integer idValoracion) throws Exception;
}
