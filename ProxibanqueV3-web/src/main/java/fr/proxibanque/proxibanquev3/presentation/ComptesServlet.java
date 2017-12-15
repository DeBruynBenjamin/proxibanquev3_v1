package fr.proxibanque.proxibanquev3.presentation;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.proxibanque.proxibanquev3.domaine.Client;
import fr.proxibanque.proxibanquev3.domaine.Compte;
import fr.proxibanque.proxibanquev3.domaine.CompteCourant;
import fr.proxibanque.proxibanquev3.domaine.CompteEpargne;
import fr.proxibanque.proxibanquev3.domaine.Conseiller;

/**
 * Servlet implementation class ComptesServlet
 */
@WebServlet("/appli/comptes")
public class ComptesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComptesServlet() {
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

	protected void traitement(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Selon si le client étudié est particulier ou entreprise, on se comporte différemment:
		HttpSession maSession = request.getSession();
		Client cli = (Client) maSession.getAttribute("cli");
		List<Compte> listes = cli.getListeComptes();
		maSession.setAttribute("listeComptesCli", listes);
		
		
		for(Compte cpt : listes) {
			String numCompte = cpt.getNumCompte();
			String typeCompte = numCompte.substring(1,2);
  			if (typeCompte.equals("1")) {
  				CompteCourant cc = (CompteCourant) cpt;
  				maSession.setAttribute("compte", cc);
  				
  			}else{  
  				CompteEpargne ce = (CompteEpargne) cpt;
  				maSession.setAttribute("compte", ce);
  	
  			}
			}
				
	}
	
}
