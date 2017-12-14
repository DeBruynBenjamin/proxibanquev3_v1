package fr.proxibanque.proxibanquev3.presentation;

import java.io.IOException;

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
import fr.proxibanque.proxibanquev3.service.ConseillerService;
import java.util.List;

/**
 * Servlet implementation class ModifServlet
 * Cette servlet est appelée depuis la page modifClient.jsp. Elle récupère la requête html générée par le
 * formulaire y étant présent, et traite les informations reçues, à savoir les nouvelles informations
 * sur le client à modifier. Elle permet ensuite l'inscription de ces données en base de données, et
 * redirige par la suite l'utilisateur vers la page accueil.jsp où il pourra y observer les modifications
 * apportées.
 */
@WebServlet("/ModifServlet")
public class ModifServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		traitement(request, response);
	}

	/**
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		traitement(request, response);
	}
	
	/**
	 * Cette méthode permet le traitement de la requête selon l'objectif de la servlet. 
	 * La méthode traite les informations reçues, à savoir les nouvelles informations
	 * sur le client à modifier. Elle permet ensuite l'inscription de ces données en base de données, et
	 * redirige par la suite l'utilisateur vers la page accueil.jsp où il pourra y observer les modifications
	 * apportées.
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void traitement(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		ClientService cs = new ClientService();
		ConseillerService consServ = new ConseillerService();
		
		//Selon si le client étudié est particulier ou entreprise, on se comporte différemment:
		HttpSession maSession = request.getSession();
		Client cli = (Client) maSession.getAttribute("cli");
		Conseiller cons = (Conseiller) maSession.getAttribute("cons");
                
		//Récupération d'une première partie des données d'entrée
		String adresse = request.getParameter("adresse");
		String codePostal = request.getParameter("codepostal");
		String ville = request.getParameter("ville");
		String telephone = request.getParameter("telephone");
		String email = request.getParameter("email");
		
		//Récupération des autres données dépendantes du type de client, instanciation des objets et traitement
		if (cli.getTypeClient()==ClientEntreprise.TYPE_CLIENT_ENTREPRISE) {
			//Le client est une entreprise
			String raisonSociale = request.getParameter("raisonsociale");
			String nSiret = request.getParameter("siret");
			ClientEntreprise clientE = new ClientEntreprise(cli.getIdCli(),ClientEntreprise.TYPE_CLIENT_ENTREPRISE,
					adresse, codePostal, ville, telephone, email, cons, raisonSociale, nSiret);
			//On envoie à la couche service pour mettre ensuite en base de données
			cs.updateClient(clientE);
			
		}
		else {
			//Le client est un particulier
			String prenomCli = request.getParameter("prenom");
			String nomCli = request.getParameter("nom");
			ClientParticulier clientP = new ClientParticulier(cli.getIdCli(),ClientParticulier.TYPE_CLIENT_PARTICULIER,
					adresse, codePostal, ville, telephone, email, cons, nomCli, prenomCli);
			//On envoie à la couche service pour mettre ensuite en base de données
			cs.updateClient(clientP);
			maSession.setAttribute("cli", clientP);
		}
		
		//On recharge le conseiller avec les données mises à jour.
		String loginCons = cons.getLoginCons();
		
		Conseiller consEnSession = consServ.getConsByLogin(loginCons);
		maSession.setAttribute("cons", consEnSession);
		maSession.setAttribute("validmodifs", "Les modifications ont bien été prises en compte.");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("accueil.jsp");
		dispatcher.forward(request, response);
		
	}

}
