package fr.escalade.entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Commentaire {
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "site_nom", length = 30)
	@Size(min = 1, max = 30)
    private String site;
	
	@Column(length = 500)
	@Size(min = 1, max = 500)
	private String comment;
    
    private String auteur;
    
    private Timestamp date;
    
    public Commentaire() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Commentaire(String site, String comment, String auteur, Timestamp date) {
		super();
		this.site = site;
		this.comment = comment;
		this.auteur = auteur;
		this.date = date;
	}
	public Commentaire(String site) {
		super();
		this.site = site;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

}