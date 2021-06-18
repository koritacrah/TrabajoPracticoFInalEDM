package ar.edu.unju.edm.controller;




import java.io.IOException;
import java.util.Base64;

import org.apache.commons.logging.Log;

import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
import ar.edu.unju.edm.model.Turista;
import ar.edu.unju.edm.service.IPoIsService;
import ar.edu.unju.edm.service.ITuristaService;
import ar.edu.unju.edm.service.IValoracionService;


@Controller
public class PoIsController {
	private static final Log LOGGER = LogFactory.getLog(PoIsController.class);
	@Autowired
	@Qualifier("implementacionMYSQLValoracion")
	IValoracionService valoracionService;
	@Autowired
	@Qualifier("implementacionMYSQLPoI")
	IPoIsService poiService;
	@Autowired
	ITuristaService turistaService;
	@Autowired
	Turista unTurista;
	@GetMapping("/cargar/poi")
	public String crearPoI(Model model) {
		LOGGER.info("METHOD: ingresando el metodo cargar");
		model.addAttribute("poi", poiService.crearPoI());
		model.addAttribute("editMode", "false");
		return ("cargarpoi");
	}
	@PostMapping(value="/poi/guardar", consumes = "multipart/form-data")
	public String guardarNuevoPoI(@RequestParam("file") MultipartFile file, @RequestParam("file1") MultipartFile file1, @RequestParam("file2")  MultipartFile file2 , @ModelAttribute("poiGuardado") PoIs nuevoPoI, BindingResult resultado ,Model model,Authentication authentication)  throws IOException {
		byte[] content = file.getBytes();
		String base64 = Base64.getEncoder().encodeToString(content);
		nuevoPoI.setImagen(base64);
		
		byte[] content1 = file1.getBytes();
		String base65 = Base64.getEncoder().encodeToString(content1);
		nuevoPoI.setImagen1(base65);
		
		byte[] content2 = file2.getBytes();
		String base66 = Base64.getEncoder().encodeToString(content2);
		nuevoPoI.setImagen2(base66);
		
		
		
		if (resultado.hasErrors()) 
		{
			model.addAttribute("poiGuardado", nuevoPoI);
			return("cargarpoi");
		}
		else 
		{
			//declarando el autentiqueishon
			LOGGER.info("METHOD: ingresando el metodo Guardar");
			Authentication auth = SecurityContextHolder
		            .getContext()
		            .getAuthentication();
		    UserDetails userTurista = (UserDetails) auth.getPrincipal();
		    try {
				Turista turistaEncontrado = turistaService.encontrarUnTuristaPorEmail(userTurista.getUsername());
				
				if (turistaEncontrado != null) {
					
					turistaEncontrado.setPuntos(turistaEncontrado.getPuntos() + 10);
					System.out.println(turistaEncontrado.getPuntos());
					
					nuevoPoI.setTuristaAutor(turistaEncontrado);
					poiService.guardarPoIs(nuevoPoI);
					model.addAttribute("pois", poiService.obtenerTodosPoIs());
				
					
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
			/*Turista turistaEncontrado=turistaService.encontrarUnTurista(userTurista.getUsername());		
			poiService.guardarPoIs(nuevoPoI);*/
			
			//trabajarConFechas();
			return "redirect:/mis/pois";
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
	public String modificarPoI(@RequestParam("file") MultipartFile file , @RequestParam("file1") MultipartFile file1, @RequestParam("file2")  MultipartFile file2 , @ModelAttribute("poi") PoIs poiModificado, Model model)  throws Exception{
		
		byte[] content = file.getBytes();
		String base64 = Base64.getEncoder().encodeToString(content);
		poiModificado.setImagen(base64);
		
		byte[] content1 = file1.getBytes();
		String base65 = Base64.getEncoder().encodeToString(content1);
		poiModificado.setImagen1(base65);
		
		byte[] content2 = file2.getBytes();
		String base66 = Base64.getEncoder().encodeToString(content2);
		poiModificado.setImagen2(base66);
		
		
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
	@GetMapping("/poi/eliminar/{codPoI}")
public String eliminarPoI(Model model, @PathVariable(name ="codPoI")int codPoI) {
		try {
			poiService.eliminarPoI(codPoI);
			
		}catch(Exception e){
			model.addAttribute("listErrorMessage",e.getMessage());
		}
		return "redirect:/mis/pois";
	}
	
	
	@GetMapping("/mis/pois")
	public String cargarMisPoIs(Model model) {		

		Authentication auth = SecurityContextHolder
	            .getContext()
	            .getAuthentication();
	    UserDetails userTurista = (UserDetails) auth.getPrincipal();
	    try {
			Turista turistaEncontrado = turistaService.encontrarUnTuristaPorEmail(userTurista.getUsername());
			
			if (turistaEncontrado != null) {
				
				
				model.addAttribute("Poi", poiService.obtenerPoiNuevo());
				model.addAttribute("pois", poiService.obtenerMisPoIs(turistaEncontrado));
			
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return("mispoiss");
	}
	@GetMapping({"/home"})
	public String cargarhome(Model model){
		model.addAttribute("pois", poiService.obtenerTodosPoIs());

		return "home";
	}
	
	@GetMapping({"/punto"})
	public String cargarpunto(Model model){
		model.addAttribute("pois", poiService.obtenerTodosPoIs());
		model.addAttribute("valoracion", valoracionService.crearUnaValoracion());
		return "punto";
	}
	
	
}

