package fr.escalade.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.escalade.entities.Site;

public interface SiteRepository extends JpaRepository<Site, Integer> {

	@Query("select s from Site s where lower(s.nom) like lower(:nom) and lower(s.pays) like lower(:pays) and lower(s.region) like lower(:region) and s.nombreSecteur >= :sec")
	Page<Site> chercher(@Param("nom")String nom, @Param("pays")String m, @Param("region")String region, @Param("sec")int secteur, Pageable pageable);

	Site findSiteByNom(String nom);

}
