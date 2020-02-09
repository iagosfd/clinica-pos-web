package dev.fujioka.iagolima.Clinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.fujioka.iagolima.Clinica.model.Exames;

@Repository
public interface ExamesRepository extends JpaRepository<Exames, Long> {

}
