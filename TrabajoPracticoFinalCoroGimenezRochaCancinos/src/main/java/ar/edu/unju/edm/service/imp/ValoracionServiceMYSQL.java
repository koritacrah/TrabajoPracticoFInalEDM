package ar.edu.unju.edm.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Turista;
import ar.edu.unju.edm.model.Valoracion;
import ar.edu.unju.edm.repository.IValoracionDAO;
import ar.edu.unju.edm.service.IValoracionService;

@Service
public abstract class ValoracionServiceMYSQL implements IValoracionService {
	@Autowired
	Valoracion unaValoracion;
	@Autowired
	IValoracionDAO valoracionDAO;
	@Override
	public Valoracion crearUnaValoracion() {
		// TODO Auto-generated method stub
		return unaValoracion;
	}

	@Override
	public void guardarValoracion(Valoracion unaValoracion) {
		// TODO Auto-generated method stub
		valoracionDAO.save(unaValoracion);
	}

	@Override
	public void eliminarValoracion(Valoracion unaValoracion) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarValoracion(Turista unaValoracion) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Valoracion> obtenerTodasValoracion() {
		// TODO Auto-generated method stub
		return (List<Valoracion>) valoracionDAO.findAll();
	}

	@Override
	public Valoracion encontrarValoracionId(Integer idValoracion) throws Exception {
		// TODO Auto-generated method stub
		return valoracionDAO.findById(idValoracion).orElseThrow(()-> new Exception ("este turista no existe"));
	}

}
