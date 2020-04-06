package fr.escalade.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.NotNull;


public class InfoSite implements Serializable {
	
	@Id
	@Column(name = "site_nom")
	private String siteNom;
	
	Site site;
	
	@Column(name = "utilisateur_email")
	private String utilisateur;

	@Column(length = 1000)
	private String description;
	
	@Column(length = 500)
	private String commentaire;
	
	@Column(length = 35)
	private String tag;

	public InfoSite() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InfoSite(String description, String commentaire, String tag) {
		super();
		this.description = description;
		this.commentaire = commentaire;
		this.tag = tag;
	}
	
	public String getSiteNom() {
		return siteNom;
	}

	public void setSiteNom(String siteNom) {
		this.siteNom = siteNom;
	}

	public String getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateurId(String utilisateur) {
		this.utilisateur = utilisateur;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}
