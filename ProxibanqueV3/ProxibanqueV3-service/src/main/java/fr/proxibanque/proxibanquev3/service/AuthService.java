package fr.proxibanque.proxibanquev3.service;

import fr.proxibanque.proxibanquev3.dao.ConseillerDao;
import fr.proxibanque.proxibanquev3.domaine.Conseiller;

/**
 * Cette classe gère les méthodes permettant l'authentification du conseiller.
 * @author Loriane & Hattmann
 *
 */
public class AuthService {

	/**
	 * Cette méthode a pour objectif de vérifier si les paramètres du conseiller en entrée (login, mot de passe)
	 * correspondent à ceux de la base.
	 * La méthode crée un nouvel objet Conseiller en utilisant le login donné et en appelant la méthode de la 
	 * classe ConseillerDao getConsByLogin.
	 * Elle compare ensuite le mot de passe de ce conseiller au mot de passe donné en paramètre d'entrée.
	 * Si c'est les mêmes, l'authentification est OK, on renvoie true. Sinon, false.
	 * @param cons, un objet Conseiller
	 * @return un boolean : vrai si l'authentification est correcte, faux sinon.
	 */
	public boolean authOK(Conseiller cons) {
		boolean result = false;
		ConseillerDao cd = new ConseillerDao();
		
		String passwordInsere = cons.getPassword();
		String loginInsere = cons.getLoginCons();
		
		Conseiller cons2 = cd.getConsByLogin(loginInsere);
		
		if (cons2.getPassword() != null && cons2.getPassword().equals(passwordInsere)) {
			result = true;
		}
		
		return result;
	}

}
