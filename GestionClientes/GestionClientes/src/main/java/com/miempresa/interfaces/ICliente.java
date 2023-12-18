package com.miempresa.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.miempresa.modelo.Cliente;

@Repository
public interface ICliente extends CrudRepository<Cliente, Integer>{
	Cliente findByNombre(String nombre);
}
