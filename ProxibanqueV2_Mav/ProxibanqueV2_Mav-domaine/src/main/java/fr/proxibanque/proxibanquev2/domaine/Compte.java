package fr.proxibanque.proxibanquev2.domaine;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;



/**
 * @author Loriane & Hattmann
 * La classe Compte est la classe-mère des classes CompteCourant et CompteEpargne.
 * Un objet Compte a pour attributs un numéro de compte, un solde, une date d'ouverture, et est relié à un Client.
 */

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Compte {
	
	@Id
	private String numCompte; 
	private float solde; 
	private String dateOuv;
	@JoinColumn(name = "IDCLI", referencedColumnName = "IDCLI")
    @OneToOne(fetch = FetchType.EAGER)
	private Client client;
	//@OneToMany(mappedBy="compteEmetteur")
    //private List<Virement> listeVir;
	
	//Constructeur
	public Compte(String numCompte, float solde, String dateOuv, Client client) {
		super();
		this.numCompte = numCompte;
		this.solde = solde;
		this.dateOuv = dateOuv;
		this.client = client;
	}
	
	public Compte() {
		super();
	}



	//Getters & Setters
	public String getNumCompte() {
		return numCompte;
	}

	public void setNumCompte(String numCompte) {
		this.numCompte = numCompte;
	}

	public float getSolde() {
		return solde;
	}

	public void setSolde(float solde) {
		this.solde = solde;
	}

	public String getDateOuv() {
		return dateOuv;
	}

	public void setDateOuv(String dateOuv) {
		this.dateOuv = dateOuv;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	
	
	
	
	
	
}
