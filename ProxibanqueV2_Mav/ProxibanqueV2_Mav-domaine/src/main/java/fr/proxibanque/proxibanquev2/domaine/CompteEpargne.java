package fr.proxibanque.proxibanquev2.domaine;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author Loriane & Hattmann
 * La classe permet l'instanciation d'un objet de type CompteEpargne qui est notamment caractérisé par un
 * taux de rémunération (par défaut 3%).
 */

@Entity
@Table(name = "COMPTE_EPARGNE")
@NamedQueries({
    @NamedQuery(name = "CompteEpargne.findAll", query = "SELECT c FROM Compte_Epargne c")
    , @NamedQuery(name = "CompteEpargne.findByNumCompte", query = "SELECT c FROM Compte_Epargne c WHERE c.numCompte = :numCompte")})
public class CompteEpargne extends Compte {
	
	
	private float tauxRem;

	//Constructeur
	public CompteEpargne(String numCompte, float solde, String dateOuv, Client client, float tauxRem) {
		super(numCompte, solde, dateOuv, client);
		this.tauxRem = tauxRem;
	}
	
	public CompteEpargne() {
		super();
	}


	//Getters & Setters
	public float getTauxRem() {
		return tauxRem;
	}

	public void setTauxRem(float tauxRem) {
		this.tauxRem = tauxRem;
	} 
	
	
	
	

}
