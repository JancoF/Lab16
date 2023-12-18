package com.miempresa.interfaces;

import org.springframework.data.repository.CrudRepository;

import com.miempresa.modelo.Cliente;

public interface ICliente extends CrudRepository<Cliente, Integer>{

}
