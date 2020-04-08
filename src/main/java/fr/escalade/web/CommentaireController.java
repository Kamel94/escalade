package fr.escalade.web;

import java.security.Principal;
import java.time.Instant;
import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.escalade.dao.CommentaireRepository;
import fr.escalade.dao.SiteRepository;
import fr.escalade.dao.TopoRepository;
import fr.escalade.dao.UtilisateurRepository;
import fr.escalade.entities.Commentaire;
import fr.escalade.entities.Site;
import fr.escalade.entities.Topo;
import fr.escalade.entities.Utilisateur;

@Controller
public class CommentaireController {

	@Autowired
	TopoRepository topoRepository;

	@Autowired
	CommentaireRepository commentaireRepository;
	
	@Autowired
	SiteRepository siteRepository;
	
	@Autowired
	UtilisateurRepository utilisateurRepository;


	/* @GetMapping(value = "/user/commentaire")
	public String commentaire(Model model, 
			@RequestParam(name="page", defaultValue = "0") int p,
			@RequestParam(name="size", defaultValue = "6") int s,
			@RequestParam(name="motCle", defaultValue = "") String mc) {

		Page<Commentaire> pageSites = commentaireRepository.chercher( mc  , PageRequest.of(p, s));
		model.addAttribute("listeCommentaires", pageSites.getContent());
		int[] pages = new int[pageSites.getTotalPages()];

		model.addAttribute("pages", pages);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", mc);

		return "commentaire";
	}*/

	@GetMapping(value = "/commentaire/{id}")
	public String commentaire(Model model, @PathVariable("id")int id, 
			@RequestParam(name="page", defaultValue = "0") int p,
			@RequestParam(name="size", defaultValue = "6") int s) {

		Page<Commentaire> commentaire = commentaireRepository.chercher(id, PageRequest.of(p, s) );
		
		model.addAttribute("liste", commentaire);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);

		return "commentaire";
	}

	@GetMapping(value="/user/ajoutCom/{id}")
	public String ajoutCom(Model model, @PathVariable("id") int id, Principal principal) {
		Site site = siteRepository.findById(id).orElse(null);
		Utilisateur utilisateur = utilisateurRepository.findUtilisateurByPseudo(principal.getName());
		
		model.addAttribute("utilisateur", utilisateur);
		model.addAttribute("site", site);
		model.addAttribute("commentaire", new Commentaire(id));
		model.addAttribute("localDate", LocalDateTime.now());
		return "ajoutCom"; 
	}

	@RequestMapping(value="/user/modifierCom/{site}", method=RequestMethod.GET)
	public String modifierCom(Model model, @PathVariable("site")int siteId, 
			Principal principal, int id) {
		Commentaire commentaire = commentaireRepository.findById(id).orElse(null);
		Site site = siteRepository.findById(siteId).orElse(null);
		Utilisateur utilisateur = utilisateurRepository.findUtilisateurByPseudo(principal.getName());
		
		model.addAttribute("utilisateur", utilisateur);
		model.addAttribute("site", site);
		model.addAttribute("commentaire", commentaire);
		model.addAttribute("localDate", LocalDateTime.now());
		return "modifCom"; 
	}

	@GetMapping(value="/user/supprimerCom/{site}")
	public String supprimerCom(Integer id) {
		commentaireRepository.deleteById(id);
		return "redirect:/siteDetail/{site}";
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

}
