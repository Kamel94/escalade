package fr.escalade.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReservationTopo implements Serializable {
	
	@Id
	@Column(name = "topo_nom")
	private String topoNom;
	
	@Column(name = "utilisateur_email")
	private String Proprietaire;
	
	private String reponseDemande;

	public ReservationTopo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReservationTopo(String reponseDemande) {
		super();
		this.reponseDemande = reponseDemande;
	}

	public String getReponseDemande() {
		return reponseDemande;
	}

	public void setReponseDemande(String reponseDemande) {
		this.reponseDemande = reponseDemande;
	}

	public String getTopoNom() {
		return topoNom;
	}

	public void setTopoNom(String topoNom) {
		this.topoNom = topoNom;
	}

	public String getProprietaire() {
		return Proprietaire;
	}

	public void setProprietaire(String proprietaire) {
		Proprietaire = proprietaire;
	}

}
