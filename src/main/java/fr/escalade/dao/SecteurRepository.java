package fr.escalade.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.escalade.entities.Secteur;
import fr.escalade.entities.Site;

public interface SecteurRepository extends JpaRepository<Secteur, Integer> {

	@Query("select s from Secteur s where s.nom like :x")
	Page<Secteur> chercher(@Param("x")String nom, Pageable pageable);
	
	@Query("select s from Secteur s where s.site like :x")
	List<Secteur> secteur(@Param("x")String nom);
	
	List<Secteur> findBySite(int id);
	
	/*"SELECT DISTINCT a FROM Article a  WHERE a.genre.idGenre = :genre"
	@Query("select distinct s from Secteur s where s.site.siteNom like :x")
	List<Secteur> sec(@Param("x")String nom);*/

}
