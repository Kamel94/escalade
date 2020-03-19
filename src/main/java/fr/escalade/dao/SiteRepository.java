package fr.escalade.dao;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.escalade.entities.Site;

public interface SiteRepository extends JpaRepository<Site, String> {
	
	@Query("select s from Site s where s.nom like :x or s.pays like :y or s.region like :r or s.ville like :v")
	Page<Site> chercher(@Param("x")String mc, @Param("y")String m, @Param("r")String region, @Param("v")String ville, Pageable pageable);
	
	/*@Query(value = "SELECT s FROM Site s ORDER BY s.nom")
	Page<Site> chercher(Pageable pageable);*/
	
	Site findByNom(String nom);

}
