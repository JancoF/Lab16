package com.miempresa.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.miempresa.interfaceServicio.IClienteServicio;
import com.miempresa.modelo.Cliente;

import jakarta.validation.Valid;


@Controller
@RequestMapping
public class controlador {
	
	@Autowired
	private IClienteServicio servicio;
	
	@GetMapping("/listarClientes")
	public String listarClientes(Model model) {
	    List<Cliente> clientes = servicio.listar();  	
	    model.addAttribute("clientes", clientes);
	    return "clientes";
	}
	
	@GetMapping("/agregarCliente")
	public String agregarCliente(Model model) {
	    model.addAttribute("cliente", new Cliente());
	    return "agregarCliente";
	}

	@PostMapping("/guardarCliente")
    public String guardarCliente(@Valid Cliente cliente, BindingResult result) {
        if (result.hasErrors()) {
            return "agregarCliente";
        }
        servicio.guardar(cliente);
        return "redirect:/listarClientes";
    }
	
	@GetMapping("/editarCliente/{id}")
	public String editarCliente(@PathVariable int id, RedirectAttributes atributos) {
	    Optional<Cliente> cliente = servicio.listarId(id);
	    atributos.addFlashAttribute("cliente", cliente);
	    return "redirect:/mostrarCliente";
	}

	@GetMapping("/mostrarCliente")
	public String mostrarCliente(@ModelAttribute("cliente") Cliente c, Model model) {
	    model.addAttribute("cliente", c);
	    return "agregarCliente";
	}

	@GetMapping("/eliminarCliente/{id}")
	public String eliminarCliente(@PathVariable int id) {
	    servicio.borrar(id);
	    return "redirect:/listarClientes";
	}

	@GetMapping("/buscarCliente")
	public String busquedaCliente(Model model, @RequestParam(value = "nombre", required = false) String nombre) {
	    System.out.println("Nombre a buscar: " + nombre);
	    List<Cliente> clientes;
	    if (nombre != null && !nombre.isEmpty()) {
	        // Si hay un nombre proporcionado, realiza una búsqueda con ese nombre
	        clientes = servicio.listar(nombre);
	    } else {
	        // Si no hay un nombre, obtén todos los clientes
	        clientes = servicio.listar();
	    }
	    System.out.println("Resultados de la búsqueda: " + clientes);
	    model.addAttribute("clientes", clientes);
	    return "clientes";
	}

	@GetMapping("/login")
	public String showLogin() {
	    return "login";
	}
	
	@GetMapping("/error")
    public String showErrorPage() {
        return "error";
    }
}
