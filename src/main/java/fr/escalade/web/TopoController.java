package fr.escalade.web;

import java.security.Principal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
public class TopoController {

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

	@GetMapping(value = "/topos")
	public String topos(Principal principal, Model model, 
			@RequestParam(name="page", defaultValue = "0") int p,
			@RequestParam(name="size", defaultValue = "4") int s,
			@RequestParam(name="motCle", defaultValue = "") String mc) {

		Page<Topo> pageTopos = topoRepository.chercher("%" + mc + "%", PageRequest.of(p, s));
		Utilisateur utilisateur = utilisateurRepository.findUtilisateurByPseudo(principal.getName());
		int[] pages = new int[pageTopos.getTotalPages()];

		model.addAttribute("utilisateur", utilisateur);
		model.addAttribute("listeTopos", pageTopos.getContent());
		model.addAttribute("pages", pages);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", mc);

		return "topos";
	}

	@GetMapping(value = "/topos/{id}")
	public String topoSite(Model model, @PathVariable("id")String id) {

		List<Topo> topos = topoRepository.findByNom(id);
		model.addAttribute("listeTopos", topos);

		return "topos";
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

	/*@RequestMapping(value="/user/commentaire", method=RequestMethod.GET)
	public String commentaire(Model model, String id) {
		Topo t = topoRepository.findById(id).orElse(null);
		model.addAttribute("topo", t);
		return "commentaire";
	}*/

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
	public String listeToposDunUtilisateur(Principal principal, Model model, 
			@RequestParam(name="page", defaultValue = "0") int p,
			@RequestParam(name="size", defaultValue = "4") int s,
			@RequestParam(name="motCle", defaultValue = "") String mc) {
		//List<Topo> topos = topoRepository.findByProprietaireOrderByNom(principal.getName());
		
		Page<Topo> topos = topoRepository.findByProprietaireOrderByNom(principal.getName(), PageRequest.of(p, s));
		//	model.addAttribute("topos", topos);

		model.addAttribute("topos", topos.getContent());
		int[] pages = new int[topos.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);
		return "listemestopos";
	}

	@GetMapping(value="/user/listeMesDemandes")
	public String listeMesDemandes(Principal principal, Model model, 
			@RequestParam(name="page", defaultValue = "0") int p,
			@RequestParam(name="size", defaultValue = "10") int s,
			@RequestParam(name="motCle", defaultValue = "") String mc) {

		Page<Topo> topos = topoRepository.findAll(PageRequest.of(p, s));
		int[] pages = new int[topos.getTotalPages()];

		model.addAttribute("topos", topos.getContent());
		model.addAttribute("pages", pages);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", mc);
		
		List<ReservationTopo> r = reservationRepository.findAll();
		model.addAttribute("r", r);
		
		

		/*(Principal principal, Model model, String n) {
		List<Topo> topos = topoRepository.findByProprietaireOrderByNom(principal.getName());
		model.addAttribute("topos", topos);
		Utilisateur emprunteur = utilisateurRepository.findUtilisateurByPseudo(principal.getName());
		model.addAttribute("emprunteur", emprunteur);

		List<Topo> topos = topoRepository.findAll();
		model.addAttribute("topos", topos);*/

		return "listeMesDemandes";
	}

	@GetMapping("/user/demandepret/{id}")
	public String gererDemandeEmpruntTopo(@PathVariable("id") String id, Principal principal, Model model) {
		Topo topo = topoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Nom topo inconnu : " + id));
		if (topo.getDisponibilite().equals("Disponible") && topo.getEmprunteur() == null) {
			Utilisateur emprunteur = utilisateurRepository.findUtilisateurByPseudo(principal.getName());
			LocalDateTime dateTime = LocalDateTime.now();
			ReservationTopo resa = new ReservationTopo(topo.getNom(), "Demande", principal.getName(), topo.getProprietaire(), Timestamp.valueOf(dateTime));
			//resa.setDemandeur(principal.getName());
			topo.setEmprunteur(emprunteur);
			topo.setDisponibilite("Demande");
			reservationRepository.save(resa);
		} else {
			if (topo.getDisponibilite().equals("Demande") && topo.getEmprunteur().getPseudo().equals(principal.getName())) {
				Utilisateur emprunteur = utilisateurRepository.findUtilisateurByPseudo(principal.getName());
				topo.setDisponibilite("Disponible");
				topo.setEmprunteur(null);
			} else {
			}
		}
		topoRepository.save(topo);
		return "redirect:/topos";
	}

	@GetMapping("/user/accepterpret/{id}")
	public String accepterPretTopo(@PathVariable("id") String id, Principal principal, Model model) {
		Topo topo = topoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Nom topo inconnu pour acceptation prêt: " + id));
		topo.setDisponibilite("Indisponible");

		LocalDateTime dateTime = LocalDateTime.now();
		ReservationTopo resa = reservationRepository.findByTopoNomAndReponseDemande(id, "Demande");

		//resa.setUtilisateurModif(topo.getProprietaire());
		resa.setReponseDemande("Acceptée");
		resa.setDateModif(Timestamp.valueOf(dateTime));

		reservationRepository.save(resa);
		topoRepository.save(topo);
		return "redirect:/user/listeMesTopos";
	}
	
	/*@GetMapping("/user/refuser/{id}")
	public String refuserPret(@PathVariable("id") String id, Principal principal, Model model) {
		Topo topo = topoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Identifiant topo inconnu pour refus de prêt: " + id));
		
		
		topo.setDisponibilite("Disponible");
		topo.setEmprunteur(null);

		//reservationRepository.save(resa);
		topoRepository.save(topo);
		return "redirect:/user/listeMesTopos";
	}*/
	
	@GetMapping("/user/redisponible/{id}")
	public String redisponible(@PathVariable("id") String id, Principal principal, Model model) {
		Topo topo = topoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Identifiant topo inconnu pour refus de prêt: " + id));
		
		topo.setDisponibilite("Disponible");
		topo.setEmprunteur(null);

		topoRepository.save(topo);
		return "redirect:/user/listeMesTopos";
	}

	@GetMapping("/user/refuserpret/{id}")
	public String refuserPretTopo(@PathVariable("id") String id, Principal principal, Model model) {
		Topo topo = topoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Identifiant topo inconnu pour refus de prêt: " + id));
		LocalDateTime dateTime = LocalDateTime.now();
		
		ReservationTopo resa = reservationRepository.findByTopoNomAndReponseDemande(id, "Demande");

		resa.setReponseDemande("Refusée");
		resa.setDateModif(Timestamp.valueOf(dateTime));
		
		topo.setDisponibilite("Disponible");
		topo.setEmprunteur(null);

		//reservationRepository.save(resa);
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

	@GetMapping("/accueil")
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
		return "accueil";
	}

	@GetMapping("/403")
	public String nonAutorise() {
		return "403";
	}

}
