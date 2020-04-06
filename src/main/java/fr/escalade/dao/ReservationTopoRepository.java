package fr.escalade.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.escalade.entities.ReservationTopo;

public interface ReservationTopoRepository extends JpaRepository<ReservationTopo, Integer> {
	
	@Query("select r from ReservationTopo r where r.topoId like :x")
	Page<ReservationTopo> page(@Param("x")int topo, Pageable pageable);
	
	ReservationTopo findByUtilisateurModif(int topo);

	ReservationTopo findByTopoIdAndReponseDemande(int id, String string);

}
