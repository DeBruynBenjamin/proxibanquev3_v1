package fr.proxibanque.proxibanquev2.domaine;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;




/**
 * @author Loriane & Hattmann
 * La classe Client est la classe-mère des classes ClientEntreprise et ClientService.
 * Un objet Client a pour attributs une adresse, code postal, ville, un numéro de téléphone, un email
 * et est relié à une liste de comptes (CompteCourant, CompteEpargne), et un Conseiller.
 * Un attribut typeClient permet quant à lui de définir par la suite s'il s'agira d'un client entreprise
 * ou d'un client particulier.
 */

@Entity
@Table(name = "CLIENT")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_CLIENT")
@NamedQueries({
    @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c")})
public abstract class Client {
	
	// Attributes
		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
		private int idCli;
		@Transient
		private int typeClient;
		private String adresse;
		private String codePostal;
		private String ville;
		private String telephone;
		private String email;
		@JoinColumn(name = "LOGINCONS", referencedColumnName = "LOGINCONS")
	    @ManyToOne(fetch = FetchType.EAGER)
		private Conseiller conseiller;
		@Transient
		private List<Compte> listeComptes;
		@OneToOne(mappedBy = "client", fetch = FetchType.EAGER)
		private CompteEpargne compteEpargne;
		@OneToOne(mappedBy = "client", fetch = FetchType.EAGER)
	    private CompteCourant compteCourant;
		
		//Constructeurs

		public Client() {
			super();
			
			this.listeComptes = new ArrayList<>();
			this.listeComptes.add(this.compteCourant);
			this.listeComptes.add(this.compteEpargne);
		}
		
		public Client(int idCli, int typeClient, String adresse, String codePostal, String ville, String telephone, String email, 
				Conseiller conseiller, List<Compte> listeComptes) {
			super();
			this.idCli = idCli;
			this.typeClient=typeClient;
			this.adresse = adresse;
			this.codePostal = codePostal;
			this.ville = ville;
			this.telephone = telephone;
			this.email = email;
			this.conseiller = conseiller;
			this.listeComptes = listeComptes;
		}

		public Client(int idCli, int typeClient, String adresse, String codePostal, String ville, String telephone, String email, 
				Conseiller conseiller) {
			super();
			this.idCli = idCli;
			this.typeClient=typeClient;
			this.adresse = adresse;
			this.codePostal = codePostal;
			this.ville = ville;
			this.telephone = telephone;
			this.email = email;
			this.conseiller = conseiller;
		}

		public Client(int idCli, int typeClient, String adresse, String codePostal, String ville, String telephone, String email) {
			super();
			this.idCli = idCli;
			this.typeClient=typeClient;
			this.adresse = adresse;
			this.codePostal = codePostal;
			this.ville = ville;
			this.telephone = telephone;
			this.email = email;
		}

		//Getters & Setters
		public int getIdCli() {
			return idCli;
		}

		public int getTypeClient() {
			return typeClient;
		}

		public void setTypeClient(int typeClient) {
			this.typeClient = typeClient;
		}

		public String getAdresse() {
			return adresse;
		}

		public void setAdresse(String adresse) {
			this.adresse = adresse;
		}

		public String getCodePostal() {
			return codePostal;
		}

		public void setCodePostal(String codePostal) {
			this.codePostal = codePostal;
		}

		public String getVille() {
			return ville;
		}

		public void setVille(String ville) {
			this.ville = ville;
		}

		public String getTelephone() {
			return telephone;
		}

		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public Conseiller getConseiller() {
			return conseiller;
		}

		public void setConseiller(Conseiller conseiller) {
			this.conseiller = conseiller;
		}

		public List<Compte> getListeComptes() {
			return listeComptes;
		}

		
		
		public CompteEpargne getCompteEpargne() {
			
			for (Compte compte : this.listeComptes) {
				
				if(compte instanceof CompteEpargne) {
					return (CompteEpargne) compte;
				}
			}
			
			return compteEpargne;
		}

		public CompteCourant getCompteCourant() {
			
			for (Compte compte : this.listeComptes) {
				
				if(compte instanceof CompteEpargne) {
					return (CompteCourant) compte;
				}
			}
			return compteCourant;
		}

		public void setListeComptes(List<Compte> listeComptes) {
			this.listeComptes = listeComptes;
		}
		
		
		
		
		
		
	
}
