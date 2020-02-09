package dev.fujioka.iagolima.Clinica.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.fujioka.iagolima.Clinica.exception.PersonNotFoundException;
import dev.fujioka.iagolima.Clinica.model.Paciente;
import dev.fujioka.iagolima.Clinica.repository.PacienteRepository;

@RestController
@RequestMapping("/api")
public class PacienteController {

	@Autowired
	PacienteRepository pacienteRepository;
	
	@GetMapping("/paciente")
	public List<Paciente> getPaciente(){
		return pacienteRepository.findAll();
	}
	@PostMapping("/paciente")
	public Paciente savePaciente(@Valid @RequestBody Paciente paciente) {
		Paciente pacienteSalvo = pacienteRepository.save(paciente);
		return pacienteSalvo;
	}
	@PutMapping("/paciente/{id}")
	public Paciente updatePaciente(@PathVariable(value = "id") Long id, @Valid @RequestBody Paciente pacienteDetails) {

		Paciente paciente = pacienteRepository.findById(id).
				orElseThrow(() -> new PersonNotFoundException("Paciente", "id", id));
						
		paciente.setNome(pacienteDetails.getNome());
		paciente.setSobrenome(pacienteDetails.getSobrenome());
		paciente.setCpf(pacienteDetails.getCpf());
		paciente.setIdade(pacienteDetails.getIdade());
		paciente.setEmail(pacienteDetails.getEmail());

		Paciente updatedPaciente = pacienteRepository.save(paciente);
		return updatedPaciente;
	}

	@DeleteMapping("/paciente/{id}")
	public ResponseEntity<Paciente> deletePaciente(@PathVariable(value = "id") Long id) {

		Paciente paciente = pacienteRepository.findById(id)
				.orElseThrow(() -> new PersonNotFoundException("Paciente", "id", id));

		pacienteRepository.delete(paciente);
		return ResponseEntity.ok().build();
	}

}
