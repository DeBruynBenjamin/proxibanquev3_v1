package fr.proxibanque.proxibanquev3.presentation;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.proxibanque.proxibanquev3.domaine.Conseiller;
import fr.proxibanque.proxibanquev3.service.AuthService;
import fr.proxibanque.proxibanquev3.service.ConseillerService;

/**
 * Servlet implementation class LoginServlet
 * Cette servlet est appelée après avoir soumis le formulaire d'authentification sur index.html.
 * Elle permet la vérification du mot de passe via une recherche en base de données à partir du login.
 * Si le mot de passe est correct, le conseiller est mis en session et l'utilisateur est redirigé vers la page
 * accueil.jsp.
 */
@WebServlet("/appli/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		
		String login=request.getParameter("logincons");
		String pwd=request.getParameter("mdpcons");
		Conseiller cons = new Conseiller(login,pwd);
		AuthService as = new AuthService();
		ConseillerService consServ = new ConseillerService();
		
		RequestDispatcher dispatcher;
		
		if (as.authOK(cons)) {
			try {
				Conseiller consEnSession = consServ.getConsByLogin(login);
				maSession.setAttribute("cons", consEnSession);
				dispatcher=request.getRequestDispatcher("accueil.jsp");
			}catch (NullPointerException npe) {
				maSession.setAttribute("erreur", "Erreur : un problème est survenu sur votre session. Veuillez vous reconnecter.");
				dispatcher = request.getRequestDispatcher("erreur.jsp");
			}
		}
		else {
			maSession.setAttribute("erreur", "Erreur");
			dispatcher = request.getRequestDispatcher("loginBis.jsp");
		}
		
		dispatcher.forward(request, response);
	
	}
}
