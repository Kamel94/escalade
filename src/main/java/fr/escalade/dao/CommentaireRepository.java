package fr.escalade.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.escalade.entities.Commentaire;

public interface CommentaireRepository extends JpaRepository<Commentaire, Integer> {

	@Query("select c from Commentaire c where c.site = :x")
	Page<Commentaire> chercher(@Param("x")int site, Pageable pageable);

}
