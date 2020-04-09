package fr.escalade.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.escalade.entities.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

	Utilisateur findUtilisateurByPseudo(String pseudo);

	Utilisateur findUtilisateurByStatut(String statut);

	Utilisateur findUtilisateurById(int proprietaire);

}
