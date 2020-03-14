package fr.escalade.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.escalade.entities.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, String> {
	
	@Query("select u from Utilisateur u where u.nom like :x")
	List<Utilisateur> proprio ( @Param("x")String proprietaire);
	
	Utilisateur findUtilisateurByPseudo(String pseudo);

}
