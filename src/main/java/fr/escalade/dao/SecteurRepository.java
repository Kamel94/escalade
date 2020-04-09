package fr.escalade.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.escalade.entities.Secteur;

public interface SecteurRepository extends JpaRepository<Secteur, Integer> {

	Page<Secteur> findBySite(int id, Pageable pageable);

	List<Secteur> findBySite(int id);

}
