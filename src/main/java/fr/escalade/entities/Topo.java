package fr.escalade.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.sun.istack.NotNull;

@Entity
public class Topo implements Serializable {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 70)
	@NotNull
	@Size(min = 1, max = 70)
	private String nom;
	
	/*@ManyToOne
	@JoinColumn(name="utilisateur_id")*/
	@Column( name = "utilisateur_createur", insertable = false, updatable = false)
	private Integer proprietaire;
	
	@Column(length = 500)
	@Size(min = 1, max = 500)
	private String description;
	
	@Column(length = 100)
	@Size(min = 1, max = 100)
	private String lieu;
	
	@Column(name = "date_parution")
	/*@Size(min = 1, max = 70)*/
	@DateTimeFormat(iso = ISO.DATE, pattern = "yyyy-MM-dd", style = "dd-MM-yyyy")
	private Date parution;
	
	private Integer emprunteur;
	
	@Column(name = "utilisateur_createur")
	private int utilisateurCreateur;

	@Column(name = "utilisateur_modif")
	private int utilisateurModif;

	@Column(name = "date_creation")
	private Timestamp dateCreation;

	@Column(name = "date_modif")
	private Timestamp dateModif;
	
	private Integer demandeur;
	
	@Column(name= "site_id")
	private Integer site;

	public Integer getEmprunteur() {
		return emprunteur;
	}

	public void setEmprunteur(Integer emprunteur) {
		this.emprunteur = emprunteur;
	}

	@Column(length = 70)
	@Size(min = 1, max = 70)
	private String disponibilite;

	public Topo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Topo(Integer proprietaire, String description, String lieu, Date parution, Integer emprunteur, String disponibilite) {
		super();
		this.proprietaire = proprietaire;
		this.description = description;
		this.lieu = lieu;
		this.parution = parution;
		this.emprunteur = emprunteur;
		this.disponibilite = disponibilite;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public Date getParution() {
		return parution;
	}

	public void setParution(Date parution) {
		this.parution = parution;
	}

	public String getDisponibilite() {
		return disponibilite;
	}

	public void setDisponibilite(String disponibilite) {
		this.disponibilite = disponibilite;
	}
	
	public Integer getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(Integer proprietaire) {
		this.proprietaire = proprietaire;
	}

	public Integer getDemandeur() {
		return demandeur;
	}

	public void setDemandeur(Integer demandeur) {
		this.demandeur = demandeur;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSite() {
		return site;
	}

	public void setSite(Integer site) {
		this.site = site;
	}

}
