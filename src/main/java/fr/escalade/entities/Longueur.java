package fr.escalade.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Longueur implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
