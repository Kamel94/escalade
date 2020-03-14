package fr.escalade.metier;

import org.springframework.ui.Model;

import fr.escalade.entities.Topo;

public interface ITopoMetier {
	
	Topo consulterTopo(String nom);
	void ajouterTopo(Model model, String nom);
	void supprimerTopo(String nom);
	void proprietaire(String nom, String proprietaire);

}
