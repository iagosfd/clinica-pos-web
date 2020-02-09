package dev.fujioka.iagolima.Clinica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import dev.fujioka.iagolima.Clinica.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>{
	List<Paciente> findPacienteByCpf(int cpf);

}
