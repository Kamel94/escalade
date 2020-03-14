package fr.escalade.metier;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import fr.escalade.dao.TopoRepository;
import fr.escalade.entities.Topo;

@Service
@Transactional
public class TopoMetierImpl implements ITopoMetier {

	private TopoRepository topoRepository;
	@Override
	public Topo consulterTopo(String nom) {
		
		Topo topo = topoRepository.findById(nom).orElse(null);
		
		if(topo == null) throw new RuntimeException("Topo introuvable");
		return topo;
	}

	@Override
	public void ajouterTopo(Model model, String nom) {
		Topo topo = consulterTopo(nom);
		  model.addAttribute("ajout", new Topo());
		
	}

	@Override
	public void supprimerTopo(String nom) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void proprietaire(String nom, String proprietaire) {
		// TODO Auto-generated method stub
		
	}

}
