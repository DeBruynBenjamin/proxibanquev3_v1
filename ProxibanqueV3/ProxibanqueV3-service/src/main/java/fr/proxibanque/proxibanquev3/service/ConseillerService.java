package fr.proxibanque.proxibanquev3.service;

import java.util.ArrayList;
import java.util.List;

import fr.proxibanque.proxibanquev3.dao.ConseillerDao;
import fr.proxibanque.proxibanquev3.domaine.Client;
import fr.proxibanque.proxibanquev3.domaine.Conseiller;

/**
 * @author Loriane & Hattman
 * Cette classe permet la gestion des conseillers.
 * Elle regroupe les méthodes permettant de :
 *  - récupérer un objet Client à l'aide de son login : getConsByLogin
 */
public class ConseillerService {

	/**
	 * Cette méthode permet d'obtenir un conseiller en donnant un login.
	 * Elle permet, en appelant des méthodes d'autres classes, d'obtenir un conseiller
	 * avec une liste de clients, ayant eux-même une liste de comptes.
	 * @param login, un String
	 * @return un objet Conseiller
	 */
	public Conseiller getConsByLogin(String login) {
		
		Conseiller cons = new Conseiller();
		
		ConseillerDao cd = new ConseillerDao();
		ClientService clis = new ClientService();
		
		List<Client> listeClientsConseiller = new ArrayList<>();
		
		cons = cd.getConsByLogin(login);
		
		listeClientsConseiller = clis.getListCliByCons(cons);
		
		if (cons != null) {
			cons.setListeCli(listeClientsConseiller);
		}
		
		return cons;
	}
	
}
