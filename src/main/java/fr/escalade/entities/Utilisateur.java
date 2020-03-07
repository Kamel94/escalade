package fr.escalade.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.NotNull;

@Entity
public class Utilisateur implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 30)
	private String nom;

	@Column(length = 30)
	private String prenom;

	@Column(length = 15)
	private String pseudo;

	@Column(length = 50)
	private String email;

	@Column(length = 50)
	private String password;
	
	@Column(length = 5)
	private String statut;

	private String dateAdhesion;

	@Column(length = 20)
	private String niveau;
	
	@Column
	@NotNull
	private int nombreTopo;

	@Column(length = 15)
	private String telephone;

	public Utilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Utilisateur(String nom, String prenom, String pseudo, String email, String password, String statut,
			String dateAdhesion, String niveau, int nombreTopo, String telephone) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.pseudo = pseudo;
		this.email = email;
		this.password = password;
		this.statut = statut;
		this.dateAdhesion = dateAdhesion;
		this.niveau = niveau;
		this.nombreTopo = nombreTopo;
		this.telephone = telephone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public String getDateAdhesion() {
		return dateAdhesion;
	}

	public void setDateAdhesion(String dateAdhesion) {
		this.dateAdhesion = dateAdhesion;
	}

	public String getNiveau() {
		return niveau;
	}

	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}

	public int getNombreTopo() {
		return nombreTopo;
	}

	public void setNombreTopo(int nombreTopo) {
		this.nombreTopo = nombreTopo;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}
