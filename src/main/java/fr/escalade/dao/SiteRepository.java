package fr.escalade.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.escalade.entities.Site;

public interface SiteRepository extends JpaRepository<Site, String> {
	
	@Query("select s from Site s where lower(s.nom) like lower(:x) or lower(s.pays) like lower(:y) or lower(s.region) like lower(:r) or lower(s.ville) like lower(:v)")
	Page<Site> chercher(@Param("x")String mc, @Param("y")String m, @Param("r")String region, @Param("v")String ville, Pageable pageable);
	
	/*@Query(value = "SELECT s FROM Site s ORDER BY s.nom")
	Page<Site> chercher(Pageable pageable);*/
	@Query("select s from Site s where s.nom like :x")
	Site finById(@Param("x")String id);
	
	List<Site> findByNom(String nom);

}
