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
import dev.fujioka.iagolima.Clinica.model.Funcionario;
import dev.fujioka.iagolima.Clinica.repository.FuncionarioRepository;


@RestController
@RequestMapping("/api")
public class FuncionarioController {
	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	@GetMapping("/funcionario")
	public List<Funcionario> getFuncioario(){
		return funcionarioRepository.findAll();
	}
	@PostMapping("/funcionario")
	public Funcionario saveFuncionario(@Valid @RequestBody Funcionario funcionario) {
		Funcionario funcionarioSalvo = funcionarioRepository.save(funcionario);
		return funcionarioSalvo;
	}
	@PutMapping("/funcionario/{id}")
	public Funcionario updateFuncionario(@PathVariable(value = "id") Long id, @Valid @RequestBody Funcionario funcionarioDetails) {

		Funcionario funcionario = funcionarioRepository.findById(id).
				orElseThrow(() -> new PersonNotFoundException("Funcionario", "id", id));
						
		funcionario.setNome(funcionarioDetails.getNome());
		funcionario.setSobrenome(funcionarioDetails.getSobrenome());
		funcionario.setCpf(funcionarioDetails.getCpf());
		funcionario.setIdade(funcionarioDetails.getIdade());
		funcionario.setCargo(funcionarioDetails.getCargo());

		Funcionario updatedFuncionario = funcionarioRepository.save(funcionario);
		return updatedFuncionario;
	}

	@DeleteMapping("/funcionario/{id}")
	public ResponseEntity<Funcionario> deleteFuncionario(@PathVariable(value = "id") Long id) {

		Funcionario funcionario = funcionarioRepository.findById(id)
				.orElseThrow(() -> new PersonNotFoundException("Funcionario", "id", id));

		funcionarioRepository.delete(funcionario);
		return ResponseEntity.ok().build();
	}


}
