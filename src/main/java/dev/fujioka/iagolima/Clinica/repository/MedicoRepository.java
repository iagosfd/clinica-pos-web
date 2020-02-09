package dev.fujioka.iagolima.Clinica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import dev.fujioka.iagolima.Clinica.model.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico,Long>{
	List<Medico> findMedicoByCrm(int crm);
	
	

}
