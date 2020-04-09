package fr.escalade.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Commentaire implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "site_id")
	private Integer site;

	@Column(length = 500, name = "commentaire")
	@Size(min = 1, max = 500)
	private String comment;

	@ManyToOne
	@JoinColumn(name = "utilisateur_createur")
	private Utilisateur auteur;

	@Column(name = "date_creation")
	private Timestamp date;

	@Column(name = "utilisateur_modif")
	private Integer utilisateurModif;

	@Column(name = "date_modif")
	private Timestamp dateModif;

	public Commentaire() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Commentaire(Integer site, String comment, Utilisateur auteur, Timestamp date) {
		super();
		this.site = site;
		this.comment = comment;
		this.auteur = auteur;
		this.date = date;
	}

	public Commentaire(Integer site) {
		super();
		this.site = site;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Integer getSite() {
		return site;
	}

	public void setSite(Integer site) {
		this.site = site;
	}

	public int getUtilisateurModif() {
		return utilisateurModif;
	}

	public void setUtilisateurModif(int utilisateurModif) {
		this.utilisateurModif = utilisateurModif;
	}

	public Timestamp getDateModif() {
		return dateModif;
	}

	public void setDateModif(Timestamp dateModif) {
		this.dateModif = dateModif;
	}

	public Utilisateur getAuteur() {
		return auteur;
	}

	public void setAuteur(Utilisateur auteur) {
		this.auteur = auteur;
	}

	public void setUtilisateurModif(Integer utilisateurModif) {
		this.utilisateurModif = utilisateurModif;
	}

}