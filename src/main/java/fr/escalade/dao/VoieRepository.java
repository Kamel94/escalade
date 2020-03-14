package fr.escalade.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.escalade.entities.Voie;

public interface VoieRepository extends JpaRepository<Voie, Integer> {

	@Query("select v from Voie v where v.secteurNom like :x")
	Page<Voie> chercher(@Param("x")String mc, Pageable pageable);

}
