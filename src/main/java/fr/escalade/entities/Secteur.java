package fr.escalade.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.sun.istack.NotNull;

@Entity
public class Secteur implements Serializable {
	
	@Id
	private String nom;
	
	/*@OneToMany
	@JoinColumn(name="site_id")*/
	@Column(name = "site_nom")
	private String site;


	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}
	
	@Column
	@NotNull
	private int nombreVoie;

	public Secteur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Secteur(String site) {
		super();
		this.site = site;
	}

	public int getNombreVoie() {
		return nombreVoie;
	}

	public void setNombreVoie(int nombreVoie) {
		this.nombreVoie = nombreVoie;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
