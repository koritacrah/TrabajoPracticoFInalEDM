package ar.edu.unju.edm.service;

import java.util.List;

import org.springframework.stereotype.Service;


import ar.edu.unju.edm.model.Valoracion;

@Service
public interface IValoracionService {
	public Valoracion crearUnaValoracion();
	public void guardarValoracion (Valoracion unaValoracion);
	public void eliminarValoracion (int Idvaloracion) throws Exception;
	public void modificarValoracion (Valoracion valModificado)throws Exception;
	public List<Valoracion> obtenerTodasValoracion();
	public Valoracion encontrarValoracionId(int idValoracion) throws Exception;

}
