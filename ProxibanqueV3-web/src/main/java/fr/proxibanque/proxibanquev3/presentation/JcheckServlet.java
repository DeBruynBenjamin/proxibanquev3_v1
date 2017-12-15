package fr.proxibanque.proxibanquev3.presentation;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.proxibanque.proxibanquev3.domaine.Client;
import fr.proxibanque.proxibanquev3.domaine.ClientEntreprise;
import fr.proxibanque.proxibanquev3.domaine.ClientParticulier;
import fr.proxibanque.proxibanquev3.domaine.Compte;
import fr.proxibanque.proxibanquev3.domaine.CompteCourant;
import fr.proxibanque.proxibanquev3.domaine.CompteEpargne;
import fr.proxibanque.proxibanquev3.domaine.Conseiller;
import fr.proxibanque.proxibanquev3.service.ClientService;
import fr.proxibanque.proxibanquev3.service.CompteService;
import fr.proxibanque.proxibanquev3.service.ConseillerService;

/**
 * Servlet implementation class AccueilServlet AccueilServlet est la servlet qui
 * permet de rediriger depuis la page accueil.jsp l'utilisateur vers la bonne
 * page, tout en préparant l'affichage des pages en question en mettant en
 * session les bonnes informations. Selon le choix qu'il a réalisé, s'il a
 * choisi l'option "Modifier les infos clients", il sera redirigé vers
 * modifClient.jsp, s'il a choisi "Virement depuis ce compte", il sera redirigé
 * vers "virement.jsp", et dans le troisième cas il sera redirigé vers
 * "comptesClient.jsp". Cette servlet est donc un pont entre la page accueil.jsp
 * et les autres pages de l'application.
 */
@WebServlet("/appli/jcheck")
public class JcheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JcheckServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		traitement(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		traitement(request, response);
	}
	
	/**
	 * Cette méthode permet le traitement de la requête selon l'objectif de la servlet. 
	 * Elle permet la vérification du mot de passe via une recherche en base de données à partir du login.
	 * Si le mot de passe est correct, le conseiller est mis en session et l'utilisateur est redirigé vers la page
	 * accueil.jsp.
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void traitement(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession maSession = request.getSession();
		maSession.setAttribute("erreur", null);
		
		String login=request.getRemoteUser();
		ConseillerService consServ = new ConseillerService();
		
		RequestDispatcher dispatcher;
		
		
				Conseiller consEnSession = consServ.getConsByLogin(login);
				maSession.setAttribute("cons", consEnSession);
				maSession.setAttribute("connecte", "ConnexionOK");
				dispatcher=request.getRequestDispatcher("/appli/accueilCheck.jsp");

		
		dispatcher.forward(request, response);
		
	}
}
