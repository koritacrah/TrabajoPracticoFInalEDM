package ar.edu.unju.edm.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Turista;
import ar.edu.unju.edm.repository.ITuristaDAO;

@Service
public class LoginTuristaServiceImp implements UserDetailsService{

	@Autowired
	ITuristaDAO iTuristaDAO;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		// metodo que nos devuelve detalles de un usuario
		System.out.println("ccccccccccccccccc");
		Turista turistaEncontrado = iTuristaDAO.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Usuario inválido"));
		
		//generamos una lista de autorizaciones
		List<GrantedAuthority> tipos = new ArrayList<>();
		System.out.println("fffffffffffffffff"+turistaEncontrado.getPerfil());
		
		//generamos tipo de autorizaciones
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(turistaEncontrado.getPerfil());
		System.out.println("ddddddddddddddd");
		tipos.add(grantedAuthority);
		
		
		//usuario que va a estar en sesion
		UserDetails user = (UserDetails) new User(email, turistaEncontrado.getPassword(), tipos);
		System.out.println("tuvieja"+turistaEncontrado.getPassword());
		return user;
	}
	

}
