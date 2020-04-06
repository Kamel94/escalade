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

public interface SiteRepository extends JpaRepository<Site, Integer> {

	@Query("select s from Site s where lower(s.nom) like lower(:nom) and lower(s.pays) like lower(:pays) and lower(s.region) like lower(:region) and s.nombreSecteur >= :sec")
	Page<Site> chercher(@Param("nom")String nom, @Param("pays")String m, @Param("region")String region, @Param("sec")int secteur, Pageable pageable);
	
	/*@Query(value = "SELECT distinct site.* FROM site " + "LEFT JOIN secteur on site.nom = secteur.site_nom " + 
	"WHERE (:region = 'all' OR site.region = :region) " + 
	"AND (:pays = 'all' OR site.pays = :pays) " + 
	"AND (site.nom = :motCle) " + 
	"AND (SELECT COUNT(secteur.nom) FROM secteur WHERE secteur.site_nom = site.nom) >= :nbrSecteur",
	nativeQuery = true)
	Page<Site> chercher(@Param("motCle")String motCle, @Param("region")String region, @Param("pays")String pays, @Param("nbrSecteur")int nbrSecteur, Pageable pageable);
	
	@Query(value = "SELECT s FROM Site s ORDER BY s.nom")
	Page<Site> chercher(Pageable pageable);

	@Query("select s from Site s where lower(s.nom) like lower(:x)")
	Page<Site> chercher(@Param("x")String mc, Pageable pageable);*/

	//"SELECT DISTINCT a FROM Article a  WHERE a.genre.idGenre = :genre"

	@Query("select s from Site s where s.nom like :x")
	List<Site> finById(@Param("x")String id);

	List<Site> findByNom(String nom);

}
