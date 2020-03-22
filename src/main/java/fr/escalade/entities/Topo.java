package fr.escalade.entities;

import java.io.Serializable;
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
	
	@Id
	@Column(length = 70)
	@NotNull
	@Size(min = 1, max = 70)
	private String nom;
	
	/*@ManyToOne
	@JoinColumn(name="utilisateur_id")*/
	@Column(length = 30, name = "utilisateur_identifiant")
	private String proprietaire;
	
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
	
	private Utilisateur emprunteur;
	
	private Utilisateur utilisateur;
	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Utilisateur getEmprunteur() {
		return emprunteur;
	}

	public void setEmprunteur(Utilisateur emprunteur) {
		this.emprunteur = emprunteur;
	}

	@Column(length = 70)
	@Size(min = 1, max = 70)
	private String disponibilite;

	public Topo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Topo(String proprietaire, String description, String lieu, Date parution, Utilisateur emprunteur, String disponibilite) {
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
	
	public String getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(String proprietaire) {
		this.proprietaire = proprietaire;
	}

}
