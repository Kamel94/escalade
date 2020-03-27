package fr.escalade.web;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.escalade.dao.SiteRepository;
import fr.escalade.dao.TopoRepository;
import fr.escalade.dao.UtilisateurRepository;
import fr.escalade.entities.Site;
import fr.escalade.entities.Topo;
import fr.escalade.entities.Utilisateur;

@Controller
public class TopoController {

	@Autowired
	private TopoRepository topoRepository;

	@Autowired
	private SiteRepository siteRepository;
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@GetMapping(value = "/accueil")
	public String accueil(Model model, 
			@RequestParam(name="page", defaultValue = "0") int p,
			@RequestParam(name="size", defaultValue = "4") int s,
			@RequestParam(name="motCle", defaultValue = "") String mc) {

		Page<Topo> pageTopos = topoRepository.chercher("%" + mc + "%","%" + mc + "%", PageRequest.of(p, s));

		model.addAttribute("listeTopos", pageTopos.getContent());
		int[] pages = new int[pageTopos.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", mc);

		return "Accueil";
	}
	
	@GetMapping(value = "/accueil/{id}")
	public String topoSite(Model model, @PathVariable("id")String id) {

		List<Topo> topos = topoRepository.findByNom(id);
		model.addAttribute("listeTopos", topos);

		return "Accueil";
	}

	@GetMapping(value="/user/ajout")
	public String ajout(Model model, String id) {
		List<Site> site = siteRepository.findAll();
		model.addAttribute("site", site);
		model.addAttribute("topo", new Topo());
		return "Ajout";
	}

	@RequestMapping(value="/user/modifier", method=RequestMethod.GET)
	public String modifier(Model model, String id) {
		Topo t = topoRepository.findById(id).orElse(null);
		model.addAttribute("topo", t);
		return "Modif";
	}

	/*@RequestMapping(value="/user/commentaire", method=RequestMethod.GET)
	public String commentaire(Model model, String id) {
		Topo t = topoRepository.findById(id).orElse(null);
		model.addAttribute("topo", t);
		return "commentaire";
	}*/

	@GetMapping(value="/admin/supprimer")
	public String supprimer(String id) {
		topoRepository.deleteById(id);
		return "redirect:/listeMesTopos";
	}

	@RequestMapping(value="/user/enregistrer", method=RequestMethod.POST)
	public String enregistrer(Model model, @Valid Topo topo, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "Ajout";
		}
		topoRepository.save(topo);
		return "Confirmation";
	}

	@GetMapping(value="/user/listeMesTopos")
	public String listeToposDunUtilisateur(Principal principal, Model model) {
		List<Topo> topos = topoRepository.findByProprietaireOrderByNom(principal.getName());
		model.addAttribute("topos", topos);
		return "listemestopos";
	}
	
	@GetMapping(value="/user/listeMesDemandes")
	public String listeMesDemandes(Principal principal, Model model) {

		List<Topo> topos = topoRepository.findAll();
		model.addAttribute("topos", topos);
		
		/*(Principal principal, Model model, String n) {
		List<Topo> topos = topoRepository.findByProprietaireOrderByNom(principal.getName());
		model.addAttribute("topos", topos);
		Utilisateur emprunteur = utilisateurRepository.findUtilisateurByPseudo(principal.getName());
		model.addAttribute("emprunteur", emprunteur);*/
		return "listeMesDemandes";
	}

	@GetMapping("/user/demandepret/{id}")
	public String gererDemandeEmpruntTopo(@PathVariable("id") String id, Principal principal, Model model) {
		Topo topo = topoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Nom topo inconnu : " + id));
		if (topo.getDisponibilite().equals("Disponible") && topo.getEmprunteur() == null) {
			Utilisateur emprunteur = utilisateurRepository.findUtilisateurByPseudo(principal.getName());
			topo.setEmprunteur(emprunteur);
			topo.setDisponibilite("Demande");
		} else {
			if (topo.getDisponibilite().equals("Demande") && topo.getEmprunteur().getPseudo().equals(principal.getName())) {
				Utilisateur emprunteur = utilisateurRepository.findUtilisateurByPseudo(principal.getName());
				topo.setDisponibilite("Disponible");
				topo.setEmprunteur(null);
			} else {
			}
		}
		topoRepository.save(topo);
		return "redirect:/accueil";
	}

	@GetMapping("/user/accepterpret/{id}")
	public String accepterPretTopo(@PathVariable("id") String id, Principal principal, Model model) {
		Topo topo = topoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Nom topo inconnu pour acceptation prêt: " + id));
		topo.setDisponibilite("Indisponible");
		topoRepository.save(topo);
		return "redirect:/user/listeMesTopos";
	}

	@GetMapping("/user/refuserpret/{id}")
	public String refuserPretTopo(@PathVariable("id") String id, Principal principal, Model model) {
		Topo topo = topoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Identifiant topo inconnu pour refus de prêt: " + id));
		topo.setDisponibilite("Disponible");
		topo.setEmprunteur(null);
		topoRepository.save(topo);
		return "redirect:/user/listeMesTopos";
	}

	@GetMapping(value = "/user/reserver")
	public String reserver() {
		return "reservation";
	}

	@GetMapping("/")
	public String defaut() {
		return "redirect:/accueil";
	}

	@GetMapping("/403")
	public String nonAutorise() {
		return "403";
	}

}
