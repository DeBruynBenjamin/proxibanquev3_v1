package fr.proxibanque.proxibanquev2.presentation;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import fr.proxibanque.proxibanquev2.domaine.Compte;
import fr.proxibanque.proxibanquev2.domaine.Conseiller;
import fr.proxibanque.proxibanquev2.domaine.Virement;
import fr.proxibanque.proxibanquev2.service.CompteService;
import fr.proxibanque.proxibanquev2.service.ExceptionVirement;
import fr.proxibanque.proxibanquev2.service.VirementService;

/**
 * Servlet implementation class ViremtServlet
 * Cette servlet est appelée depuis la page virement.jsp. Elle récupère la requête html générée par le
 * formulaire y étant présent, et traite les informations reçues, à savoir les informations
 * sur le virement à effectuer (compte émetteur, compte cible, montant, libellé). 
 * Elle permet ensuite l'inscription du virement en base de données, et
 * redirige par l'utilisateur vers la page accueil.jsp avec un message de validation du virement.
 * Si le virement n'est pas possible, l'utilisateur est renvoyé vers une page d'erreur.
 */
@WebServlet("/ViremtServlet")
public class ViremtServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViremtServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		traitement(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		traitement(request, response);
	}

	/**
	 * Cette méthode permet le traitement de la requête selon l'objectif de la servlet. 
	 * La méthode récupère la requête html générée par le
	 * formulaire y étant présent, et traite les informations reçues, à savoir les informations
	 * sur le virement à effectuer (compte émetteur, compte cible, montant, libellé). 
	 * Elle permet ensuite l'inscription du virement en base de données, et
	 * redirige par l'utilisateur vers la page accueil.jsp avec un message de validation du virement.
	 * Si le virement n'est pas possible, l'utilisateur est renvoyé vers une page d'erreur.
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void traitement (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher;
		HttpSession maSession = request.getSession();
		maSession.setAttribute("memecompte", null);
		maSession.setAttribute("validvirement", null);
		maSession.setAttribute("mistake", null);
		maSession.setAttribute("erreur1", null);
		
		// Etape 1 : Récupération des paramètres de la requête :
		String numCompteEmet = request.getParameter("compteEmetteur");
		String numCompteCible= request.getParameter("compteCible");
		
		if (numCompteEmet.equals(numCompteCible)) {
			maSession.setAttribute("memecompte", "memecompte");
			dispatcher =request.getRequestDispatcher("virement.jsp");
		}
		else {
				String montant = request.getParameter("montant");
				String libelle= request.getParameter("libelle");
				
				// Etape 2 : On recupere les objets associes aux informations recues via session
				
				Float montantVir = Float.parseFloat(montant);
				
				CompteService cs = new CompteService();
				
				Compte compteEmet = cs.selectCompteByNumCompte(numCompteEmet);
				Compte compteCible = cs.selectCompteByNumCompte(numCompteCible);
				
				// On recupere le conseiller en session
				Conseiller conseiller = (Conseiller) maSession.getAttribute("cons");
				
				Virement virement = new Virement(conseiller, compteEmet, compteCible, montantVir ,null, libelle);
				
				//Soumettre les paramètres de la requête à la couche service  
				
				maSession.setAttribute("virement1", virement);
			
				VirementService vS = new VirementService();
				
				try {
					// On realise le virement via la couche service 
					vS.realiserVirement(virement);
					maSession.setAttribute("validvirement", "Le virement a bien été pris en compte.");
					//préparer en fonction la réponse à envoyer
					dispatcher =request.getRequestDispatcher("accueil.jsp");
					
				} catch (ExceptionVirement e) {
					// si les comditions permettant le virement ne sont pas verifiees, on creer un message d'erreur mis en session
					String erreur1 = e.getMessage();
					maSession.setAttribute("erreur1", erreur1);
					maSession.setAttribute("mistake", "NotPossible");
					dispatcher =request.getRequestDispatcher("virement.jsp");
				}
		}
		dispatcher.forward(request, response);
	}
	
}
