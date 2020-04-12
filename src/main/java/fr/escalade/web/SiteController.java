package fr.escalade.web;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

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

import fr.escalade.dao.CommentaireRepository;
import fr.escalade.dao.SecteurRepository;
import fr.escalade.dao.SiteRepository;
import fr.escalade.dao.TopoRepository;
import fr.escalade.dao.UtilisateurRepository;
import fr.escalade.entities.Commentaire;
import fr.escalade.entities.Secteur;
import fr.escalade.entities.Site;
import fr.escalade.entities.Topo;
import fr.escalade.entities.Utilisateur;

@Controller
public class SiteController {

	@Autowired
	private SiteRepository siteRepository;

	@Autowired
	private SecteurRepository secteurRepository;

	@Autowired
	private CommentaireRepository commentaireRepository;

	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Autowired
	private TopoRepository topoRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(SiteController.class);

	@GetMapping("/")
	public String defaut() {
		return "redirect:/accueil";
	}

	@GetMapping("/accueil")
	public String accueil(Model model, Principal principal, 
			@RequestParam(name="page", defaultValue = "0") int p,
			@RequestParam(name="size", defaultValue = "6") int s,
			@RequestParam(name="nom", defaultValue = "") String nom,
			@RequestParam(name="pays", defaultValue = "") String pays,
			@RequestParam(name="region", defaultValue = "") String region,
			@RequestParam(name="sec", defaultValue = "0") int sec) {

		Page<Site> pageSites = siteRepository.chercher("%" + nom + "%", "%" + pays + "%", "%" + region + "%", sec, PageRequest.of(p, s));
		model.addAttribute("listeSites", pageSites.getContent());
		int[] pages = new int[pageSites.getTotalPages()];

		model.addAttribute("pages", pages);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);
		model.addAttribute("nom", nom);
		model.addAttribute("pays", pays);
		model.addAttribute("region", region);
		model.addAttribute("sec", sec);

		if(principal != null) {
			Utilisateur utilisateur = utilisateurRepository.findUtilisateurByPseudo(principal.getName());
			model.addAttribute("utilisateur", utilisateur);
		} else {
			Utilisateur utilisateur = utilisateurRepository.findUtilisateurByPseudo("visiteur");
			model.addAttribute("utilisateur", utilisateur);
		}

		List<Site> site = siteRepository.findAll();
		model.addAttribute("site", site);
		List<Secteur> secteur = secteurRepository.findAll();
		model.addAttribute("secteur", secteur);
		return "accueil";
	}

	/*
	 * Affiche le détail d'un site.
	 */
	@GetMapping(value="/siteDetail/{id}")
	public String site(@PathVariable("id")int id, Model model, Principal principal,
			@RequestParam(name="page", defaultValue = "0") int p,
			@RequestParam(name="size", defaultValue = "6") int s) {
		Site site = siteRepository.findById(id).orElse(null);
		model.addAttribute("s", site);
		List<Secteur> secteur = secteurRepository.findBySite(id);
		model.addAttribute("secteur", secteur);
		List<Topo> topo = topoRepository.findTopoBySite(id);
		model.addAttribute("topo", topo);

		Page<Commentaire> commentaire = commentaireRepository.chercher(id, PageRequest.of(p, s));
		model.addAttribute("liste", commentaire);
		int[] pages = new int[commentaire.getTotalPages()];

		Utilisateur us = new Utilisateur();

		if(principal == null){
			us.setStatut("VISITEUR");
			Utilisateur utilisateur = utilisateurRepository.findUtilisateurByStatut(us.getStatut());
			model.addAttribute("utilisateur", utilisateur);
		} else if(principal != null) {
			Utilisateur util = utilisateurRepository.findUtilisateurByPseudo(principal.getName());
			model.addAttribute("utilisateur", util);
		}

		model.addAttribute("pages", pages);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);

		model.addAttribute("commentaire", new Commentaire(id));
		model.addAttribute("localDate", LocalDateTime.now());

		return "siteDetail";
	}

	/*
	 * Permet à un membre de taguer un site.
	 */
	@GetMapping(value="/membre/tag/{id}")
	public String tag(Model model, @PathVariable("id")int id) {

		Site site = siteRepository.getOne(id);
		logger.info("Le site " + site.getNom() + " a été tagué.");
		
		model.addAttribute("site", site);

		site.setTag(true);

		siteRepository.save(site);
		return "redirect:/siteDetail/{id}";
	}

	/*
	 * Permet à un membre de retire le tag d'un site.
	 */
	@GetMapping(value="/membre/enleverTag/{id}")
	public String enleverTag(Model model, @PathVariable("id")int id) {

		Site site = siteRepository.getOne(id);
		logger.info("Le site " + site.getNom() + " a perdu son tag.");
		
		model.addAttribute("site", site);

		site.setTag(false);

		siteRepository.save(site);
		return "redirect:/siteDetail/{id}";
	}

	@GetMapping(value="/user/ajoutSite")
	public String ajoutSite(Model model, Principal principal) {
		Utilisateur utilisateur = utilisateurRepository.findUtilisateurByPseudo(principal.getName());

		model.addAttribute("utilisateur", utilisateur);
		model.addAttribute("site", new Site());
		model.addAttribute("localDate", LocalDateTime.now());

		return "ajoutSite"; 
	}

	@RequestMapping(value="/user/modifierSite", method=RequestMethod.GET)
	public String modifierSite(Model model, int id, Principal principal) {
		Site site = siteRepository.findById(id).orElse(null);
		Utilisateur utilisateur = utilisateurRepository.findUtilisateurByPseudo(principal.getName());

		model.addAttribute("utilisateur", utilisateur);
		model.addAttribute("site", site);
		model.addAttribute("localDate", LocalDateTime.now());

		return "modifSite"; 
	}

	@GetMapping(value="/user/supprimerSite")
	public String supprimerSite(int id) {
		logger.info("Le site " + id + " a été supprimé");
		siteRepository.deleteById(id);
		return "redirect:/accueil";
	}

	@RequestMapping(value="/user/enregistrerSite", method=RequestMethod.POST)
	public String enregistrerSite(Model model, @Valid Site site, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			logger.warn("Erreur lors de l'ajout du site : " + bindingResult.getFieldError());
			return "ajoutSite";
		}
		siteRepository.save(site);
		return "confirmationSite";
	}

}
