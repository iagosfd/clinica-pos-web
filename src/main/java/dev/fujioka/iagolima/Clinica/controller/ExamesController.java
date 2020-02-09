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
import dev.fujioka.iagolima.Clinica.model.Exames;
import dev.fujioka.iagolima.Clinica.repository.ExamesRepository;

@RestController
@RequestMapping("/api")
public class ExamesController {
	
	@Autowired
	ExamesRepository examesRepository;
	
	@GetMapping("/exames")
	public List<Exames> getExames(){
		return examesRepository.findAll();
	}
	@PostMapping("/exames")
	public Exames saveExames(@Valid @RequestBody Exames exames) {
		Exames examesSalvo = examesRepository.save(exames);
		return examesSalvo;
	}
	@PutMapping("/exames/{id}")
	public Exames updateExames(@PathVariable(value = "id") Long id, @Valid @RequestBody Exames examesDetails) {

		Exames exames = examesRepository.findById(id).
				orElseThrow(() -> new PersonNotFoundException("Exames", "id", id));
						
		exames.setNomeExame(examesDetails.getNomeExame());
		exames.setValor(examesDetails.getValor());
		exames.setObservacao(examesDetails.getObservacao());
		
		Exames updatedExames = examesRepository.save(exames);
		return updatedExames;
	}

	@DeleteMapping("/exames/{id}")
	public ResponseEntity<Exames> deleteExames(@PathVariable(value = "id") Long id) {

		Exames exames = examesRepository.findById(id)
				.orElseThrow(() -> new PersonNotFoundException("Exames", "id", id));

		examesRepository.delete(exames);
		return ResponseEntity.ok().build();
	}

}
