package fr.escalade.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Longueur implements Serializable {
	
	@Id
	private Integer niveau;
	
	@Column(name = "voie_numero")
	private int numeroVoie;
	
	@Column(name = "nombre_relais")
	private int nombreRelais;

	public Longueur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Longueur(int nombreRelais) {
		super();
		this.nombreRelais = nombreRelais;
	}

	public Integer getNiveau() {
		return niveau;
	}

	public void setNiveau(Integer niveau) {
		this.niveau = niveau;
	}

	public int getNombreRelais() {
		return nombreRelais;
	}

	public void setNombreRelais(int nombreRelais) {
		this.nombreRelais = nombreRelais;
	}

	public int getNumeroVoie() {
		return numeroVoie;
	}

	public void setNumeroVoie(int numeroVoie) {
		this.numeroVoie = numeroVoie;
	}

}
