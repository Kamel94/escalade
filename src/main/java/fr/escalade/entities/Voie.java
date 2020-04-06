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
	private int secteurNom;
	
	@Column(name = "nombre_longueur")
	private int nombreLongueur;
	
	@Column(name = "utilisateur_createur")
	private int utilisateurCreateur;

	@Column(name = "utilisateur_modif")
	private int utilisateurModif;

	@Column(name = "date_creation")
	private Timestamp dateCreation;

	@Column(name = "date_modif")
	private Timestamp dateModif;
	
	public int getSecteurNom() {
		return secteurNom;
	}

	public void setSecteurNom(int secteurNom) {
		this.secteurNom = secteurNom;
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
	
	public Voie(int secteurNom) {
		super();
		this.secteurNom = secteurNom;
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

	public int getUtilisateurCreateur() {
		return utilisateurCreateur;
	}

	public void setUtilisateurCreateur(int utilisateurCreateur) {
		this.utilisateurCreateur = utilisateurCreateur;
	}

	public int getUtilisateurModif() {
		return utilisateurModif;
	}

	public void setUtilisateurModif(int utilisateurModif) {
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
