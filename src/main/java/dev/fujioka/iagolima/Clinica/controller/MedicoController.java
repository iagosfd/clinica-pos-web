package dev.fujioka.iagolima.Clinica.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.fujioka.iagolima.Clinica.exception.PersonNotFoundException;
import dev.fujioka.iagolima.Clinica.model.Medico;
import dev.fujioka.iagolima.Clinica.repository.MedicoRepository;

@RestController
@RequestMapping("/api")
public class MedicoController {

	@Autowired
	MedicoRepository medicoRepository;

	@GetMapping("/medico")
	public List<Medico> getMedico() {
		return medicoRepository.findAll();
	}

	@PostMapping("/medico")
	public Medico saveMedico(@Valid @RequestBody Medico medico) {
		Medico medicoSalvo = medicoRepository.save(medico);
		return medicoSalvo;
	}

	@PutMapping("/medico/{id}")
	public Medico updateMedico(@PathVariable(value = "id") Long id, @Valid @RequestBody Medico medicoDetails) {

		Medico medico = medicoRepository.findById(id)
				.orElseThrow(() -> new PersonNotFoundException("Medico", "id", id));

		medico.setNome(medicoDetails.getNome());
		medico.setSobrenome(medicoDetails.getSobrenome());
		medico.setCrm(medicoDetails.getCrm());
		medico.setEspecialidade(medicoDetails.getEspecialidade());

		Medico updatedMedico = medicoRepository.save(medico);
		return updatedMedico;
	}

	@DeleteMapping("/medico/{id}")
	public ResponseEntity<Medico> deleteMedico(@PathVariable(value = "id") Long id) {

		Medico medico = medicoRepository.findById(id)
				.orElseThrow(() -> new PersonNotFoundException("Medico", "id", id));

		medicoRepository.delete(medico);
		return ResponseEntity.ok().build();
	}
}
