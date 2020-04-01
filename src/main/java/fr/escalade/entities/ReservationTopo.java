package fr.escalade.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ReservationTopo implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "topo_nom")
	private String topoNom;
	
	@Column(name = "utilisateur_createur")
	private String demandeur;
	
	@Column(name = "reponse_demande")
	private String reponseDemande;

	@Column(name = "utilisateur_modif")
	private String utilisateurModif;

	@Column(name = "date_creation")
	private Timestamp dateCreation;

	@Column(name = "date_modif")
	private Timestamp dateModif;

	public ReservationTopo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReservationTopo(String topoNom, String reponseDemande, String demandeur, String utilisateurModif, Timestamp dateCreation) {
		super();
		this.topoNom = topoNom;
		this.reponseDemande = reponseDemande;
		this.demandeur = demandeur;
		this.utilisateurModif = utilisateurModif;
		this.dateCreation = dateCreation;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDemandeur() {
		return demandeur;
	}

	public void setDemandeur(String demandeur) {
		this.demandeur = demandeur;
	}

	public String getUtilisateurModif() {
		return utilisateurModif;
	}

	public void setUtilisateurModif(String utilisateurModif) {
		this.utilisateurModif = utilisateurModif;
	}

	public Timestamp getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Timestamp dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Timestamp getDateModif() {
		return dateModif;
	}

	public void setDateModif(Timestamp dateModif) {
		this.dateModif = dateModif;
	}

}
