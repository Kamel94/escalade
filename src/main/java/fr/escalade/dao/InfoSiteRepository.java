package fr.escalade.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.escalade.entities.InfoSite;

public interface InfoSiteRepository extends JpaRepository<InfoSite, String> {
	
	@Query("select p from InfoSite p where p.description like :x")
	Page<InfoSite> chercher(@Param("x")String mc, Pageable pageable);
	
	List<InfoSite> findBySite(String nom);

}
