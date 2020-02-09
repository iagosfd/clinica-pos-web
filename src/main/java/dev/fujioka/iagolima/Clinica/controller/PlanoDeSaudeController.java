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
import dev.fujioka.iagolima.Clinica.model.PlanoDeSaude;
import dev.fujioka.iagolima.Clinica.repository.PlanoDeSaudeRepository;

@RestController
@RequestMapping("/api")
public class PlanoDeSaudeController {
	
	@Autowired
	PlanoDeSaudeRepository planoRepository;
	
	@GetMapping("/plano")
	public List<PlanoDeSaude> getPlanoDeSaude(){
		return planoRepository.findAll();
	}
	@PostMapping("/plano")
	public PlanoDeSaude savePlanoDeSaude(@Valid @RequestBody PlanoDeSaude plano) {
		PlanoDeSaude planoSalvo = planoRepository.save(plano);
		return planoSalvo;
	}
	@PutMapping("/plano/{id}")
	public PlanoDeSaude updatePlanoDeSaude(@PathVariable(value = "id") Long id, @Valid @RequestBody PlanoDeSaude planoDetails) {

		PlanoDeSaude plano = planoRepository.findById(id).
				orElseThrow(() -> new PersonNotFoundException("Plano", "id", id));
						
		plano.setNomePlano(planoDetails.getNomePlano());
		plano.setCobertura(planoDetails.getCobertura());
		
		PlanoDeSaude updatedPlanoDeSaude = planoRepository.save(plano);
		return updatedPlanoDeSaude;
	}

	@DeleteMapping("/plano/{id}")
	public ResponseEntity<PlanoDeSaude> deletePlanoDeSaude(@PathVariable(value = "id") Long id) {

		PlanoDeSaude plano = planoRepository.findById(id)
				.orElseThrow(() -> new PersonNotFoundException("Plano", "id", id));

		planoRepository.delete(plano);
		return ResponseEntity.ok().build();
	}

}
	


