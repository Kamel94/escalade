package fr.escalade.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.NotNull;

@Entity
public class Secteur implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	@NotNull
	private int nombreVoie;

	public Secteur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Secteur(int nombreVoie) {
		super();
		this.nombreVoie = nombreVoie;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getNombreVoie() {
		return nombreVoie;
	}

	public void setNombreVoie(int nombreVoie) {
		this.nombreVoie = nombreVoie;
	}

}
