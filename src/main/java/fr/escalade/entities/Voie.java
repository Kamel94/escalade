package fr.escalade.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Voie implements Serializable {
	
	@Id
	@Column(name = "numero_voie")
	@NotNull
	private Integer id;
	
	@Column(name = "secteur_nom")
	private String secteurNom;
	
	@Column(name = "nombre_longueur")
	private int nombreLongueur;
	
	@Column(name = "nombre_point")
	private int nombrePoint;
	
	public String getSecteurNom() {
		return secteurNom;
	}

	public void setSecteurNom(String secteurNom) {
		this.secteurNom = secteurNom;
	}

	@Column(length = 2)
	private String cotation;

	public Voie() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Voie(int nombreLongueur, int nombrePoint, String cotation) {
		super();
		this.nombreLongueur = nombreLongueur;
		this.nombrePoint = nombrePoint;
		this.cotation = cotation;
	}
	
	public Voie(String secteurNom) {
		super();
		this.secteurNom = secteurNom;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getNombreLongueur() {
		return nombreLongueur;
	}

	public void setNombreLongueur(int nombreLongueur) {
		this.nombreLongueur = nombreLongueur;
	}

	public int getNombrePoint() {
		return nombrePoint;
	}

	public void setNombrePoint(int nombrePoint) {
		this.nombrePoint = nombrePoint;
	}

	public String getCotation() {
		return cotation;
	}

	public void setCotation(String cotation) {
		this.cotation = cotation;
	}

}
