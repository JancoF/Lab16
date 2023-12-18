package com.miempresa.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.miempresa.interfaceServicio.IClienteServicio;
import com.miempresa.modelo.Cliente;


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
	public String guardarCliente(Cliente cliente) {
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
	    System.out.println(nombre);
	    List<Cliente> clientes = servicio.listar(nombre);
	    model.addAttribute("clientes", clientes);
	    return "buscarCliente";
	}

	@GetMapping("/login")
	public String showLogin() {
	    return "login";
	}

	@GetMapping("/error403")
	public String showErrorPage() {
	    return "error403";
	}


}
