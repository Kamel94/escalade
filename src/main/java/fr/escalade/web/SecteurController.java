package fr.escalade.web;

import java.security.Principal;
import java.time.LocalDateTime;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import fr.escalade.dao.UtilisateurRepository;
import fr.escalade.entities.Secteur;
import fr.escalade.entities.Site;
import fr.escalade.entities.Utilisateur;

@Controller
public class SecteurController {

	@Autowired
	private SiteRepository siteRepository;

	@Autowired
	private SecteurRepository secteurRepository;

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(SecteurController.class);

	/*
	 * Permet d'afficher la liste des secteurs pour un site.
	 */
	@GetMapping(value="/secteur/{id}")
	public String secteurSite(@PathVariable("id")int id, Model model, Principal principal,
			@RequestParam(name = "page", defaultValue = "0")int p,
			@RequestParam(name = "size", defaultValue = "5")int s) {

		Page<Secteur> secteur = secteurRepository.findBySite(id, PageRequest.of(p, s));
		model.addAttribute("secteur", secteur);

		Site site = siteRepository.findById(id).orElse(null);

		model.addAttribute("site", site);

		if(principal == null){
			Utilisateur utilisateur = new Utilisateur();
			utilisateur.setPseudo("visiteur");
			utilisateur.setStatut("VISITEUR");
			model.addAttribute("utilisateur", utilisateur);
		} else if(principal != null) {
			Utilisateur utilisateur = utilisateurRepository.findUtilisateurByPseudo(principal.getName());
			model.addAttribute("utilisateur", utilisateur);
		}

		return "secteur";
	}

	@GetMapping(value="/user/ajoutSecteur/{id}")
	public String ajoutSecteur(Model model, @PathVariable("id")int id, Principal principal) {
		Utilisateur utilisateur = utilisateurRepository.findUtilisateurByPseudo(principal.getName());
		Site site = siteRepository.findById(id).orElse(null);

		model.addAttribute("site", site);
		model.addAttribute("utilisateur", utilisateur);
		model.addAttribute("secteur", new Secteur(id));
		model.addAttribute("localDate", LocalDateTime.now());

		return "ajoutSecteur";
	}

	@RequestMapping(value="/user/modifierSecteur/{id}", method=RequestMethod.GET)
	public String modifierSecteur(Model model, @PathVariable("id")int id, Principal principal) {

		Secteur secteur = secteurRepository.findById(id).orElse(null);
		Site site = siteRepository.findById(secteur.getSite()).orElse(null);
		Utilisateur utilisateur = utilisateurRepository.findUtilisateurByPseudo(principal.getName());

		model.addAttribute("utilisateur", utilisateur);
		model.addAttribute("site", site);
		model.addAttribute("secteur", secteur);
		model.addAttribute("localDate", LocalDateTime.now());

		return "modifSecteur";
	}

	@GetMapping(value="/user/supprimerSecteur")
	public String supprimerSecteur(int id) {
		logger.info("Le secteur " + id + " a été supprimé");
		secteurRepository.deleteById(id);
		return "secteur";
	}

	@RequestMapping(value="/user/enregistrerSecteur/{id}", method=RequestMethod.POST)
	public String enregistrerSecteur(Model model,@PathVariable("id")int id, @Valid Secteur secteur, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			logger.warn("Erreur lors de l'ajout du secteur : " + bindingResult.getFieldError());
			return "ajoutSecteur";
		}

		Site site = siteRepository.findById(id).orElse(null);

		model.addAttribute("site", site);
		secteurRepository.save(secteur);
		return "confirmationSecteur";
	}

}
