package fr.escalade.web;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.escalade.dao.SecteurRepository;
import fr.escalade.dao.SiteRepository;
import fr.escalade.dao.TopoRepository;
import fr.escalade.dao.UtilisateurRepository;
import fr.escalade.entities.InfoSite;
import fr.escalade.entities.Secteur;
import fr.escalade.entities.Site;
import fr.escalade.entities.Topo;
import fr.escalade.entities.Utilisateur;

@Controller
public class SecteurController {

	@Autowired
	private SiteRepository siteRepository;

	@Autowired
	private SecteurRepository secteurRepository;
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;

	/*@GetMapping(value = "/secteur")
	public String secteur(Model model, 
			@RequestParam(name="page", defaultValue = "0") int p,
			@RequestParam(name="size", defaultValue = "6") int s,
			@RequestParam(name="motCle", defaultValue = "") String mc) {
		
		Page<Secteur> listeSecteur = secteurRepository.chercher("%" + mc + "%", PageRequest.of(p, s));
		model.addAttribute("listeSecteurs", listeSecteur.getContent());
		int[] listes = new int[listeSecteur.getTotalPages()];
		model.addAttribute("secteur", listes);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", mc);

		return "secteur";
	}*/
	
	@GetMapping(value="/secteur/{id}")
	public String secteurSite(@PathVariable("id")int id, Model model, Principal principal) {
		List<Secteur> secteur = secteurRepository.findBySite(id);
		model.addAttribute("secteur", secteur);
		
		Site site = siteRepository.findById(id).orElse(null);
		
		model.addAttribute("site", site);

		Utilisateur u = new Utilisateur();
		
		if(principal == null){
			u.setStatut("VISITEUR");
			Utilisateur utilisateur = utilisateurRepository.findUtilisateurByStatut(u.getStatut());
			model.addAttribute("utilisateur", utilisateur);
		} else if(principal != null) {
			Utilisateur utilisateur = utilisateurRepository.findUtilisateurByPseudo(principal.getName());
			model.addAttribute("utilisateur", utilisateur);
		}
		
		return "secteur";
	}

	@GetMapping(value="/user/ajoutSecteur/{id}")
	public String ajoutSecteur(Model model, @PathVariable("id")int id) {
		
		model.addAttribute("secteur", new Secteur(id));
		model.addAttribute("localDate", LocalDateTime.now());
		
		return "ajoutSecteur";
	}

	@RequestMapping(value="/user/modifierSecteur", method=RequestMethod.GET)
	public String modifierSecteur(Model model, int id) {
		
		Secteur secteur = secteurRepository.findById(id).orElse(null);
		model.addAttribute("secteur", secteur);
		model.addAttribute("localDate", LocalDateTime.now());
		
		return "modifSecteur";
	}

	@GetMapping(value="/user/supprimerSecteur")
	public String supprimerSecteur(int id) {
		secteurRepository.deleteById(id);
		return "secteur";
	}

	@RequestMapping(value="/user/enregistrerSecteur", method=RequestMethod.POST)
	public String enregistrerSecteur(Model model, @Valid Secteur secteur, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "ajoutSecteur";
		}
		secteurRepository.save(secteur);
		return "confirmationSecteur";
	}

	/*@GetMapping(value = "/informations")
	public String informationsSites(Model model, 
			@RequestParam(name="page", defaultValue = "0") int p,
			@RequestParam(name="size", defaultValue = "6") int s,
			@RequestParam(name="motCle", defaultValue = "") String mc, 
			@RequestParam int id) {

		Page<Site> pageSites = siteRepository.chercher("%" + mc + "%", PageRequest.of(p, s));

		model.addAttribute("listeSites", pageSites.getContent());
		int[] pages = new int[pageSites.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", mc);
		model.addAttribute("id", id);
		return "informations";
	}*/

}
