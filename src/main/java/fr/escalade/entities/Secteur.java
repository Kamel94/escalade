package fr.escalade.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.sun.istack.NotNull;

@Entity
public class Secteur implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String nom;
	
	/*@ManyToOne(fetch =FetchType.LAZY)
	@JoinColumn(name="site_id", nullable = false, insertable = false, updatable = false)
	private Site sit;*/
	
	@Column(name = "site_id")
	private int site;
	
	@Column
	@NotNull
	private int nombreVoie;
	
	@Column(name = "utilisateur_createur")
	private String utilisateurCreateur;

	@Column(name = "utilisateur_modif")
	private String utilisateurModif;

	@Column(name = "date_creation")
	private Timestamp dateCreation;

	@Column(name = "date_modif")
	private Timestamp dateModif;

	public Secteur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Secteur(int site) {
		super();
		this.site = site;
	}

	public int getSite() {
		return site;
	}

	public void setSite(int site) {
		this.site = site;
	}
	
	public int getNombreVoie() {
		return nombreVoie;
	}

	public void setNombreVoie(int nombreVoie) {
		this.nombreVoie = nombreVoie;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getUtilisateurCreateur() {
		return utilisateurCreateur;
	}

	public void setUtilisateurCreateur(String utilisateurCreateur) {
		this.utilisateurCreateur = utilisateurCreateur;
	}

	public String getUtilisateurModif() {
		return utilisateurModif;
	}

	public void setUtilisateurModif(String utilisateurModif) {
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
