package fr.escalade.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Longueur implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
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

}
