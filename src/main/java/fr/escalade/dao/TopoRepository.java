package fr.escalade.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.escalade.entities.Topo;
import fr.escalade.entities.Utilisateur;

public interface TopoRepository extends JpaRepository<Topo, Integer> {
	
	@Query("select p from Topo p where p.nom like :x")
	Page<Topo> chercher(@Param("x")String mc, Pageable pageable);

	//List<Topo> findByProprietaireOrderByNom(String name);
	
	List<Topo> findByProprietaireOrderByDisponibilite(String name);

	Page<Topo> findByProprietaireOrderByNom(int name, Pageable pageable);
	
	//Topo findByNom(String u);
	
	Topo findByProprietaireOrderByNom(String dispo);
	
	Topo findByProprietaire(Integer integer);
	
	List<Topo> findByNom(String u);

	//@Query("select p from Topo p where p.nom like :x")
	Topo findByProprietaireOrderByNom(int i);

	Topo findTopoByNom(String nom);

}
