package ar.edu.unju.edm.controller;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.edm.model.Turista;
import ar.edu.unju.edm.service.ITuristaService;

@Controller
public class TuristaController {
	private static final Log LOGGER = LogFactory.getLog(TuristaController.class);
	
	@Autowired
	@Qualifier("implementacionMYSQLturista")
	ITuristaService turistaService;
	
	@GetMapping("/turista/mostrar")
	public String cargarTurista(Model model) {
		model.addAttribute("unTurista", turistaService.crearTurista());
		return("turista");
	}
	
	@PostMapping("/turista/guardar")
	public String guardarNuevoTurista(@Valid @ModelAttribute("unTurista") Turista nuevoTurista, BindingResult resultado ,Model model) {
		
		if (resultado.hasErrors()) 
		{
			model.addAttribute("unTurista", nuevoTurista);
			return("turista");
		}
		else 
		{
			LOGGER.info("METHOD: ingresando el metodo Guardar");
			turistaService.guardarTurista(nuevoTurista);
			return "redirect:/turista/mostrar";
		}
	}
	
	@GetMapping("/turista/perfiles")
	public String cargarPerfilTuristas(Model model){
		model.addAttribute("turistas", turistaService.obtenerTodosTurista());
		return("perfiles");
	}
	

	
	@GetMapping("/turista/editar/{idTurista}")
	public String editarTurista(Model model, @PathVariable(name="idTurista") Integer idTurista) throws Exception {
		try {
			Turista turistaEncontrado = turistaService.encontrarUnTurista(idTurista);
			model.addAttribute("unTurista", turistaEncontrado);	
			model.addAttribute("editMode", "true");
		}
		catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("unCliente", turistaService.crearTurista());
			model.addAttribute("editMode", "false");
		}
		return("modificar");
	}
	
	@PostMapping("/turista/modificar")
	public String modificarTurista(@ModelAttribute("unTurista") Turista turistaModificado, Model model) {
		try {
			System.out.println("turista modificad:"+turistaModificado.getIdTurista());
			turistaService.modificarTurista(turistaModificado);
			model.addAttribute("unTurista", new Turista());				
			model.addAttribute("editMode", "false");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("unTurista", turistaModificado);
			model.addAttribute("editMode", "true");
		}
		return("redirect:/turista/perfiles");
	}

	@GetMapping("/turista/eliminarTurista/{idTurista}")
	public String eliminarTurista(Model model, @PathVariable(name="idTurista") Integer idTurista) {
		LOGGER.info("METHOD: ingresando el metodo Eliminar");
		try {
			turistaService.eliminarTurista(idTurista);		
		}
		catch(Exception e){
			model.addAttribute("listErrorMessage",e.getMessage());
		}			
		return "redirect:/login";
	}
}
