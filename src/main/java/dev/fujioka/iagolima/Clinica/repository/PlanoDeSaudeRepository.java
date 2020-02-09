package dev.fujioka.iagolima.Clinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.fujioka.iagolima.Clinica.model.PlanoDeSaude;

@Repository
public interface PlanoDeSaudeRepository extends JpaRepository<PlanoDeSaude, Long>{

}
