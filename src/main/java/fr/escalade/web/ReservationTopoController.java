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

import fr.escalade.dao.ReservationTopoRepository;
import fr.escalade.dao.SecteurRepository;
import fr.escalade.dao.SiteRepository;
import fr.escalade.dao.TopoRepository;
import fr.escalade.dao.UtilisateurRepository;
import fr.escalade.entities.ReservationTopo;
import fr.escalade.entities.Secteur;
import fr.escalade.entities.Site;
import fr.escalade.entities.Topo;
import fr.escalade.entities.Utilisateur;

@Controller
public class ReservationTopoController {

	@Autowired
	private TopoRepository topoRepository;

	@Autowired
	private SiteRepository siteRepository;
	
	@Autowired
	private SecteurRepository secteurRepository;
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Autowired
	private ReservationTopoRepository reservationRepository;
	
	@GetMapping(value = "/admin/reservation")
	public String reservationTopo(Model model, 
			@RequestParam(name="page", defaultValue = "0") int p,
			@RequestParam(name="size", defaultValue = "4") int s,
			@RequestParam(name="motCle", defaultValue = "") String mc) {

		Page<ReservationTopo> page = reservationRepository.page("%" + mc + "%", PageRequest.of(p, s));

		model.addAttribute("reservation", page.getContent());
		int[] pages = new int[page.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", mc);

		return "reservation";
	}
	
	/*@GetMapping(value = "/accueil/{id}")
	public String topoSite(Model model, @PathVariable("id")String id) {

		List<Topo> topos = topoRepository.findByNom(id);
		model.addAttribute("listeTopos", topos);

		return "Accueil";
	}

	@GetMapping(value="/user/ajoutTopo/{id}")
	public String ajout(Model model, @PathVariable("id")String id) {
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

	@GetMapping(value="/supprimer")
	public String supprimer(String id) {
		topoRepository.deleteById(id);
		return "redirect:/listeMesTopos";
	}

	@RequestMapping(value="/user/enregistrer/{id}", method=RequestMethod.POST)
	public String enregistrer(Model model, @Valid Topo topo, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "redirect:/user/ajoutTopo/{id}";
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
		return "redirect:/accueil1";
	}
	
	@GetMapping("/accueil1")
	public String accueil(Model model, 
			@RequestParam(name="page", defaultValue = "0") int p,
			@RequestParam(name="size", defaultValue = "6") int s,
			@RequestParam(name="motCle", defaultValue = "") String mc,
			@RequestParam(name="pays", defaultValue = "") String pays,
			@RequestParam(name="region", defaultValue = "") String region) {

		Page<Site> pageSites = siteRepository.chercher("%" + mc + "%", "%" + pays + "%", "%" + region + "%", PageRequest.of(p, s));
		model.addAttribute("listeSites", pageSites.getContent());
		int[] pages = new int[pageSites.getTotalPages()];
		
		model.addAttribute("pages", pages);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", mc);
		
		List<Site> site = siteRepository.findAll();
		model.addAttribute("site", site);
		List<Secteur> secteur = secteurRepository.findAll();
		model.addAttribute("secteur", secteur);
		return "accueil1";
	}

	@GetMapping("/403")
	public String nonAutorise() {
		return "403";
	}*/

}
