package fr.escalade.web;

/*import java.util.ArrayList;
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
import org.springframework.web.servlet.ModelAndView;

import fr.escalade.dao.LongueurRepository;
import fr.escalade.dao.VoieRepository;
import fr.escalade.entities.Longueur;
import fr.escalade.entities.Topo;
import fr.escalade.entities.Voie;


public class LongueurController {
	
	@Autowired
	private LongueurRepository longueurRepository;

	@GetMapping(value = "/longueur/{id}")
	public String longueur(Model model,
			@PathVariable("id")int id) {
		
		List<Longueur> liste = longueurRepository.findByNumeroVoie(id);
		model.addAttribute("listeLongueur", liste);
		
		return "longueur";
	}
	
	String lien(int numeroVoie, int numeroLongueur) {
		if(numeroVoie == numeroLongueur) {
			return "longueur";
		}
		return "voie";
	}*/
	
	/*@GetMapping(value = "/voie")
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
	}

	@GetMapping(value="/user/ajoutLongueur/{id}")
	public String ajoutLongueur(Model model, Longueur longueur, @PathVariable("id")int id) {
		model.addAttribute("longueur", new Longueur(id));
		return "ajoutLongueur";
	}

	@RequestMapping(value="/user/modifierLongueur", method=RequestMethod.GET)
	public String modifierLongueur(Model model, int id) {
		Longueur longueur = longueurRepository.findById(id).orElse(null);
		model.addAttribute("longueur", longueur);
		return "modifLongueur";
	}

	@GetMapping(value="/admin/supprimerLongueur")
	public String supprimerLongueur(int id) {
		longueurRepository.deleteById(id);
		return "redirect:/longueur";
	}

	@RequestMapping(value="/user/enregistrerLongueur", method=RequestMethod.POST)
	public String enregistrerLongueur(Model model, @Valid Longueur longueur, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "ajoutLongueur";
		}
		longueurRepository.save(longueur);
		return "confirmationLongueur";
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
	}

}*/
