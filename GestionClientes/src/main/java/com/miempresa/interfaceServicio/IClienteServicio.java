package com.miempresa.interfaceServicio;

import java.util.List;
import java.util.Optional;

import com.miempresa.modelo.Cliente;

public interface IClienteServicio {
	List<Cliente> listar();

	Optional<Cliente> listarId(int id);

	int guardar(Cliente p);

	void borrar(int id);

	List<Cliente> listar(String palabraClave);

}
