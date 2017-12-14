package fr.proxibanque.proxibanquev2.service;

import java.util.List;

import fr.proxibanque.proxibanquev2.dao.ClientDao;
import fr.proxibanque.proxibanquev2.domaine.Client;
import fr.proxibanque.proxibanquev2.domaine.Compte;
import fr.proxibanque.proxibanquev2.domaine.Conseiller;

/**
 * @author Loriane & Hattmann
 * La classe ClientService gère la gestion des clients.
 * Elle regroupe les méthodes permettant de :
 *  - mettre à jour un client : updateClient
 *  - récupérer la liste des clients d'un conseiller particulier : getListCliByCons
 *  - récupérer un objet Client à l'aide de son idClient : getClientbyIdcli
 */
public class ClientService {

	/**
	 * Cette méthode a pour objectif d'obtenir la liste des clients sous la responsabilité d'un conseiller.
	 * @param cons, un Conseiller
	 * @return List<Client>, une liste d'objets Client
	 */
	public List<Client> getListCliByCons(Conseiller cons) {
		
		ClientDao clid = new ClientDao();
		CompteService cs = new CompteService();
		List<Client> listeClients = clid.getListCliByCons(cons);
		
		for (Client client : listeClients) {
			List<Compte> listeC = cs.getComptesbyClient(client);
			client.setListeComptes(listeC);
		}
		
		return listeClients;
		
	}
	
	
	/**
	 * Cette méthode a pour objectif d'obtenir un objet Client à l'aide de son idClient.
	 * Elle permet d'obtenir un client en appelant la méthode getClientByIdcli de la classe ClientDao,
	 * puis elle appelle la méthode getComptesbyClient de CompteService pour attribuer à ce client
	 * la liste des comptes qui lui sont associés.
	 * @param idcli
	 * @return un objet Client
	 */
	public Client getClientbyIdcli(int idcli) {
				
			ClientDao cld = new ClientDao();
			CompteService cpts = new CompteService();
			
			Client client = cld.getClientByIdcli(idcli);
			
			if (client != null) {
				List<Compte> listeComptes = cpts.getComptesbyClient(client);
				
				client.setListeComptes(listeComptes);
			}
			
			return client;
	}
	
	
	/**
	 * Cette méthode a pour objectif de mettre à jour les données d'un client dans la base de données,
	 * en appelant la méthode de la classe ClientDao updateClient.
	 * @param cl, un Client
	 */
	public void updateClient(Client cl) {
		ClientDao cld = new ClientDao();
		cld.updateClient(cl);
	}
	
}
