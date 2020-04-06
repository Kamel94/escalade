package fr.escalade.web;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
import fr.escalade.dao.VoieRepository;
import fr.escalade.entities.Longueur;
import fr.escalade.entities.Secteur;
import fr.escalade.entities.Site;
import fr.escalade.entities.Topo;
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

	/*@GetMapping(value = "/voie")
	public String voie(Model model, 
			@RequestParam(name="page", defaultValue = "0") int p,
			@RequestParam(name="size", defaultValue = "6") int s,
			@RequestParam(name="motCle", defaultValue = "") String mc) {

		Page<Voie> listeVoie = voieRepository.chercher("%" + mc + "%", PageRequest.of(p, s));
		model.addAttribute("listeVoie", listeVoie.getContent());
		int[] voies = new int[listeVoie.getTotalPages()];
		model.addAttribute("voie", voies);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", mc);

		return "voie";
	}

	@GetMapping(value = "/voie")
	public String longueur(Model model, 
			@RequestParam(name="page", defaultValue = "0") int p,
			@RequestParam(name="size", defaultValue = "6") int s,
			@RequestParam(name="motCle", defaultValue = "") String mc) {

		Page<Longueur> listeLongueur = longueurRepository.chercher("%" + mc + "%", PageRequest.of(p, s));
		model.addAttribute("listeLongueur", listeLongueur.getContent());
		int[] longueurs = new int[listeLongueur.getTotalPages()];
		model.addAttribute("longueur", longueurs);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", mc);

		return "voie";
	}*/

	@GetMapping(value="/voie/{site}/{id}")
	public String secteurSite(@PathVariable("id")int id, Model model, @PathVariable("site")int site,
			Principal principal) {
		List<Voie> voie = voieRepository.findBySecteurId(id);
		Site sit = siteRepository.findById(site).orElse(null);
		Secteur secteur = secteurRepository.getOne(id);
		
		model.addAttribute("voie", voie);
		model.addAttribute("site", sit);
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
	public String ajoutVoie(Model model, Voie voie, @PathVariable("site")int site, @PathVariable("id")int nom) {

		model.addAttribute("voie", new Voie(nom));
		Site sit = siteRepository.findById(site).orElse(null);
		model.addAttribute("site", sit);
		model.addAttribute("localDate", LocalDateTime.now());
		return "ajoutVoie";
	}

	@RequestMapping(value="/user/modifierVoie/{site}/{nom}", method=RequestMethod.GET)
	public String modifierVoie(Model model, int id, @PathVariable("site")int site) {
		Voie voie = voieRepository.findById(id).orElse(null);
		model.addAttribute("voie", voie);
		Site sit = siteRepository.findById(site).orElse(null);
		model.addAttribute("site", sit);
		model.addAttribute("localDate", LocalDateTime.now());
		return "modifVoie";
	}

	@GetMapping(value="/admin/supprimerVoie/{site}/{nom}/{id}")
	public String supprimerVoie(Model model, @PathVariable("id")int id, @PathVariable("site")int site) {
		voieRepository.deleteById(id);
		Site sit = siteRepository.findById(site).orElse(null);
		model.addAttribute("site", sit);
		return "redirect:/voie/{site}/{nom}" ;
	}

	@RequestMapping(value="/user/enregistrerVoie/{site}", method=RequestMethod.POST)
	public String enregistrerVoie(Model model, @Valid Voie voie, BindingResult bindingResult, @PathVariable("site")int site) {

		Site sit = siteRepository.findById(site).orElse(null);
		if(bindingResult.hasErrors()) { 
			model.addAttribute("site", sit);
			model.addAttribute("localDate", LocalDateTime.now());
			return "ajoutVoie"; 
		}

		model.addAttribute("site", sit);
		voieRepository.save(voie);
		return "confirmationVoie";
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
