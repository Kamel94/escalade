package fr.escalade.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.escalade.entities.Voie;

public interface VoieRepository extends JpaRepository<Voie, Integer> {

	Page<Voie> findBySecteurId(int mc, Pageable pageable);

}
