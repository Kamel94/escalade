package fr.escalade.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.escalade.dao.CommentaireRepository;
import fr.escalade.dao.TopoRepository;
import fr.escalade.dao.UtilisateurRepository;
import fr.escalade.entities.Commentaire;
import fr.escalade.entities.Topo;
import fr.escalade.entities.Utilisateur;

@Controller
public class CommentaireController {

    @Autowired
    TopoRepository topoRepository;

    @Autowired
    CommentaireRepository commentaireRepository;
    
    @Autowired
    UtilisateurRepository utilisateurRepository;
    
    @RequestMapping(value="/user/enregistrerCom", method=RequestMethod.POST)
	public String enregistrerCom(Model model, @Valid Commentaire commentaire, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "ajoutCom";
		}
		commentaireRepository.save(commentaire);
		return "confirmationCom";
	}

}
