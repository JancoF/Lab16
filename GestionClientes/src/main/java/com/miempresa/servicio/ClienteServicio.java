package com.miempresa.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miempresa.interfaceServicio.IClienteServicio;
import com.miempresa.interfaces.ICliente;
import com.miempresa.modelo.Cliente;

@Service
public class ClienteServicio implements IClienteServicio{

	@Autowired
	private ICliente repo;
	
	@Override
	public List<Cliente> listar() {
		return (List<Cliente>)repo.findAll();
	}
	
	@Override
	public Optional<Cliente> listarId(int id) {
	    return repo.findById(id);
	}

	@Override
	public int guardar(Cliente cliente) {
	    Cliente c = repo.save(cliente);
	    if (c != null) {
	        return 1;
	    }
	    return 0;
	}

	@Override
	public void borrar(int id) {
	    repo.deleteById(id);
	}
  
	@Override
	public List<Cliente> listar(String palabraClave) {
	    return repo.findAllByPalabraClave(palabraClave);
	}

	
}
