package fr.escalade.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.escalade.entities.Topo;
import fr.escalade.entities.Utilisateur;

public interface TopoRepository extends JpaRepository<Topo, String> {
	
	@Query("select p from Topo p where p.nom like :x")
	Page<Topo> chercher(@Param("x")String mc, Pageable pageable);

	//List<Topo> findByProprietaireOrderByNom(String name);
	
	List<Topo> findByProprietaireOrderByDisponibilite(String name);

	Page<Topo> findByProprietaireOrderByNom(String name, Pageable pageable);
	
	//Topo findByNom(String u);
	
	Topo findByProprietaire(String p);
	
	List<Topo> findByNom(String u);

}
