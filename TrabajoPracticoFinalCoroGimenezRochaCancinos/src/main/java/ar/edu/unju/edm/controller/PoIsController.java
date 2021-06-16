package ar.edu.unju.edm.controller;




import java.io.IOException;
import java.util.Base64;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ar.edu.unju.edm.model.PoIs;
import ar.edu.unju.edm.service.IPoIsService;


@Controller
public class PoIsController {
	private static final Log LOGGER = LogFactory.getLog(PoIsController.class);
	
	@Autowired
	@Qualifier("implementacionMYSQLPoI")
	
	IPoIsService poiService;
	
	
	@GetMapping("/cargar/poi")
	public String crearPoI(Model model) {
		LOGGER.info("METHOD: ingresando el metodo cargar");
		model.addAttribute("poi", poiService.crearPoI());
		model.addAttribute("editMode", "false");
		return ("cargarpoi");
	}
	@PostMapping(value="/poi/guardar", consumes = "multipart/form-data")
	public String guardarNuevoPoI(@RequestParam("file") MultipartFile file, @ModelAttribute("poiGuardado") PoIs nuevoPoI, BindingResult resultado ,Model model)  throws IOException {
		byte[] content = file.getBytes();
		String base64 = Base64.getEncoder().encodeToString(content);
		nuevoPoI.setImagen(base64);
		
		if (resultado.hasErrors()) 
		{
			model.addAttribute("poiGuardado", nuevoPoI);
			return("cargarpoi");
		}
		else 
		{
			//deberia tener un try por si ocurre algun error
			LOGGER.info("METHOD: ingresando el metodo Guardar");
					
			poiService.guardarPoIs(nuevoPoI);
			//trabajarConFechas();
			return "redirect:/cargar/poi";
		}
	}

	@GetMapping("/poi/editar/{codPoI}")
	public String editarCliente(Model model, @PathVariable(name="codPoI") int id) throws Exception {
		try {
			PoIs poiEncontrado = poiService.encontrarUnPoi(id);

			model.addAttribute("poi", poiEncontrado);	
			model.addAttribute("editMode", "true");
			
		}
		catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("poi", poiService.crearPoI());
			model.addAttribute("editMode", "false");
		}
		return("cargarpoi");
	}
	
	@PostMapping(value="/poi/modificar", consumes = "multipart/form-data")
	public String modificarPoI(@RequestParam("file") MultipartFile file, @ModelAttribute("poi") PoIs poiModificado, Model model)  throws Exception{
		
		byte[] content = file.getBytes();
		String base64 = Base64.getEncoder().encodeToString(content);
		poiModificado.setImagen(base64);
		
		try {
			poiService.modificarPoI(poiModificado);
			model.addAttribute("poi", new PoIs());				
			model.addAttribute("editMode", "false");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("poi", poiModificado);			
			model.addAttribute("editMode", "true");
		}
		return "redirect:/cargar/poi";
	}
	@GetMapping("poi/eliminar/{codPoI}")
public String eliminarPoI(Model model, @PathVariable(name ="codPoI")int codPoI) {
		try {
			poiService.eliminarPoI(codPoI);
			
		}catch(Exception e){
			model.addAttribute("listErrorMessage",e.getMessage());
		}
		return "redirect:/cargar/poi";
	}
	
	
	@GetMapping("/mis/pois")
	public String cargarMisPoIs(Model model) {		
		model.addAttribute("Poi", poiService.obtenerPoiNuevo());
		model.addAttribute("pois", poiService.obtenerTodosPoIs());
		return("mispoiss");
	}
	@GetMapping({"/home"})
	public String cargarhome(Model model){
		model.addAttribute("pois", poiService.obtenerTodosPoIs());

		return "home";
	}
	
	
	
	
}

