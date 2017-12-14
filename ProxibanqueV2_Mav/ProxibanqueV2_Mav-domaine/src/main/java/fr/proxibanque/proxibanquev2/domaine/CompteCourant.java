package fr.proxibanque.proxibanquev2.domaine;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author Loriane & Hattmann
 * La classe permet l'instanciation d'un objet de type CompteCourant qui est notamment caractérisé par une
 * autorisation de découvert (par défaut 500€).
 */

@Entity
@Table(name = "COMPTE_COURANT")
@NamedQueries({
    @NamedQuery(name = "CompteCourant.findAll", query = "SELECT c FROM Compte_Courant c")
    , @NamedQuery(name = "CompteCourant.findByNumCompte", query = "SELECT c FROM Compte_Courant c WHERE c.numCompte = :numCompte")})
public class CompteCourant extends Compte {

	private float decouvert;

	//Constructeur
	public CompteCourant(String numCompte, float solde, String dateOuv, Client client, float decouvert) {
		super(numCompte, solde, dateOuv, client);
		this.decouvert = decouvert;
	}

	public CompteCourant() {
		super();
	}

	//Getters & Setters
	public float getDecouvert() {
		return decouvert;
	}

	public void setDecouvert(float decouvert) {
		this.decouvert = decouvert;
	} 
	
	
	

}
