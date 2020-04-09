package fr.escalade.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.escalade.entities.Topo;

public interface TopoRepository extends JpaRepository<Topo, Integer> {

	@Query("select p from Topo p where p.nom like :x")
	Page<Topo> chercher(@Param("x")String mc, Pageable pageable);

	Page<Topo> findByProprietaireOrderByNom(int name, Pageable pageable);

	Topo findTopoByNom(String nom);

	List<Topo> findTopoBySite(int site);

}
