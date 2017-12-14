package fr.proxibanque.proxibanquev2.service;

import java.util.List;
import java.util.Random;

import fr.proxibanque.proxibanquev2.dao.CompteDao;
import fr.proxibanque.proxibanquev2.domaine.Client;
import fr.proxibanque.proxibanquev2.domaine.ClientParticulier;
import fr.proxibanque.proxibanquev2.domaine.Compte;
import fr.proxibanque.proxibanquev2.domaine.CompteCourant;
import fr.proxibanque.proxibanquev2.domaine.CompteEpargne;

/**
 * @author Loriane & Hattmann
 * La classe CompteService gère la gestion des comptes.
 * Elle regroupe les méthodes permettant de :
 *  - récupérer la liste des Comptes associés à un client : getComptesbyClient
 *  - générer un numéro de compte unique geneNumCompte
 *
 */
public class CompteService {

	
	/**
	 * Cette méthode permet d'obtenir une liste de comptes liés à un client, en appelant la méthode
	 * getComptesByIdcli de la classe CompteDao.
	 * @param cli, un objet Client
	 * @return une liste d'objet Compte.
	 */
	public List<Compte> getComptesbyClient(Client cli) {
		
		CompteDao cd = new CompteDao();
		List<Compte> listeComptes = cd.getComptesByIdcli(cli);
		
		return listeComptes;
		
	}
	
	
	/**
	 * Cette méthode permet d'obtenir un Compte à partir d'un numéro de compte, en appelant
	 * les méthodes de la Dao associée, CompteDao.
	 * @param numCompte
	 * @return un objet Compte
	 */
	public Compte selectCompteByNumCompte (String numCompte){ 
		
		CompteDao cD = new CompteDao();
		CompteCourant cc = cD.selectCompteCourantByNumCompte(numCompte);
		 	
	if(cc.getClient() != null) {
		if (cc.getNumCompte().equals(numCompte)) {
		return cc;
		}
		return cc;
	}
	else {
		CompteEpargne ce = cD.selectCompteEpargneByNumCompte(numCompte);
		return ce;
	}
	
	
}
	
	/**
	 * Cette méthode permet d'obtenir la liste de tous les comptes courant de Proxibanque, en appelant 
	 * la méthode selectAllComptesCourants de la classe CompteDao.
	 * @param cli, un objet Client
	 * @return une liste d'objet CompteCourant.
	 */
	public List<CompteCourant> getAllComptesCourants() {
		
		CompteDao cd = new CompteDao();
		List<CompteCourant> listeComptesCourants = cd.selectAllComptesCourants();
		
		return listeComptesCourants;
		
	}
	
	/**
	 * Cette méthode permet d'obtenir la liste de tous les comptes épargne de Proxibanque, en appelant 
	 * la méthode selectAllCOmptesEpargnes de la classe CompteDao.
	 * @param cli, un objet Client
	 * @return une liste d'objet CompteEpargne.
	 */
	public List<CompteEpargne> getAllComptesEpargnes() {
		
		CompteDao cd = new CompteDao();
		List<CompteEpargne> listeComptesEpargne = cd.selectAllComptesEpargnes();
		
		return listeComptesEpargne;
		
	}
	
	
	/**
	 * Cette méthode permet de générer un numéro de compte unique en fonction des informations du compte
	 * donné en paramètre d'entrée.
	 * @param compte, un objet Compte
	 */
	public void geneNumCompte (Compte compte) {
		
		Random rand = new Random();
		int[] suite = new int[4];
		
		StringBuffer sB = new StringBuffer();
		
		//Pour client entreprise _ 1 pour compte courant
		//Pour client particulier _ 2 pour compte epargne
				//sB.append("99082");
		
		if (compte.getClient().getClass().equals(ClientParticulier.class)) {
			sB.append("9");
			
		}else { sB.append("7");
		}
		
		if (compte.getClass().equals(CompteCourant.class)) {
			sB.append("1");
			
		}else { sB.append("2");
		}
		
		for (int i = 0; i < 4; i++) {
			suite[i] = rand.nextInt(10); // Générateur aléatoire entre 0 et 9 - On obtient un tableau a 4
			System.out.println(suite[i]);// chiffres
			sB.append(suite[i]);
		}
		
		compte.setNumCompte(sB.toString());
	
	}
	
}