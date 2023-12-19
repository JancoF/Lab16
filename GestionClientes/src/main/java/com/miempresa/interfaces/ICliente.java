package com.miempresa.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.miempresa.modelo.Cliente;

@Repository
public interface ICliente extends CrudRepository<Cliente, Integer>{
	Cliente findByNombre(String nombre);
	
	@Query("SELECT e FROM Cliente e WHERE e.nombre LIKE %:palabraClave%")
    public List<Cliente> findAllByPalabraClave(@Param("palabraClave") String palabraClave);
	
}
