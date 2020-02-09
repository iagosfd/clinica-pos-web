package dev.fujioka.iagolima.Clinica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.fujioka.iagolima.Clinica.model.Funcionario;


@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
	List<Funcionario> findFuncioarioByCpf(int cpf);

}
