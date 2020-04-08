package fr.escalade.dao;

import java.util.List;

//import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.escalade.entities.Commentaire;
import fr.escalade.entities.Site;

public interface CommentaireRepository extends JpaRepository<Commentaire, Integer> {

	@Query("select c from Commentaire c where c.site = :x")
	Page<Commentaire> chercher(@Param("x")int site, Pageable pageable);
	
	
	@Query("select c from Commentaire c where c.site like :x")
	List<Commentaire> findAll(@Param("x")String id);
	
	Commentaire findBySite(int id);
	
	/*@Query("select c from Commentaire c where c.id like :x")
	List<Commentaire> findById(@Param("x")int id);*/
	
}
