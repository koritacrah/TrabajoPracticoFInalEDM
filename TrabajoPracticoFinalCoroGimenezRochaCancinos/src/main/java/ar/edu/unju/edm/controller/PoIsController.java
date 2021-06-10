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
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.edm.model.PoIs;
import ar.edu.unju.edm.service.IPoIsService;


@Controller
public class PoIsController {
	private static final Log LOGGER = LogFactory.getLog(PoIsController.class);
	
	@Autowired
	@Qualifier("implementacionMYSQLPoI")
	
	IPoIsService poiService;
	//PoIs PoI;
	
	@GetMapping("/cargar/poi")
	public String crearPoI(Model model) {
		LOGGER.info("METHOD: ingresando el metodo cargar");
		model.addAttribute("poI", poiService.crearPoI());
		return ("cargarpoi");
	}
	@PostMapping("/poi/guardar")
	public String guardarNuevoPoI(@Valid @ModelAttribute("poiGuardado") PoIs nuevoPoI, BindingResult resultado ,Model model) {
		
		if (resultado.hasErrors()) 
		{
			model.addAttribute("poiGuardado", nuevoPoI);
			return("cargarpoi");
		}
		else 
		{
			//deberia tener un try por si ocurre algun error
			LOGGER.info("METHOD: ingresando el metodo Guardar");
			
			//clienteService.guardarCliente(nuevoCliente);		
			poiService.guardarPoIs(nuevoPoI);
			//trabajarConFechas();
			return "redirect:/cargar/poi";
		}
	}

}

