package fr.escalade.web;

import java.util.List;

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
import fr.escalade.dao.LongueurRepository;
import fr.escalade.dao.SecteurRepository;
import fr.escalade.dao.SiteRepository;
import fr.escalade.dao.TopoRepository;
import fr.escalade.dao.UtilisateurRepository;
import fr.escalade.dao.VoieRepository;
import fr.escalade.entities.InfoSite;
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
	private LongueurRepository longueurRepository;

	@GetMapping(value = "/voie")
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
	}*/

	@GetMapping(value="/user/ajoutVoie")
	public String ajoutVoie(Model model, Voie voie) {
		model.addAttribute("voie", new Voie());
		return "ajoutVoie";
	}

	@RequestMapping(value="/user/modifierVoie", method=RequestMethod.GET)
	public String modifierVoie(Model model, int id) {
		Voie voie = voieRepository.findById(id).orElse(null);
		model.addAttribute("voie", voie);
		return "modifVoie";
	}

	@GetMapping(value="/admin/supprimerVoie")
	public String supprimerVoie(int id, String motCle, int page, int size) {
		voieRepository.deleteById(id);
		return "redirect:/voie?page=" + page + "&size=" + size + "&motCle=" + motCle ;
	}

	@RequestMapping(value="/user/enregistrerVoie", method=RequestMethod.POST)
	public String enregistrerVoie(Model model, @Valid Voie voie, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "ajoutVoie";
		}
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
