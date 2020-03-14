package fr.escalade.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.escalade.entities.Commentaire;

public interface CommentaireRepository extends JpaRepository<Commentaire, Integer> {


}
