package fr.escalade.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.escalade.entities.Site;

public interface SiteRepository extends JpaRepository<Site, String> {

	@Query("select s from Site s where lower(s.nom) like lower(:x) and lower(s.pays) like lower(:y) and lower(s.region) like lower(:r)")
	Page<Site> chercher(@Param("x")String mc, @Param("y")String m, @Param("r")String region, Pageable pageable);

	@Query("select s from Site s where s.nombreSecteur = ?1")
	List<Site> cherche(int id);
	
	/*@Query(value = "SELECT s FROM Site s ORDER BY s.nom")
	Page<Site> chercher(Pageable pageable);

	@Query("select s from Site s where lower(s.nom) like lower(:x)")
	Page<Site> chercher(@Param("x")String mc, Pageable pageable);*/

	//"SELECT DISTINCT a FROM Article a  WHERE a.genre.idGenre = :genre"

	@Query("select s from Site s where s.nom like :x")
	List<Site> finById(@Param("x")String id);

	List<Site> findByNom(String nom);

}
