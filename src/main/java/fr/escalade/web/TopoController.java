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
		int[] pages = new int[pageTopos.getTotalPages()];

		if(principal == null) {
			Utilisateur utilisateur = utilisateurRepository.findUtilisateurByPseudo("visiteur");
			model.addAttribute("utilisateur", utilisateur);
		} else {
			Utilisateur utilisateur = utilisateurRepository.findUtilisateurByPseudo(principal.getName());
			model.addAttribute("utilisateur", utilisateur);
		}
		
		model.addAttribute("listeTopos", pageTopos.getContent());
		model.addAttribute("pages", pages);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", mc);

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
	public String modifier(Model model, int id) {
		Topo t = topoRepository.findById(id).orElse(null);
		Utilisateur utilisateur = utilisateurRepository.findUtilisateurById(t.getProprietaire());

		model.addAttribute("utilisateur", utilisateur);
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
	public String supprimer(int id) {
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
		
		Utilisateur utilisateur = utilisateurRepository.findUtilisateurByPseudo(principal.getName());
		
		Page<Topo> topos = topoRepository.findByProprietaireOrderByNom(utilisateur.getId(), PageRequest.of(p, s));
		//	model.addAttribute("topos", topos);
		
		//model.addAttribute("utilisateur", u);
		model.addAttribute("topos", topos.getContent());
		int[] pages = new int[topos.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);
		return "listemestopos";
	}

	@GetMapping(value = "/user/topo/{nom}")
	public String topoId(Model model, Principal principal, @PathVariable("nom")String nom, 
			@RequestParam(name="page", defaultValue = "0") int p,
			@RequestParam(name="size", defaultValue = "4") int s,
			@RequestParam(name="motCle", defaultValue = "") String mc) {

		Utilisateur u = utilisateurRepository.findUtilisateurByPseudo(principal.getName());
		Topo topo = topoRepository.findTopoByNom(nom);
		
		Page<Topo> topos = topoRepository.findByProprietaireOrderByNom(u.getId(), PageRequest.of(p, s));
		model.addAttribute("topos", topos);
		
		if(topo.getEmprunteur() != null) {
			Utilisateur utilisateur = utilisateurRepository.findUtilisateurById(topo.getEmprunteur());
			model.addAttribute("utilisateur", utilisateur);
		} else {
			Utilisateur utilisateur = utilisateurRepository.findUtilisateurById(topo.getProprietaire());
			model.addAttribute("utilisateur", utilisateur);
		}
		
		model.addAttribute("topo", topo);

		return "topoId";
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

		return "listeMesDemandes";
	}
	
	/*@GetMapping(value="/user/contact/{nom}")
	public String contact(@PathVariable("nom")String nom, Model model, 
			@RequestParam(name="page", defaultValue = "0") int p,
			@RequestParam(name="size", defaultValue = "10") int s,
			@RequestParam(name="motCle", defaultValue = "") String mc) {
		
		Page<Topo> topos = topoRepository.findAll(PageRequest.of(p, s));
		int[] pages = new int[topos.getTotalPages()];
		
		Topo topo = topoRepository.findByProprietaireOrderByNom(principal.getName());
		model.addAttribute("tp", topo);

		Utilisateur utilisateur = utilisateurRepository.findUtilisateurByPseudo(topo.getProprietaire());
		model.addAttribute("utilisateur", utilisateur);
		
		model.addAttribute("topos", topos.getContent());
		model.addAttribute("pages", pages);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", mc);
		
		Topo topo = topoRepository.findById(nom).orElse(null);
		model.addAttribute("tp", topo);
		
		Utilisateur utilisateur = utilisateurRepository.findUtilisateurByPseudo(topo.getProprietaire());
		model.addAttribute("utilisateur", utilisateur);
		
		return "contact";
	}*/

	@GetMapping("/user/demandepret/{id}")
	public String gererDemandeEmpruntTopo(@PathVariable("id") int id, Principal principal, Model model) {
		Topo topo = topoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Nom topo inconnu : " + id));

		if(principal == null) {
			return "redirect:/login";
		} else if (topo.getDisponibilite().equals("Disponible") && topo.getEmprunteur() == null) {
			Utilisateur emprunteur = utilisateurRepository.findUtilisateurByPseudo(principal.getName());
			LocalDateTime dateTime = LocalDateTime.now();
			Utilisateur proprio = utilisateurRepository.findUtilisateurById(topo.getProprietaire());
			ReservationTopo resa = new ReservationTopo(topo.getId(), "Demande", emprunteur.getId(), topo.getProprietaire(), Timestamp.valueOf(dateTime), Timestamp.valueOf(dateTime));
			//resa.setDemandeur(principal.getName());
			topo.setEmprunteur(emprunteur.getId());
			topo.setDemandeur(emprunteur.getId());
			topo.setContact(proprio.getId());
			topo.setDisponibilite("Demande");
			reservationRepository.save(resa);
		} else {
		}
		topoRepository.save(topo);
		return "redirect:/topos";
	}

	@GetMapping("/user/accepterpret/{id}")
	public String accepterPretTopo(@PathVariable("id") int id, Principal principal, Model model) {
		Topo topo = topoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Nom topo inconnu pour acceptation prêt: " + id));
		topo.setDisponibilite("Indisponible");

		LocalDateTime dateTime = LocalDateTime.now();
		ReservationTopo resa = reservationRepository.findByTopoIdAndReponseDemande(id, "Demande");

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
	public String redisponible(@PathVariable("id") int id, Principal principal, Model model) {
		Topo topo = topoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Identifiant topo inconnu pour refus de prêt: " + id));
		
		topo.setDisponibilite("Disponible");
		topo.setEmprunteur(null);
		topo.setDemandeur(null);
		topo.setContact(null);

		topoRepository.save(topo);
		return "redirect:/user/listeMesTopos";
	}

	@GetMapping("/user/refuserpret/{id}")
	public String refuserPretTopo(@PathVariable("id") int id, Principal principal, Model model) {
		Topo topo = topoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Identifiant topo inconnu pour refus de prêt: " + id));
		LocalDateTime dateTime = LocalDateTime.now();
		
		ReservationTopo resa = reservationRepository.findByTopoIdAndReponseDemande(id, "Demande");

		model.addAttribute("resa", resa);
		
		resa.setReponseDemande("Refusée");
		resa.setDateModif(Timestamp.valueOf(dateTime));
		
		topo.setDisponibilite("Disponible");
		topo.setEmprunteur(null);
		topo.setContact(null);

		//reservationRepository.save(resa);
		topoRepository.save(topo);
		return "redirect:/user/listeMesTopos";
	}

	@GetMapping(value = "/user/reserver")
	public String reserver() {
		return "reservation";
	}

	@GetMapping("/403")
	public String nonAutorise() {
		return "403";
	}

}
