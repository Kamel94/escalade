package fr.escalade.metier;

import org.springframework.ui.Model;

import fr.escalade.entities.Topo;

public interface ITopoMetier {
	
	Topo consulterTopo(Integer id);
	void ajouterTopo(Model model, Integer id);
	void supprimerTopo(Integer id);
	void proprietaire(Integer id, String proprietaire);

}
