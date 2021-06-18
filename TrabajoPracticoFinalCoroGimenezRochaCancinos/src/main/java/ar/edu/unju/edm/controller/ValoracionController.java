package ar.edu.unju.edm.controller;

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
		System.out.println("entro1");
		//System.out.println(valoracionService.obtenerTodasValoraciones());
		model.addAttribute("valoraciones", valoracionService.obtenerTodasValoracion().size());
			   
		Authentication auth = SecurityContextHolder
	            .getContext()
	            .getAuthentication();
	    UserDetails userDetail = (UserDetails) auth.getPrincipal();
	    
	    System.out.println(userDetail.getUsername());
	    try {
	    	PoIs poiEncontrado = poiService.encontrarUnPoi(codigo);
	    	nuevaValoracion.setPoiCreador(poiEncontrado);
			System.out.println(nuevaValoracion.getPoiCreador().getCodPoI());
			Turista turistaEncontrado = turistaService.encontrarUnTuristaPorEmail(userDetail.getUsername());
			if (turistaEncontrado != null) {
				
				if (nuevaValoracion.getComentario().isEmpty()) {
					nuevaValoracion.setComentario(null);
				}
				else {turistaEncontrado.setPuntos(turistaEncontrado.getPuntos()+5);}
				
				if(nuevaValoracion.getUnaValoracion() != 0) {
					turistaEncontrado.setPuntos(turistaEncontrado.getPuntos()+8);}
				
				valoracionService.guardarValoracion(nuevaValoracion);
				nuevaValoracion.setTuristaCreador(turistaEncontrado);
				turistaService.guardarTurista(turistaEncontrado);	
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		return "redirect:/cargar/poi";
	}
	
	
	
	@GetMapping("/valoracion/editar/{idTurista}")
	public String editarValoracion(Model model, @PathVariable(name="idTurista") int id) throws Exception {
		try {
			Valoracion valoracionEncontrada = valoracionService.encontrarValoracionId(id);
			model.addAttribute("unaValoracion", valoracionEncontrada);
		    model.addAttribute("editMode", "true");
			
		}
		catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("unaValoracion", valoracionService.crearUnaValoracion());
			model.addAttribute("editMode", "false");
		}
		
		model.addAttribute("valoraciones" , valoracionService.obtenerTodasValoracion());
		return("punto");
		
	}
	
	@PostMapping("/valoracion/modificar")
	public String modificarValoracion(@ModelAttribute("unaValoracion") Valoracion valoracionModificada, Model model) {
		try {
			valoracionService.modificarValoracion(valoracionModificada);
			model.addAttribute("unaValoracion", new Valoracion());
			model.addAttribute("editMode", "false");
			
		} catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("unaValoracion", valoracionModificada);
			model.addAttribute("valoraciones", valoracionService.obtenerTodasValoracion());
			model.addAttribute("editMode", "true");
		}
		model.addAttribute("valoraciones", valoracionService.obtenerTodasValoracion());
		return("modificarvaloracion");
	}
	
	
	@GetMapping("/valoracion/eliminarValoracion/{idTurista}")
	public String eliminarValoracion(Model model, @PathVariable(name="idTurista") int id) {		
		try {	
			valoracionService.eliminarValoracion(id);
		}
		catch(Exception e){
			model.addAttribute("listErrorMessage",e.getMessage());
		}			
		return "redirect:/valoracion/mostrar";
	}
	

}


