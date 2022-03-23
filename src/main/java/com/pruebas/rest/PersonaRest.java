package com.pruebas.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pruebas.dao.IPersonaDAO;
import com.pruebas.model.Persona;

@RestController
@RequestMapping("personas")
public class PersonaRest {

	@Autowired
	private IPersonaDAO personaDAO;

	@PostMapping("/guardar")
	public void guardar(@RequestBody Persona persona) {
		System.out.println(persona);
		personaDAO.save(persona);
	}

	@GetMapping("/listar")
	public List<Persona> listar() {
		return personaDAO.findAll();
	}

	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable("id") Integer id) {
		personaDAO.deleteById(id);

	}

	@PutMapping("/actualizar/{id}")
	public void actualizar(@PathVariable("id") Integer id, @RequestBody Persona persona) {
		Persona persona2 = personaDAO.getById(id);
		System.out.println(persona2);
		persona2.setApellido(persona.getApellido());
		personaDAO.save(persona2);
	}

	@GetMapping("/otraPersona")
	@ResponseBody
	public Persona getPersonaByLastName(@RequestParam(name = "lastname") String lastname) {
		Persona persona = new Persona();
		persona.setApellido(lastname);
		Example<Persona> valor = Example.of(persona);
		List<Persona> otherPerson = personaDAO.findAll(valor);
		return otherPerson.get(0);
	}
}
