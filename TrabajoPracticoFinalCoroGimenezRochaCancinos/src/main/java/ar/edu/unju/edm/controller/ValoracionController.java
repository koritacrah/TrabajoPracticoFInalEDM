package ar.edu.unju.edm.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import ar.edu.unju.edm.model.PoIs;
import ar.edu.unju.edm.model.Turista;
import ar.edu.unju.edm.model.Valoracion;
import ar.edu.unju.edm.service.IPoIsService;
import ar.edu.unju.edm.service.ITuristaService;
import ar.edu.unju.edm.service.IValoracionService;


@Controller
public class ValoracionController {
	@Autowired
	@Qualifier("implementacionMYSQLValoracion")
	IValoracionService valoracionService;
	
	@Autowired
	IPoIsService poiService;
	
	@Autowired
	ITuristaService turistaService;
	
	@GetMapping("/valoracion/mostrar/{codPoI}")
	public String cargarValoracion(Model model,@PathVariable(name="codPoI") Integer codigo) {
		System.out.println("entro");
		Valoracion valoracionNueva = valoracionService.crearUnaValoracion();
		try {
			valoracionNueva.setPoiCreador(poiService.encontrarUnPoi(codigo));
			System.out.println(valoracionNueva.getPoiCreador().getCodPoI());
			model.addAttribute("valoracion", valoracionNueva);
		    model.addAttribute("valoraciones", valoracionService.obtenerTodasValoracion());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	    return ("misvaloraciones");
	}
	
	@PostMapping("/valoracion/guardar/{codPoI}")
	public String guardarNuevoValoracion( @ModelAttribute("unaValoracion") Valoracion nuevaValoracion,@PathVariable(name="codPoI") Integer codigo, Model model) {		
		//System.out.println(valoracionService.obtenerTodasValoraciones());
		model.addAttribute("valoraciones", valoracionService.obtenerTodasValoracion().size());
			   
		Authentication auth = SecurityContextHolder
	            .getContext()
	            .getAuthentication();
	    UserDetails userDetail = (UserDetails) auth.getPrincipal();
	    
	    try {
	    	PoIs poiEncontrado = poiService.encontrarUnPoi(codigo);
	    	nuevaValoracion.setPoiCreador(poiEncontrado);
			//System.out.println(nuevaValoracion.getPoiCreador().getCodPoI());
			Turista turistaEncontrado = turistaService.encontrarUnTuristaPorEmail(userDetail.getUsername());
			if (turistaEncontrado != null) {
				
				if (nuevaValoracion.getComentario().isEmpty()) {
					nuevaValoracion.setComentario(null);
				}
				else {turistaEncontrado.setPuntos(turistaEncontrado.getPuntos()+5);}
				if(nuevaValoracion.getUnaValoracion() != 0) {
					turistaEncontrado.setPuntos(turistaEncontrado.getPuntos()+8);}
				nuevaValoracion.setEmail(userDetail.getUsername());
				nuevaValoracion.setFechaComentario(LocalDate.now());
				nuevaValoracion.setHoraComentario(LocalTime.now());
				nuevaValoracion.setTuristaCreador(turistaEncontrado);	
				
				
				valoracionService.guardarValoracion(nuevaValoracion);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		return "redirect:/punto";
	}
	
	
	
	@GetMapping("/valoracion/editar/{idValoracion}")
	public String editarValoracion(Model model, @PathVariable(name="idValoracion")Integer id) throws Exception {
		try {
			Valoracion valoracionEncontrada = valoracionService.encontrarValoracionId(id);
			model.addAttribute("valoracion", valoracionEncontrada);
		    model.addAttribute("editMode", "true");
			
		}
		catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("valoracion", valoracionService.crearUnaValoracion());
			model.addAttribute("editMode", "false");
		}

		return("cargar_valoracion");
		
	}

	@PostMapping(value="/valoracion/modificar", consumes = "multipart/form-data")
	public String modificarValoracion(@ModelAttribute("valoracion") Valoracion valoracionModificada, Model model) throws Exception{
		try {
			valoracionService.modificarValoracion(valoracionModificada);
			model.addAttribute("valoracion", new Valoracion());
			model.addAttribute("editMode", "false");
			
		} catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("valoracion", valoracionModificada);
			model.addAttribute("editMode", "true");
		}
		model.addAttribute("valoraciones", valoracionService.obtenerTodasValoracion());
		return("modificarvaloracion");
	}
	

	@GetMapping("/valoracion/eliminar/{idValoracion}")
	public String eliminarPoI(Model model, @PathVariable(name ="idValoracion")Integer id) {
			try {
				valoracionService.eliminarValoracion(id);
				
			}catch(Exception e){
				model.addAttribute("listErrorMessage",e.getMessage());
			}
			return "redirect:/mis/valoraciones";
		}
	@GetMapping({"/cargar_valoracion/{codPoI}"} )
	public String cargarvalo(Model model,@PathVariable(name="codPoI") Integer id)throws Exception{
		PoIs poiEncontrado=poiService.encontrarUnPoi(id);
		model.addAttribute("poiEncontrado", poiEncontrado);
		model.addAttribute("valoracion", valoracionService.crearUnaValoracion());
		return "cargar_valoracion";
	}
	@GetMapping({"/punto"})
	public String cargarpunto(Model model){
		model.addAttribute("pois", poiService.obtenerTodosPoIs());
		model.addAttribute("valoracion", valoracionService.crearUnaValoracion());
		return "punto";
	}
	@GetMapping({"/valoraciones/{codPoI}"})
	public String valoracionPois(Model model,@PathVariable(name="codPoI")Integer id)throws Exception{
		PoIs poiEncontrado=poiService.encontrarUnPoi(id);
		System.out.println("entro a ver las valoraciones");
		model.addAttribute("pois", poiEncontrado);
		//model.addAttribute("valoracion", valoracionService.obtenerTodasValoracion());
		model.addAttribute("valoracion", valoracionService.obtenerMisValoraciones(poiEncontrado));
	
		
		return "valoraciones";
	}
	@GetMapping("/mis/valoraciones")
	public String cargarMisPoIs(Model model) {		

		Authentication auth = SecurityContextHolder
	            .getContext()
	            .getAuthentication();
	    UserDetails userTurista = (UserDetails) auth.getPrincipal();
	    try {
			Turista turistaEncontrado = turistaService.encontrarUnTuristaPorEmail(userTurista.getUsername());
			
			if (turistaEncontrado != null) {

				model.addAttribute("valoraciones", valoracionService.obtenerMioValoraciones(turistaEncontrado));
			
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return("misvaloraciones");
	}
}


