package fr.proxibanque.proxibanquev2.presentation;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.proxibanque.proxibanquev2.domaine.Client;
import fr.proxibanque.proxibanquev2.domaine.ClientEntreprise;
import fr.proxibanque.proxibanquev2.domaine.ClientParticulier;
import fr.proxibanque.proxibanquev2.domaine.Compte;
import fr.proxibanque.proxibanquev2.domaine.CompteCourant;
import fr.proxibanque.proxibanquev2.domaine.CompteEpargne;
import fr.proxibanque.proxibanquev2.domaine.Conseiller;
import fr.proxibanque.proxibanquev2.service.ClientService;
import fr.proxibanque.proxibanquev2.service.CompteService;

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
@WebServlet("/AccueilServlet")
public class AccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccueilServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		traitement(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		traitement(request, response);
	}

	/**
	 * Cette méthode permet le traitement de la requête selon l'objectif de la
	 * servlet. Elle permet de rediriger depuis la page accueil.jsp l'utilisateur
	 * vers la bonne page, tout en préparant l'affichage des pages en question en
	 * mettant en session les bonnes informations. Selon le choix qu'il a réalisé,
	 * s'il a choisi l'option "Modifier les infos clients", il sera redirigé vers
	 * modifClient.jsp, s'il a choisi "Virement depuis ce compte", il sera redirigé
	 * vers "virement.jsp", et dans le troisième cas il sera redirigé vers
	 * "comptesClient.jsp".
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void traitement(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String clientChoisi = request.getParameter("optradio");
		String action = request.getParameter("action");

		RequestDispatcher dispatcher = null;
		HttpSession maSession = request.getSession();
		maSession.setAttribute("erreur", null);
		maSession.setAttribute("validvirement", null);
		maSession.setAttribute("validmodifs", null);

		if (maSession.getAttribute("cons") != null) {
			maSession.setAttribute("connecte", "ConnexionOK");
		}

		// Si l'utilisateur n'a rien coché, on le laisse sur la page.
		if (clientChoisi == null) {
			maSession.setAttribute("connecte", null);
			maSession.setAttribute("erreur", "NoSelection");
			dispatcher = request.getRequestDispatcher("accueil.jsp");
		} else {

			// Sinon, on identifie quel type de client c'est :
			boolean isParticulier = false;
			int idclichoisi = 0;

			if (clientChoisi.substring(0, 1).equals("P")) {
				isParticulier = true;
				idclichoisi = Integer.parseInt(clientChoisi.substring(1));
			} else {
				idclichoisi = Integer.parseInt(clientChoisi.substring(1));
			}

			// Instanciation du client choisi et mise en session :
			ClientService cs = new ClientService();
			try {
				if (isParticulier == true) {
					ClientParticulier clientEnSession = (ClientParticulier) cs.getClientbyIdcli(idclichoisi);
					clientEnSession.setConseiller((Conseiller) maSession.getAttribute("cons"));
					maSession.setAttribute("cli", clientEnSession);
				} else {
					ClientEntreprise clientEnSession = (ClientEntreprise) cs.getClientbyIdcli(idclichoisi);
					clientEnSession.setConseiller((Conseiller) maSession.getAttribute("cons"));
					maSession.setAttribute("cli", clientEnSession);
				}
			} catch (NullPointerException npe) {
				maSession.setAttribute("erreur",
						"Erreur : un problème est survenu avec le client. Veuillez recommencer.");
				dispatcher = request.getRequestDispatcher("erreur.jsp");
			}

			// Redirection de l'utilisateur :
			if (action.equals("Modifier les informations du client")) {
				maSession.setAttribute("connecte", null);
                                
                                Client cli = (Client) maSession.getAttribute("cli");
                                List<Compte> listes = cli.getListeComptes();
				maSession.setAttribute("listeComptesCli", listes);
				maSession.setAttribute("InfoCompte1", null);
				maSession.setAttribute("InfoCompte2", null);
				maSession.setAttribute("compte1", null);
				maSession.setAttribute("compte2", null);

				for (Compte cpt : listes) {
					String numCompte = cpt.getNumCompte();
					String typeCompte = numCompte.substring(1, 2);

					if (typeCompte.equals("1")) {
						CompteCourant cc = (CompteCourant) cpt;
						maSession.setAttribute("InfoCompte1", "compteCourant");
						maSession.setAttribute("compte1", cc);
					} else {
						CompteEpargne ce = (CompteEpargne) cpt;
						maSession.setAttribute("InfoCompte2", "compteEpargne");
						maSession.setAttribute("compte2", ce);

					}
				}
                                
				dispatcher = request.getRequestDispatcher("modifClient.jsp");
				
			} else if (action.equals("Voir les comptes du client")) {
				maSession.setAttribute("connecte", null);

				Client cli = (Client) maSession.getAttribute("cli");
				List<Compte> listes = cli.getListeComptes();
				maSession.setAttribute("listeComptesCli", listes);
				maSession.setAttribute("InfoCompte1", null);
				maSession.setAttribute("InfoCompte2", null);
				maSession.setAttribute("compte1", null);
				maSession.setAttribute("compte2", null);

				for (Compte cpt : listes) {
					String numCompte = cpt.getNumCompte();
					String typeCompte = numCompte.substring(1, 2);

					if (typeCompte.equals("1")) {
						CompteCourant cc = (CompteCourant) cpt;
						maSession.setAttribute("InfoCompte1", "compteCourant");
						maSession.setAttribute("compte1", cc);
					} else {
						CompteEpargne ce = (CompteEpargne) cpt;
						maSession.setAttribute("InfoCompte2", "compteEpargne");
						maSession.setAttribute("compte2", ce);

					}
				}

				dispatcher = request.getRequestDispatcher("comptesClient.jsp");
			}

			else if (action.equals("Effectuer un virement")) {
				maSession.setAttribute("connecte", null);
				
				Client cli = (Client) maSession.getAttribute("cli");
				List<Compte> listes = cli.getListeComptes();
				maSession.setAttribute("listeComptesCli", listes);
				maSession.setAttribute("InfoCompte1", null);
				maSession.setAttribute("InfoCompte2", null);
				maSession.setAttribute("compte1", null);
				maSession.setAttribute("compte2", null);

				for (Compte cpt : listes) {
					String numCompte = cpt.getNumCompte();
					String typeCompte = numCompte.substring(1, 2);

					if (typeCompte.equals("1")) {
						CompteCourant cc = (CompteCourant) cpt;
						maSession.setAttribute("InfoCompte1", "compteCourant");
						maSession.setAttribute("compte1", cc);
					} else {
						CompteEpargne ce = (CompteEpargne) cpt;
						maSession.setAttribute("InfoCompte2", "compteEpargne");
						maSession.setAttribute("compte2", ce);

					}
				}

				// On prépare la page virement.jsp en mettant en session l'ensemble des comptes
				// de l'agence
				CompteService cpts = new CompteService();
				List<CompteCourant> allComptesCourant = cpts.getAllComptesCourants();
				List<CompteEpargne> allComptesEpargne = cpts.getAllComptesEpargnes();
				maSession.setAttribute("allComptesCourant", allComptesCourant);
				maSession.setAttribute("allComptesEpargne", allComptesEpargne);
				dispatcher = request.getRequestDispatcher("virement.jsp");
			}

		}

		dispatcher.forward(request, response);
	}

}
