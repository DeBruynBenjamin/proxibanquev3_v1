package fr.proxibanque.proxibanquev2.domaine;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * @author Loriane & Hattmann
 * Cette classe permettra l'instanciation d'un objet de type Conseiller, caractérisé par ses attributs :
 *  - un login, loginCons
 *  - un mot de passe, password
 *  - un nom, nomCons
 *  - un prenom, prenomCons
 *  - listeCli, la liste de tous les clients sous sa responsabilité.
 *  
 */

@Entity
@NamedQueries({
@NamedQuery(name = "Conseiller.findAll", query = "SELECT c FROM Conseiller c")
, @NamedQuery(name = "Conseiller.findByLogincons", query = "SELECT c FROM Conseiller c WHERE c.loginCons = :loginCons")
, @NamedQuery(name = "Conseiller.findByPassword", query = "SELECT c FROM Conseiller c WHERE c.password = :password")})
public class Conseiller {

	@Id
	private String loginCons;
	private String password;
	private String nomCons;
	private String prenomCons;
	@OneToMany(mappedBy = "conseiller", fetch = FetchType.LAZY)
	private List<Client> listeCli;
	
	//Constructeurs
	public Conseiller(String loginCons, String password, String nomCons, String prenomCons, List<Client> listeCli) {
		super();
		this.loginCons = loginCons;
		this.password = password;
		this.nomCons = nomCons;
		this.prenomCons = prenomCons;
		this.listeCli = listeCli;
	}
	
	


	public Conseiller(String loginCons, String password, String nomCons, String prenomCons) {
		super();
		this.loginCons = loginCons;
		this.password = password;
		this.nomCons = nomCons;
		this.prenomCons = prenomCons;
	}




	public Conseiller(String loginCons, String password) {
		super();
		this.loginCons = loginCons;
		this.password = password;
	}


	public Conseiller() {
		super();
	}


	//Getters & Setters
	public String getLoginCons() {
		return loginCons;
	}


	public void setLoginCons(String loginCons) {
		this.loginCons = loginCons;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getNomCons() {
		return nomCons;
	}


	public void setNomCons(String nomCons) {
		this.nomCons = nomCons;
	}


	public String getPrenomCons() {
		return prenomCons;
	}


	public void setPrenomCons(String prenomCons) {
		this.prenomCons = prenomCons;
	}


	public List<Client> getListeCli() {
		return listeCli;
	}


	public void setListeCli(List<Client> listeCli) {
		this.listeCli = listeCli;
	}	
	
	
	
	
}
