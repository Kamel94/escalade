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
}
