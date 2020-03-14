package fr.escalade.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.escalade.entities.Longueur;

public interface LongueurRepository extends JpaRepository<Longueur, Integer> {
	
	@Query("select l from Longueur l where l.test like :x")
	Page<Longueur> rechercher(@Param("x")String mc, Pageable pageable );
	
	@Query("select l from Longueur l where l.niveau like :x")
	List<Longueur> findById(@Param("x")int id);

	@Query("select l from Longueur l where l.niveau like :x")
	List<Longueur> findAll(@Param("x")int id);
}
