package fr.escalade.web;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
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
import fr.escalade.dao.VoieRepository;
import fr.escalade.entities.Commentaire;
import fr.escalade.entities.Secteur;
import fr.escalade.entities.Site;
import fr.escalade.entities.Topo;
import fr.escalade.entities.Utilisateur;
import fr.escalade.entities.Voie;

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

	/*@GetMapping(value = "/site")
	public String site(Model model,      
			@RequestParam(name="page", defaultValue = "0") int p,
			@RequestParam(name="size", defaultValue = "6") int s,
			@RequestParam(name="motCle", defaultValue = "") String mc,
			@RequestParam(name="pays", defaultValue = "") String pays,
			@RequestParam(name="region", defaultValue = "") String region,
			@RequestParam(name="nbrSecteur", defaultValue = "0") int secteur,
			Principal principal) {

		Page<Site> pageSites = siteRepository.chercher( "%" + mc + "%", "%" + pays + "%", "%" + region + "%" , secteur, PageRequest.of(p, s));
		int[] pages = new int[pageSites.getTotalPages()];
		
		List<Site> site = siteRepository.finById("%" + mc + "%");
		Set<Site> mySet = new HashSet<Site>(pageSites.getContent());
		List<Site> list2 = new ArrayList<Site>(mySet);
		HashSet set = new HashSet() ;
        set.addAll(list2) ;
        ArrayList distinctList = new ArrayList(set);
		model.addAttribute("site", distinctList);
		
		List<Site> site = siteRepository.cherche(id);
		model.addAttribute("site", site);
		
		model.addAttribute("listeSites", pageSites.getContent());
		model.addAttribute("pages", pages);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", mc);
		model.addAttribute("pays", pays);
		model.addAttribute("region", region);
		model.addAttribute("nbrSecteur", secteur);
		
		return "site";
	}*/

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
	
	@GetMapping(value="/siteDetail/{id}")
	public String site(@PathVariable("id")int id, Model model, Principal principal,
			@RequestParam(name="page", defaultValue = "0") int p,
			@RequestParam(name="size", defaultValue = "6") int s) {
		Site site = siteRepository.findById(id).orElse(null);
		model.addAttribute("s", site);
		List<Secteur> secteur = secteurRepository.findBySite(id);
		model.addAttribute("secteur", secteur);
		
		Page<Commentaire> commentaire = commentaireRepository.chercher(id, PageRequest.of(p, s));
		model.addAttribute("liste", commentaire);
		int[] pages = new int[commentaire.getTotalPages()];
		
		/*Utilisateur utilisateur = utilisateurRepository.findUtilisateurByPseudo(principal.getName());
		//model.addAttribute("utilisateu", utilisateur);*/
		Utilisateur us = new Utilisateur();
		List<Utilisateur> u = utilisateurRepository.findAll();
		model.addAttribute("utilisateu", u);
		
		//Utilisateur util = utilisateurRepository.findUtilisateurByPseudo(principal.getName());

		
		/*Site si = siteRepository.getOne(id);
		model.addAttribute("si", si);
		
		if(si.getTag() == null) {
			si.setTag(false);
		}

		siteRepository.save(si);*/
		
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
	
	@GetMapping(value="/membre/tag/{id}")
	public String tag(Model model, @PathVariable("id")int id) {
		
		Site site = siteRepository.getOne(id);
		model.addAttribute("site", site);

		site.setTag(true);
		
		siteRepository.save(site);
		return "redirect:/siteDetail/{id}";
	}
	
	@GetMapping(value="/membre/enleverTag/{id}")
	public String enleverTag(Model model, @PathVariable("id")int id) {
		
		Site site = siteRepository.getOne(id);
		model.addAttribute("site", site);
		
		site.setTag(false);
			
		siteRepository.save(site);
		return "redirect:/siteDetail/{id}";
	}
	
	@RequestMapping(value="/user/enregistrerCom/{id}", method=RequestMethod.POST)
	public String enregistrerCom(Model model, @Valid Commentaire commentaire, 
			@PathVariable("id")int id, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "ajoutCom";
		}
		Site site = siteRepository.getOne(id);
		model.addAttribute("site", site);
		commentaireRepository.save(commentaire);
		return "confirmationCom";
	}
	
	/*
	 * @GetMapping(value="/siteDetail/{id}") public String
	 * secteurDetail(@PathVariable("id")String id, Principal principal, Model model)
	 * { List<Secteur> secteur = secteurRepository.findByNom(id);
	 * model.addAttribute("listeSecteurs", secteur); return "siteDetail"; }
	 */

	@GetMapping(value="/user/ajoutSite")
	public String ajoutSite(Model model, Principal principal) {
		Utilisateur utilisateur = utilisateurRepository.findUtilisateurByPseudo(principal.getName());
		
		model.addAttribute("utilisateur", utilisateur);
		model.addAttribute("site", new Site());
		model.addAttribute("localDate", LocalDateTime.now());
		
		return "ajoutSite"; 
	}

	@RequestMapping(value="/user/modifierSite", method=RequestMethod.GET)
	public String modifierSite(Model model, int id) {
		Site site = siteRepository.findById(id).orElse(null);
		model.addAttribute("site", site);
		model.addAttribute("localDate", LocalDateTime.now());
		
		return "modifSite"; 
	}

	@GetMapping(value="/user/supprimerSite")
	public String supprimerSite(int id, String motCle, int page, int size) {
		siteRepository.deleteById(id);
		return "redirect:/accueil";
	}

	@RequestMapping(value="/user/enregistrerSite", method=RequestMethod.POST)
	public String enregistrerSite(Model model, @Valid Site site, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "ajoutSite";
		}
		siteRepository.save(site);
		return "confirmationSite";
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
