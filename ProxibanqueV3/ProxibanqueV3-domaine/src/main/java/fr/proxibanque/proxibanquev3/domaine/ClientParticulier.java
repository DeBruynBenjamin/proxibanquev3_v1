package fr.proxibanque.proxibanquev3.domaine;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * @author Loriane & Hattmann
 * La classe permet l'instanciation d'un objet de type ClientParticulier, avec pour attributs notamment un 
 * nom et un prénom.
 * La classe hérite de la classe Client.
 * La valeur de la constante TYPE_CLIENT_PARTICULIER lui est attribuée lors de l'instanciation comme valeur de
 * l'attribut typeClient.
 */

@Entity
@DiscriminatorValue("PARTICULIER")
public class ClientParticulier extends Client {
	
	private String nomCli ; 
	private String prenomCli ;
	@Transient
	public static final int TYPE_CLIENT_PARTICULIER = 9;
	

	
	
	//Constructeurs
	public ClientParticulier(int idCli, int typeClient, String adresse, String codePostal, String ville,
			String telephone, String email, Conseiller conseiller, List<Compte> listeComptes, String nomCli,
			String prenomCli) {
		super(idCli, ClientParticulier.TYPE_CLIENT_PARTICULIER, adresse, codePostal, ville, telephone, email, conseiller, listeComptes);
		this.nomCli = nomCli;
		this.prenomCli = prenomCli;
	}

	public ClientParticulier(int idCli, int typeClient, String adresse, String codePostal, String ville,
			String telephone, String email, String nomCli, String prenomCli) {
		super(idCli, ClientParticulier.TYPE_CLIENT_PARTICULIER, adresse, codePostal, ville, telephone, email);
		this.nomCli = nomCli;
		this.prenomCli = prenomCli;
	}

	
	
	public ClientParticulier(int idCli, int typeClient, String adresse, String codePostal, String ville,
			String telephone, String email, Conseiller conseiller, String nomCli, String prenomCli) {
		super(idCli, ClientParticulier.TYPE_CLIENT_PARTICULIER, adresse, codePostal, ville, telephone, email, conseiller);
		this.nomCli = nomCli;
		this.prenomCli = prenomCli;
	}

	//Getters & Setters
	public String getNomCli() {
		return nomCli;
	}

	public void setNomCli(String nomCli) {
		this.nomCli = nomCli;
	}

	public String getPrenomCli() {
		return prenomCli;
	}

	public void setPrenomCli(String prenomCli) {
		this.prenomCli = prenomCli;
	}
	
	
	
	
	
}
