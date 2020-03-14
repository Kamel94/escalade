package fr.escalade.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.escalade.dao.InfoSiteRepository;
import fr.escalade.dao.SiteRepository;
import fr.escalade.entities.InfoSite;
import fr.escalade.entities.Site;

@Controller
public class InformationController {
	
	@Autowired
	private InfoSiteRepository infoSiteRepository;
	
	private SiteRepository siteRepository;
	
	@GetMapping(value = "/informations")
	public String informations(Model model, 
			@RequestParam(name="page", defaultValue = "0") int p,
			@RequestParam(name="size", defaultValue = "6") int s,
			@RequestParam(name="motCle", defaultValue = "") String mc) {
		 
		Page<InfoSite> pageSites = infoSiteRepository.chercher("%" + mc + "%", PageRequest.of(p, s));
		//Page<Site> sites = siteRepository.chercher("%" + mc + "%", PageRequest.of(p, s));
		
		model.addAttribute("listeSites", pageSites.getContent());
		int[] pages = new int[pageSites.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", mc);
		return "informations";
	}

}
