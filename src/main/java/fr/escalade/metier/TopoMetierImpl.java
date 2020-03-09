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
	public Topo consulterTopo(Integer id) {
		
		Topo topo = topoRepository.findById(id).orElse(null);
		
		if(topo == null) throw new RuntimeException("Topo introuvable");
		return topo;
	}

	@Override
	public void ajouterTopo(Model model, Integer id) {
		Topo topo = consulterTopo(id);
		  model.addAttribute("ajout", new Topo());
		
	}

	@Override
	public void supprimerTopo(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void proprietaire(Integer id, String proprietaire) {
		// TODO Auto-generated method stub
		
	}

}
