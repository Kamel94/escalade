package fr.escalade.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.escalade.dao.SiteRepository;
import fr.escalade.dao.TopoRepository;
import fr.escalade.dao.UtilisateurRepository;
import fr.escalade.entities.Topo;

@Controller
public class TopoController {
	
	@Autowired
	private TopoRepository topoRepository;
	
	@Autowired
	private SiteRepository siteRepository;
	
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@GetMapping(value = "/accueil")
	public String accueil(Model model, 
			@RequestParam(name="page", defaultValue = "0") int p,
			@RequestParam(name="size", defaultValue = "2") int s,
			@RequestParam(name="motCle", defaultValue = "") String mc) {
		
		Page<Topo> pageTopos = topoRepository.chercher("%" + mc + "%", PageRequest.of(p, s));
		
		model.addAttribute("listeTopos", pageTopos.getContent());
		int[] pages = new int[pageTopos.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", mc);
		
		return "Accueil";
	}
	
	@GetMapping(value="/user/ajout")
	public String ajout(Model model, String nom) {
		model.addAttribute("topo", new Topo());
		return "Ajout";
	}
	
	@RequestMapping(value="/user/modifier", method=RequestMethod.GET)
	public String modifier(Model model, String id) {
		Topo t = topoRepository.findById(id).orElse(null);
		model.addAttribute("topo", t);
		return "Modif";
	}
	
	/*@RequestMapping(value="/user/commentaire", method=RequestMethod.GET)
	public String commentaire(Model model, String id) {
		Topo t = topoRepository.findById(id).orElse(null);
		model.addAttribute("topo", t);
		return "commentaire";
	}*/
	
	@GetMapping(value="/admin/supprimer")
	public String supprimer(String id, String motCle, int page, int size) {
		topoRepository.deleteById(id);
		return "redirect:/accueil?page=" + page + "&size=" + size + "&motCle=" + motCle ;
	}
	
	@RequestMapping(value="/user/enregistrer", method=RequestMethod.POST)
	public String enregistrer(Model model, @Valid Topo topo, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "Ajout";
		}
		topoRepository.save(topo);
		return "Confirmation";
	}
	
	@GetMapping(value = "/user/reserver")
	public String reserver() {
		return "reservation";
	}
	
	@GetMapping("/")
	public String defaut() {
		return "redirect:/accueil";
	}
	
	@GetMapping("/403")
	public String nonAutorise() {
		return "403";
	}

}
