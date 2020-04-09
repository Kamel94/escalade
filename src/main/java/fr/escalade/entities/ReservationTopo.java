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

	@Column(name = "topo_id")
	private Integer topoId;

	@Column(name = "utilisateur_createur")
	private Integer demandeur;

	@Column(name = "reponse_demande")
	private String reponseDemande;

	@Column(name = "utilisateur_modif")
	private Integer utilisateurModif;

	@Column(name = "date_creation")
	private Timestamp dateCreation;

	@Column(name = "date_modif")
	private Timestamp dateModif;

	public ReservationTopo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReservationTopo(Integer topoId, String reponseDemande, Integer demandeur, Integer utilisateurModif, Timestamp dateCreation, Timestamp dateModif) {
		super();
		this.topoId = topoId;
		this.reponseDemande = reponseDemande;
		this.demandeur = demandeur;
		this.utilisateurModif = utilisateurModif;
		this.dateCreation = dateCreation;
		this.dateModif = dateModif;
	}

	public String getReponseDemande() {
		return reponseDemande;
	}

	public void setReponseDemande(String reponseDemande) {
		this.reponseDemande = reponseDemande;
	}

	public Integer getTopoId() {
		return topoId;
	}

	public void setTopoId(Integer topoId) {
		this.topoId = topoId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDemandeur() {
		return demandeur;
	}

	public void setDemandeur(Integer demandeur) {
		this.demandeur = demandeur;
	}

	public Integer getUtilisateurModif() {
		return utilisateurModif;
	}

	public void setUtilisateurModif(Integer utilisateurModif) {
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
