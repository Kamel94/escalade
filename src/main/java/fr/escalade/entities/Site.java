package fr.escalade.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.NotNull;

@Entity
public class Site implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 40, unique = true)
	@NotNull
	private String nom;

	@Column(length = 30)
	@NotNull
	private String pays;

	@Column(length = 30)
	@NotNull
	private String region;

	@Column(length = 30)
	@NotNull
	private String ville;

	@Column(name = "nombre_secteur")
	@NotNull
	private int nombreSecteur;

	@Column()
	private boolean tag;

	@Column(name = "utilisateur_createur")
	private int utilisateurCreateur;

	@Column(name = "utilisateur_modif")
	private int utilisateurModif;

	@Column(name = "date_creation")
	private Timestamp dateCreation;

	@Column(name = "date_modif")
	private Timestamp dateModif;

	public Site() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Site( String pays, String region, String ville, int nombreSecteur, boolean tag) {
		super();
		this.pays = pays;
		this.region = region;
		this.ville = ville;
		this.nombreSecteur = nombreSecteur;
		this.tag = tag;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public int getNombreSecteur() {
		return nombreSecteur;
	}

	public void setNombreSecteur(int nombreSecteur) {
		this.nombreSecteur = nombreSecteur;
	}

	public boolean getTag() {
		return tag;
	}

	public void setTag(boolean b) {
		this.tag = b;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


}
