package fr.proxibanque.proxibanquev3.service;

import fr.proxibanque.proxibanquev3.dao.CompteDao;
import fr.proxibanque.proxibanquev3.dao.VirementDao;
import fr.proxibanque.proxibanquev3.domaine.Compte;
import fr.proxibanque.proxibanquev3.domaine.CompteCourant;
import fr.proxibanque.proxibanquev3.domaine.Virement;

/**
 * @author adminl
 * Cette classe permet la gestion des virements.
 */
public class VirementService {

	/**
	 * Cette méthode permet de réaliser un virement
	 * @param virement
	 * @throws ExceptionVirement : si le compte émetteur n'a pas un solde suffisant, la méthode renvoie une erreur 
	 */
	public void realiserVirement(Virement virement) throws ExceptionVirement {

		Compte compteEmetteur = virement.getCompteEmetteur();
		Compte compteCible = virement.getCompteCible();

		float soldeCompteEmetteur = compteEmetteur.getSolde();
		float soldeCompteCible = compteCible.getSolde();
		float montantVirement = virement.getMontantVirement();

		if (soldeCompteEmetteur < 0 || montantVirement > soldeCompteEmetteur)
			throw new ExceptionVirement(
					"Le virement n'est pas réalisable.\n Vérifiez que le solde du compte émetteur soit suffisant.");
		else {

			float newSoldeCompteEmetteur = soldeCompteEmetteur - montantVirement;
			float newSoldeCompteCible = soldeCompteCible + montantVirement;

			CompteDao cD = new CompteDao();
			VirementDao vD = new VirementDao();

			if(compteEmetteur instanceof CompteCourant) {
				cD.updateCompteCourant(compteEmetteur, newSoldeCompteEmetteur);
			}
			else {
				cD.updateCompteEpargne(compteEmetteur, newSoldeCompteEmetteur);
			}
			
			if(compteCible instanceof CompteCourant) {
				cD.updateCompteCourant(compteCible, newSoldeCompteCible);
			}
			else {
				cD.updateCompteEpargne(compteCible, newSoldeCompteCible);
			}

			vD.insertVirement(virement);

		}

	}

}
