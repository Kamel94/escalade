package fr.escalade.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.NotNull;

@Entity
public class Site implements Serializable {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 40)
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
	
	@Column
	@NotNull
	private int nombreSecteur;

	public Site() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Site(String nom, String pays, String region, String ville, int nombreSecteur) {
		super();
		this.nom = nom;
		this.pays = pays;
		this.region = region;
		this.ville = ville;
		this.nombreSecteur = nombreSecteur;
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

}
