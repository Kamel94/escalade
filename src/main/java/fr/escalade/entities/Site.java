package fr.escalade.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;

import org.springframework.ui.Model;

import com.sun.istack.NotNull;

import fr.escalade.dao.SecteurRepository;

@Entity
public class Site implements Serializable {
	
	
	
	/*@OneToMany(mappedBy="site", fetch=FetchType.LAZY)
	private Collection<Secteur> secteurs;

	public Collection<Secteur> getTopos() {
		return secteurs;
	}

	public void setTopos(Collection<Secteur> secteurs) {
		this.secteurs = secteurs;
	}*/
	
	@Id
	@Column(length = 40)
	@NotNull
	private String nom;
	
	@Column(length = 30)
	@NotNull
	private String pays;
	
	@Column(length = 30)
	@NotNull
	private String region;
	
	@Column(length = 30)
	@NotNull
	private String ville;
	
	@Column(name = "nombre_secteur")
	@NotNull
	private int nombreSecteur;

	@Column(length = 45)
	private String tag;
	
	@Column(name = "utilisateur_createur")
	private String utilisateurCreateur;

	@Column(name = "utilisateur_modif")
	private String utilisateurModif;

	@Column(name = "date_creation")
	private Timestamp dateCreation;

	@Column(name = "date_modif")
	private Timestamp dateModif;

	public Site() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Site( String pays, String region, String ville, int nombreSecteur, String tag) {
		super();
		this.pays = pays;
		this.region = region;
		this.ville = ville;
		this.nombreSecteur = nombreSecteur;
		this.tag = tag;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public int getNombreSecteur() {
		return nombreSecteur;
	}

	public void setNombreSecteur(int nombreSecteur) {
		this.nombreSecteur = nombreSecteur;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getUtilisateurCreateur() {
		return utilisateurCreateur;
	}

	public void setUtilisateurCreateur(String utilisateurCreateur) {
		this.utilisateurCreateur = utilisateurCreateur;
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
