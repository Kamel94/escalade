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

import fr.escalade.dao.InfoSiteRepository;
import fr.escalade.dao.SiteRepository;
import fr.escalade.dao.TopoRepository;
import fr.escalade.entities.InfoSite;
import fr.escalade.entities.Site;
import fr.escalade.entities.Topo;

@Controller
public class TopoController {
	
	@Autowired
	private TopoRepository topoRepository;
	
	@Autowired
	private SiteRepository siteRepository;
	
	@Autowired
	private InfoSiteRepository infoSiteRepository;
	
	@GetMapping(value = "/accueil")
	public String accueil(Model model, 
			@RequestParam(name="page", defaultValue = "0") int p,
			@RequestParam(name="size", defaultValue = "6") int s,
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
	
	@GetMapping(value="/admin/ajout")
	public String ajout(Model model) {
		model.addAttribute("topo", new Topo());
		return "Ajout";
	}
	
	@RequestMapping(value="/admin/modifier", method=RequestMethod.GET)
	public String modifier(Model model, Integer id) {
		Topo t = topoRepository.findById(id).orElse(null);
		model.addAttribute("topo", t);
		return "Modif";
	}
	
	@GetMapping(value="/admin/supprimer")
	public String supprimer(Integer id, String motCle, int page, int size) {
		topoRepository.deleteById(id);
		return "redirect:/accueil?page=" + page + "&size=" + size + "&motCle=" + motCle ;
	}
	
	@RequestMapping(value="/admin/enregistrer", method=RequestMethod.POST)
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
	
	@GetMapping(value = "/informations")
	public String informations(Model model, 
			@RequestParam(name="page", defaultValue = "0") int p,
			@RequestParam(name="size", defaultValue = "6") int s,
			@RequestParam(name="motCle", defaultValue = "") String mc) {
		
		Page<InfoSite> pageSites = infoSiteRepository.chercher("%" + mc + "%", PageRequest.of(p, s));
		
		model.addAttribute("listeSites", pageSites.getContent());
		int[] pages = new int[pageSites.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", mc);
		return "informations";
	}
	
	@GetMapping("/")
	public String defaut() {
		return "redirect:/accueil";
	}
	
	@GetMapping("/403")
	public String nonAuthaurise() {
		return "403";
	}

}
