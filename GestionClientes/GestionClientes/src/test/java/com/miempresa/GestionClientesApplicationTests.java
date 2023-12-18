package com.miempresa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.miempresa.interfaces.IUsuario;
import com.miempresa.modelo.Usuario;

@SpringBootTest
class GestionClientesApplicationTests {

	@Autowired
	private IUsuario repo;
	
	@Bean
	public BCryptPasswordEncoder passEncoder() {
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
		return bcpe;
	}
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Test
	void crearUsuario(){
		Usuario user = new Usuario();
		user.setId(1);
		user.setNombre("Noe");
		user.setClave(encoder.encode("123456"));
		user.setRol("ROLE_ADMIN");
		repo.save(user);
	}
	
	@Test
	void crearUsuario2() {
		Usuario user = new Usuario();
		user.setId(2);
		user.setNombre("Esthefany");
		user.setClave(encoder.encode("1234"));
		user.setRol("ROLE_USER");
		repo.save(user);
	}
}
