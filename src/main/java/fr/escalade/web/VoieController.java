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
import fr.escalade.dao.VoieRepository;
import fr.escalade.entities.Secteur;
import fr.escalade.entities.Site;
import fr.escalade.entities.Utilisateur;
import fr.escalade.entities.Voie;

@Controller
public class VoieController {

	@Autowired
	private VoieRepository voieRepository;

	@Autowired
	private SiteRepository siteRepository;

	@Autowired
	private SecteurRepository secteurRepository;

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(VoieController.class);

	/*
	 * Affiche la liste des voies d'un secteur.
	 */
	@GetMapping(value="/voie/{site}/{id}")
	public String secteurSite(@PathVariable("id")int secteurId, Model model, 
			@PathVariable("site")int siteId, Principal principal,
			@RequestParam(name = "page", defaultValue = "0")int p,
			@RequestParam(name = "size", defaultValue = "5")int s) {

		Page<Voie> voie = voieRepository.findBySecteurId(secteurId, PageRequest.of(p, s));
		Site site = siteRepository.findById(siteId).orElse(null);
		Secteur secteur = secteurRepository.getOne(secteurId);

		model.addAttribute("voie", voie);
		model.addAttribute("site", site);
		model.addAttribute("secteur", secteur);

		Utilisateur u = new Utilisateur();

		if(principal == null){
			u.setStatut("VISITEUR");
			Utilisateur utilisateur = utilisateurRepository.findUtilisateurByStatut(u.getStatut());
			model.addAttribute("utilisateur", utilisateur);
		} else if(principal != null) {
			Utilisateur utilisateur = utilisateurRepository.findUtilisateurByPseudo(principal.getName());
			model.addAttribute("utilisateur", utilisateur);
		}

		return "voie";
	}

	@GetMapping(value="/user/ajoutVoie/{site}/{id}")
	public String ajoutVoie(Model model, Voie voie, @PathVariable("site")int site, @PathVariable("id")int id,
			Principal principal) {

		Site sit = siteRepository.findById(site).orElse(null);
		Secteur secteur = secteurRepository.findById(id).orElse(null);
		Utilisateur utilisateur = utilisateurRepository.findUtilisateurByPseudo(principal.getName());

		model.addAttribute("utilisateur", utilisateur);
		model.addAttribute("secteur", secteur);
		model.addAttribute("voie", new Voie(id));
		model.addAttribute("site", sit);
		model.addAttribute("localDate", LocalDateTime.now());
		return "ajoutVoie";
	}

	@RequestMapping(value="/user/modifierVoie/{site}/{secteur}/{id}", method=RequestMethod.GET)
	public String modifierVoie(Model model, @PathVariable("id")int id, @PathVariable("site")int siteId,
			@PathVariable("secteur")int secteurId, Principal principal) {

		Voie voie = voieRepository.findById(id).orElse(null);
		Site site = siteRepository.findById(siteId).orElse(null);
		Secteur secteur = secteurRepository.findById(secteurId).orElse(null);
		Utilisateur utilisateur = utilisateurRepository.findUtilisateurByPseudo(principal.getName());

		model.addAttribute("utilisateur", utilisateur);
		model.addAttribute("secteur", secteur);
		model.addAttribute("voie", voie);
		model.addAttribute("site", site);
		model.addAttribute("localDate", LocalDateTime.now());
		return "modifVoie";
	}

	@GetMapping(value="/supprimerVoie/{site}/{secteur}/{id}")
	public String supprimerVoie(Model model, @PathVariable("id")int id, @PathVariable("site")int site) {
		voieRepository.deleteById(id);
		logger.info("La voie " + id + " a été supprimé.");
		Site sit = siteRepository.findById(site).orElse(null);
		model.addAttribute("site", sit);
		return "redirect:/voie/{site}/{secteur}" ;
	}

	@RequestMapping(value="/user/enregistrerVoie/{site}/{secteur}", method=RequestMethod.POST)
	public String enregistrerVoie(Model model, @Valid Voie voie, BindingResult bindingResult, 
			@PathVariable("site")int siteId, @PathVariable("secteur")int secteurId) {

		Site site = siteRepository.findById(siteId).orElse(null);
		Secteur secteur = secteurRepository.findById(secteurId).orElse(null);

		if(bindingResult.hasErrors()) { 
			logger.warn("Erreur lors de l'ajout d'une voie " + bindingResult.getFieldError());
			model.addAttribute("site", site);
			model.addAttribute("localDate", LocalDateTime.now());
			return "ajoutVoie"; 
		}

		model.addAttribute("secteur", secteur);
		model.addAttribute("site", site);
		voieRepository.save(voie);
		return "confirmationVoie";
	}

}
