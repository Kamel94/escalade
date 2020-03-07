package fr.escalade.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ReservationTopo implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String reponseDemande;

	public ReservationTopo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReservationTopo(String reponseDemande) {
		super();
		this.reponseDemande = reponseDemande;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReponseDemande() {
		return reponseDemande;
	}

	public void setReponseDemande(String reponseDemande) {
		this.reponseDemande = reponseDemande;
	}

}
