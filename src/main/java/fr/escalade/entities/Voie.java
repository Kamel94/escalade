package fr.escalade.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Voie implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private int nombreLongueur;
	
	private int nombrePoint;
	
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
