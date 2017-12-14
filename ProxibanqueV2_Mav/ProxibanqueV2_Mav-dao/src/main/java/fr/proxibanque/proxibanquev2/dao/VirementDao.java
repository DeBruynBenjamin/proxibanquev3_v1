package fr.proxibanque.proxibanquev2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.proxibanque.proxibanquev2.domaine.Virement;

public class VirementDao {

	
	 /**
	  * Cette méthode permet d'insérer un virement en base de données.
	 * @param virement
	 */
	public void insertVirement(Virement virement){
	        try {
	            //Initialisation
	            Connection cnx = Dao.seConnecter();
	            //requete SQL a executer
	            String insertionCompte = "insert into virement(numvir,logincons,compteemetteur,comptecible,montantvir,datevir,libelle) values(sequencevir.nextval,?,?,?,?,sysdate,?)";
	            PreparedStatement pstmt1 = cnx.prepareStatement(insertionCompte);
	            
	            pstmt1.setString(1, virement.getConseiller().getLoginCons());
	            pstmt1.setString(2, virement.getCompteEmetteur().getNumCompte());
	            pstmt1.setString(3, virement.getCompteCible().getNumCompte());
	            pstmt1.setDouble(4, virement.getMontantVirement());
	            pstmt1.setString(5, virement.getLibelle());
	            
	            //execution de la requete
	            pstmt1.execute();
	            //deconnexion de la bdd
	            Dao.seDeconnecter(cnx);
	        } catch (SQLException ex) {
	            Logger.getLogger(VirementDao.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }
	 
	 
	
	
}
