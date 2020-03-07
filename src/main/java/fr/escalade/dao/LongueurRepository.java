package fr.escalade.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.escalade.entities.Longueur;

public interface LongueurRepository extends JpaRepository<Longueur, Integer> {

}
