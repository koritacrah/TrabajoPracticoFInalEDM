package ar.edu.unju.edm.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ar.edu.unju.edm.service.IPoIsService;

public class PoIsController {
	private static final Log LOGGER = LogFactory.getLog(PoIsController.class);
	
	@Autowired
	@Qualifier("implementacionMYSQLPoI")
	
	IPoIsService poiService;

	@GetMapping("/cargarPoI")
	public String crearPoI(Model model) {
		LOGGER.info("METHOD: ingresando el metodo cargar");
		model.addAttribute("PoI", poiService.crearPoI());

		return ("PoI");
	}
}
