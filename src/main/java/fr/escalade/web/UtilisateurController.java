package fr.escalade.web;

import java.security.Principal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.escalade.dao.UtilisateurRepository;
import fr.escalade.entities.Utilisateur;

@Controller
public class UtilisateurController {

	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Autowired 
	PasswordEncoder passwordEncoder;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@GetMapping(value = "/inscription")
	public String inscription(Model model, Principal principal) {

		List<Utilisateur> utilisateur = utilisateurRepository.findAll();
		LocalDateTime dateTime = LocalDateTime.now();

		model.addAttribute("utilisateur", new Utilisateur());
		model.addAttribute("localDate", dateTime);
		
		if(principal != null) {
			Utilisateur u = utilisateurRepository.findUtilisateurByPseudo(principal.getName());
			model.addAttribute("u", u);
		} else {
			Utilisateur u = utilisateurRepository.findUtilisateurByPseudo("visiteur");
			model.addAttribute("u", u);
		}
		
		return "inscription";
	}

	@RequestMapping(value="/enregistrer", method=RequestMethod.POST)
	public String enregistrer(Model model, @Valid Utilisateur utilisateur, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "redirect:/inscription";
		}

		String encryptedPassword = passwordEncoder.encode(utilisateur.getPassword());
		utilisateur.setPassword(encryptedPassword);
		//utilisateur.setUtilisateurCreateur(utilisateur.getId());

		utilisateurRepository.save(utilisateur);
		return "confirmInscription";
	}
	
	@GetMapping(value = "/admin/utilisateurs")
	public String pageDesUtilisateurs(Model model,      
			@RequestParam(name="page", defaultValue = "0") int p,
			@RequestParam(name="size", defaultValue = "10") int s) {

		Page<Utilisateur> utilisateur = utilisateurRepository.findAll(PageRequest.of(p, s));
		int[] pages = new int[utilisateur.getTotalPages()];
		
		model.addAttribute("utilisateur", utilisateur.getContent());
		model.addAttribute("pages", pages);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);
		
		return "utilisateurs";
	}

	@GetMapping(value = "/rendreMembre/{id}")
	public String rendreMembre(Model model, Principal principal, @PathVariable("id")int id, int page, int size) {

		Utilisateur utilisateur = utilisateurRepository.findById(id).orElse(null);
		Utilisateur admin = utilisateurRepository.findUtilisateurByPseudo(principal.getName());
		
		utilisateur.setStatut("MEMBRE");
		utilisateur.setDateModif(Timestamp.valueOf(LocalDateTime.now()));
		utilisateur.setUtilisateurModif(admin.getId());
		
		utilisateurRepository.save(utilisateur);
		
		return "redirect:/admin/utilisateurs?page=" + page + "&size=" + size;
	}
	
	@GetMapping(value = "/rendreUser/{id}")
	public String rendreUser(Model model, Principal principal, @PathVariable("id")int id, int page, int size) {

		Utilisateur utilisateur = utilisateurRepository.findById(id).orElse(null);
		Utilisateur admin = utilisateurRepository.findUtilisateurByPseudo(principal.getName());
		
		utilisateur.setStatut("USER");
		utilisateur.setDateModif(Timestamp.valueOf(LocalDateTime.now()));
		utilisateur.setUtilisateurModif(admin.getId());
		
		utilisateurRepository.save(utilisateur);
		
		return "redirect:/admin/utilisateurs?page=" + page + "&size=" + size;
	}

	@GetMapping(value="/supprimerUtilisateur")
	public String supprimerUtilisateur(int id, int page, int size) {
		utilisateurRepository.deleteById(id);
		return "redirect:/admin/utilisateurs?page=" + page + "&size=" + size;
	}
	
	/*@GetMapping(value = "/deconnexion")
	public String deconnexion() {
		return "deconnexion";
	}
	
	@RequestMapping(value = "/connexion", method = RequestMethod.GET)
    public ModelAndView loginGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return new ModelAndView("redirect:/profile/notes");
        }
        return new ModelAndView("connexion");
    }

	@GetMapping(value = "/connexion")
	public String connexion() {
		return "connexion";
	}*/

}
