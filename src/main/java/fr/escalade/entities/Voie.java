package fr.escalade.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Voie implements Serializable {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "numero_voie")
	private int numeroVoie;
	
	@Column(name = "secteur_id")
	private Integer secteurId;
	
	@Column(name = "nombre_longueur")
	private int nombreLongueur;
	
	@Column(name = "utilisateur_createur")
	private Integer utilisateurCreateur;

	@Column(name = "utilisateur_modif")
	private Integer utilisateurModif;

	@Column(name = "date_creation")
	private Timestamp dateCreation;

	@Column(name = "date_modif")
	private Timestamp dateModif;
	
	public Integer getSecteurId() {
		return secteurId;
	}

	public void setSecteurId(Integer secteurId) {
		this.secteurId = secteurId;
	}
	
	@Column(length = 2 )
	@Size(min = 1, max = 2)
	private String cotation;

	public Voie() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Voie(int nombreLongueur, String cotation) {
		super();
		this.nombreLongueur = nombreLongueur;
		this.cotation = cotation;
	}
	
	public Voie(Integer secteurId) {
		super();
		this.secteurId = secteurId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getNumeroVoie() {
		return numeroVoie;
	}

	public void setNumeroVoie(int numeroVoie) {
		this.numeroVoie = numeroVoie;
	}

	public int getNombreLongueur() {
		return nombreLongueur;
	}

	public void setNombreLongueur(int nombreLongueur) {
		this.nombreLongueur = nombreLongueur;
	}

	public String getCotation() {
		return cotation;
	}

	public void setCotation(String cotation) {
		this.cotation = cotation;
	}

	public Integer getUtilisateurCreateur() {
		return utilisateurCreateur;
	}

	public void setUtilisateurCreateur(Integer utilisateurCreateur) {
		this.utilisateurCreateur = utilisateurCreateur;
	}

	public Integer getUtilisateurModif() {
		return utilisateurModif;
	}

	public void setUtilisateurModif(Integer utilisateurModif) {
		this.utilisateurModif = utilisateurModif;
	}

	public Timestamp getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Timestamp dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Timestamp getDateModif() {
		return dateModif;
	}

	public void setDateModif(Timestamp dateModif) {
		this.dateModif = dateModif;
	}

}
