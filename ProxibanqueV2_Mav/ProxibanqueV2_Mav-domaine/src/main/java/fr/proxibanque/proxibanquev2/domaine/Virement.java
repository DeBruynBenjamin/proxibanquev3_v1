package fr.proxibanque.proxibanquev2.domaine;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * @author Loriane & Hattmann
 * Cette classe permettra l'instanciation d'un objet de type Virement, caract�ris� par ses attributs :
 *  - un conseiller (celui qui a r�alis� le virement)
 *  - un compte �metteur
 *  - un compte r�cepteur
 *  - une date de virement
 *  - un libell�
 *  
 */

@Entity
@NamedQueries({
    @NamedQuery(name = "Virement.findAll", query = "SELECT v FROM Virement v")
    , @NamedQuery(name = "Virement.findByNumvir", query = "SELECT v FROM Virement v WHERE v.numVir = :numVir")
    , @NamedQuery(name = "Virement.findByCompteemetteur", query = "SELECT v FROM Virement v WHERE v.compteEmetteur = :compteEmetteur")
    , @NamedQuery(name = "Virement.findByComptecible", query = "SELECT v FROM Virement v WHERE v.compteCible = :compteCible")
    , @NamedQuery(name = "Virement.findByDatevir", query = "SELECT v FROM Virement v WHERE v.dateVir = :dateVir")})
public class Virement {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int numVir;
	@JoinColumn(name = "LOGINCONS", referencedColumnName = "LOGINCONS")
    @ManyToOne(fetch = FetchType.EAGER)
	private Conseiller conseiller;
	@JoinColumn(name = "COMPTEMETTEUR", referencedColumnName = "NUMCOMPTE")
    @ManyToOne(fetch = FetchType.LAZY)
	private Compte compteEmetteur;
	@JoinColumn(name = "COMPTECIBLE", referencedColumnName = "NUMCOMPTE")
    @ManyToOne(fetch = FetchType.LAZY)
	private Compte compteCible;
	private float montantVirement;
	private String dateVirement;
	private String libelle;
	
	//Constructeur

	public Virement() {
		super();
	}
	
	public Virement(Conseiller conseiller, Compte compteEmetteur, Compte compteCible, float montantVirement,
			String dateVirement, String libelle) {
		super();
		this.conseiller = conseiller;
		this.compteEmetteur = compteEmetteur;
		this.compteCible = compteCible;
		this.montantVirement = montantVirement;
		this.dateVirement = dateVirement;
		this.libelle = libelle;
	}

	
	public int getNumVir() {
		return numVir;
	}

	public void setNumVir(int numVir) {
		this.numVir = numVir;
	}

	public Conseiller getConseiller() {
		return conseiller;
	}

	public void setConseiller(Conseiller conseiller) {
		this.conseiller = conseiller;
	}

	public Compte getCompteEmetteur() {
		return compteEmetteur;
	}

	public void setCompteEmetteur(Compte compteEmetteur) {
		this.compteEmetteur = compteEmetteur;
	}

	public Compte getCompteCible() {
		return compteCible;
	}

	public void setComptecible(Compte compteCible) {
		this.compteCible = compteCible;
	}

	public float getMontantVirement() {
		return montantVirement;
	}

	public void setMontantVirement(float montantVirement) {
		this.montantVirement = montantVirement;
	}

	public String getDateVirement() {
		return dateVirement;
	}

	public void setDateVirement(String dateVirement) {
		this.dateVirement = dateVirement;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	
}
