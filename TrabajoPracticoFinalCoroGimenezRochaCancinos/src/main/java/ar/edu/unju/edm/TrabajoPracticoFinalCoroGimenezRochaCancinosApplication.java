package ar.edu.unju.edm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ar.edu.unju.edm.model.Turista;
import ar.edu.unju.edm.service.ITuristaService;

@SpringBootApplication
public class TrabajoPracticoFinalCoroGimenezRochaCancinosApplication implements CommandLineRunner{
	
	@Autowired
	@Qualifier("implementacionMYSQLturista")
	ITuristaService turistaService;
	
	@Autowired
	Turista unTurista;

	public static void main(String[] args) {
		SpringApplication.run(TrabajoPracticoFinalCoroGimenezRochaCancinosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
//		unTurista.setApellido("prueba");
//		unTurista.setEmail("correo@gmail.com");
//		unTurista.setNombre("prueba");
//		unTurista.setPassword("123456");
//		unTurista.setPaís("Argentina");
//		unTurista.setPerfil("userNormal");
//		turistaService.guardarTurista(unTurista);
	}

}
