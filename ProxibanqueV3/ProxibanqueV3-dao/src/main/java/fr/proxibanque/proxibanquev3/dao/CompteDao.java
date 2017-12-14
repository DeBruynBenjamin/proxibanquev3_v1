package fr.proxibanque.proxibanquev3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.proxibanque.proxibanquev3.domaine.Client;
import fr.proxibanque.proxibanquev3.domaine.Compte;
import fr.proxibanque.proxibanquev3.domaine.CompteCourant;
import fr.proxibanque.proxibanquev3.domaine.CompteEpargne;

/**
 * @author Loriane
 * Cette classe permet la gestion de la base de données des comptes.
 */
public class CompteDao {

	/**
	 * Cette méthode permet d'obtenir la liste de comptes associés à un client.
	 * @param cli, un objet Client
	 * @return une liste de Compte.
	 */
	public List<Compte> getComptesByIdcli(Client cli) {
	     
		List<Compte> comptes =new ArrayList<>();
		
		try {
	    	   

	            // On cree une connexion a une base de donnee parametree ds la classe Dao
	            Connection cnx = Dao.seConnecter();
	            
	            Statement stat1 = cnx.createStatement();
	            
	            //Parcourir la liste des comptes courants :
	            String sql = "select * from compte_courant where idcli="+cli.getIdCli();
	            ResultSet rs=stat1.executeQuery(sql);

	            while(rs.next()){
	                
	                String numcompte = rs.getString(1);
	                float solde = rs.getFloat(4);
	                String dateO = rs.getString(3);
	                float decouv = rs.getFloat(5);
	                
	               CompteCourant compte = new CompteCourant(numcompte,solde,dateO,cli,decouv);
	               comptes.add(compte);
	            }
	            
	            //Parcourir la liste des comptes épargne :
	            String sql2 = "select * from compte_epargne where idcli="+cli.getIdCli();
	            ResultSet rs2=stat1.executeQuery(sql2);

	            while(rs2.next()){
	                
	                String numcompte = rs2.getString(1);
	                float solde = rs2.getFloat(4);
	                String dateO = rs2.getString(3);
	                float tauxRem = rs2.getFloat(5);
	                
	               CompteEpargne compte = new CompteEpargne(numcompte,solde,dateO,cli,tauxRem);
	               comptes.add(compte);
	            }	            
	            
	            
	            Dao.seDeconnecter(cnx);
	            
	            return comptes;
	             
	        } catch (SQLException ex) {
	            Logger.getLogger(CompteDao.class.getName()).log(Level.SEVERE, null, ex);
	        }
	       
	       return comptes;		
		
		
	}

	/**
	 * Cette méthode permet d'obtenir le compte épargne d'un client donné.
	 * @param cli un objet Client
	 * @return CompteEpargne
	 */
	public CompteEpargne getComptesEpargneByIdcli(Client cli) {
	     
		CompteEpargne compte =new CompteEpargne();
		
		try {
	            // On cree une connexion a une base de donnee parametree ds la classe Dao
	            Connection cnx = Dao.seConnecter();
	            
	            Statement stat1 = cnx.createStatement();
	            
	            //Parcourir la liste des comptes épargne :
	            String sql2 = "select * from compte_epargne where idcli="+cli.getIdCli();
	            ResultSet rs2=stat1.executeQuery(sql2);

	            while(rs2.next()){
	                
	                String numcompte = rs2.getString(1);
	                float solde = rs2.getFloat(4);
	                String dateO = rs2.getString(3);
	                float tauxRem = rs2.getFloat(5);
	                
	               compte = new CompteEpargne(numcompte,solde,dateO,cli,tauxRem);
	            }	            
	            
	            
	            Dao.seDeconnecter(cnx);
	            
	            return compte;
	             
	        } catch (SQLException ex) {
	            Logger.getLogger(CompteDao.class.getName()).log(Level.SEVERE, null, ex);
	        }
	       
	       return compte;		

	}
	
	/**
	 * Cette méthode permet d'obtenir le compte courant d'un client donné.
	 * @param cli un objet Client
	 * @return CompteCourant
	 */
	public CompteCourant getComptesCourantByIdcli(Client cli) {
	     
		CompteCourant compte =new CompteCourant();
		
		try {
	            // On cree une connexion a une base de donnee parametree ds la classe Dao
	            Connection cnx = Dao.seConnecter();
	            
	            Statement stat1 = cnx.createStatement();
	            
	            //Parcourir la liste des comptes épargne :
	            String sql = "select * from compte_courant where idcli="+cli.getIdCli();
	            ResultSet rs=stat1.executeQuery(sql);

	            while(rs.next()){
	                
	                String numcompte = rs.getString(1);
	                float solde = rs.getFloat(4);
	                String dateO = rs.getString(3);
	                float decouv = rs.getFloat(5);
	                
	               compte = new CompteCourant(numcompte,solde,dateO,cli,decouv);
	            }	            
	            
	            Dao.seDeconnecter(cnx);
	            
	            return compte;
	             
	        } catch (SQLException ex) {
	            Logger.getLogger(CompteDao.class.getName()).log(Level.SEVERE, null, ex);
	        }
	       
	       return compte;		

	}
	
		
	/**
	 * Cette méthode permet de supprimer un compte en base de données.
	 * @param compte
	 */
	public void deleteCompte(Compte compte){
        
	    
        try {
            Connection cnx=Dao.seConnecter();
            
            //Initialisation
            String suppressionCompte="delete from compte where numcompte=?";
            PreparedStatement pstmt2 = cnx.prepareStatement(suppressionCompte);
            
            pstmt2.setString(1, compte.getNumCompte());
            pstmt2.executeUpdate();
            
            Dao.seDeconnecter(cnx);
            
        } catch (SQLException ex) {
            Logger.getLogger(CompteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
    
   /**
    * Cette méthode permet de mettre à jour un compte courant avec un nouveau solde.
    * @param compte
    * @param newSolde
    */
public void updateCompteCourant (Compte compte, float newSolde){
        try {
            Connection cnx=Dao.seConnecter();
            
            //Initialisation
            String majCompte="Update compte_courant set solde=? where numcomptecc=?";
            PreparedStatement pstmt = cnx.prepareStatement(majCompte);
            
            pstmt.setFloat(1, newSolde);
            pstmt.setString(2, compte.getNumCompte());
        
            pstmt.executeUpdate();
            
            Dao.seDeconnecter(cnx);
        } catch (SQLException ex) {
            Logger.getLogger(CompteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   /**
    * Cette méthode permet de mettre à jour un compte épargne avec un nouveau solde.
 * @param compte
 * @param newSolde
 */
public void updateCompteEpargne (Compte compte, float newSolde){
       try {
           Connection cnx=Dao.seConnecter();
           
           //Initialisation
           String majCompte="Update compte_epargne set solde=? where numcomptece=?";
           PreparedStatement pstmt = cnx.prepareStatement(majCompte);
           
           pstmt.setFloat(1, newSolde);
           pstmt.setString(2, compte.getNumCompte());
       
           pstmt.executeUpdate();
           
           Dao.seDeconnecter(cnx);
       } catch (SQLException ex) {
           Logger.getLogger(CompteDao.class.getName()).log(Level.SEVERE, null, ex);
       }
  }
  
   /**
    * Cette méthode permet de sélectionner tous les comptes courants de Proxibanque.
    * @return une liste de CompteCourant.
    */
public List<CompteCourant> selectAllComptesCourants(){
       try {
    	   
    	   List<CompteCourant> comptes=new ArrayList<>();
            // TODO code application logic here
            // On cree une connexion a une base de donnee parametree ds la classe Dao
            Connection cnx = Dao.seConnecter();
            
            Statement stat1 = cnx.createStatement();
            
            String sql = "select * from compte_courant";
            ResultSet rs=stat1.executeQuery(sql);
            
            //CompteCourant(String numCompte, float solde, String dateOuv, Client client, float decouvert)
            
            while(rs.next()){
                
                String numCompte= rs.getString(1);
                float  solde = rs.getFloat(4);
                String dateouv= rs.getString(3);
                int idCli = rs.getInt(2);
                float decouvert = rs.getFloat(5);
                    
                ClientDao cd = new ClientDao();
                
                Client client = cd.getClientByIdcli(idCli);
                
               CompteCourant compteC = new CompteCourant(numCompte,solde,dateouv,client,decouvert);
               comptes.add(compteC);
               
            }
            
            Dao.seDeconnecter(cnx);
            
            return comptes;
             
                    } catch (SQLException ex) {
            Logger.getLogger(CompteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       return null;
   }
   
/**
 * Cette méthode permet de sélectionner tous les comptes courants de Proxibanque.
 * @return une liste de CompteEpargne.
 */
public List<CompteEpargne> selectAllComptesEpargnes(){
       try {
    	   
    	   List<CompteEpargne> comptes=new ArrayList<>();
            // TODO code application logic here
            // On cree une connexion a une base de donnee parametree ds la classe Dao
            Connection cnx = Dao.seConnecter();
            
            Statement stat1 = cnx.createStatement();
            
            String sql = "select * from compte_epargne";
            ResultSet rs=stat1.executeQuery(sql);
            
            	while(rs.next()){
                
                String numCompte= rs.getString(1);
                float  solde = rs.getFloat(4);
                String dateouv= rs.getString(3);
                int idCli = rs.getInt(2);
                float tauxRem = rs.getFloat(5);
                    
                ClientDao cd = new ClientDao();
                
                Client client = cd.getClientByIdcli(idCli);
                
               CompteEpargne compteC = new CompteEpargne(numCompte,solde,dateouv,client,tauxRem);
               comptes.add(compteC);
               
            }
            
            Dao.seDeconnecter(cnx);
            
            return comptes;
             
                    } catch (SQLException ex) {
            Logger.getLogger(CompteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       return null;
   }
   
   /**
    * Cette méthode permet de créer un objet CompteCourant avec un numéro de compte
    * @param numCompte
    * @return un CompteCourant
    */
public CompteCourant selectCompteCourantByNumCompte (String numCompte){
       
	   CompteCourant compteC = new CompteCourant();
	   
        try {
            Connection cnx = Dao.seConnecter();
            
            String selectionByNumCompte = "select * from compte_courant where upper(numcomptecc)=upper('"+numCompte+"')";
            Statement state=cnx.createStatement();
            
            ResultSet rs=state.executeQuery(selectionByNumCompte);
            
            	while(rs.next()){
                
                float  solde = rs.getFloat(4);
                String dateouv= rs.getString(3);
                int idCli = rs.getInt(2);
                float decouvert = rs.getFloat(5);
                    
                ClientDao cd = new ClientDao();
                
                Client client = cd.getClientByIdcli(idCli);
                
               compteC = new CompteCourant(numCompte,solde,dateouv,client,decouvert);
               
               
            }
            Dao.seDeconnecter(cnx);
            
            return compteC;
            
        } catch (SQLException ex) {
            Logger.getLogger(CompteDao.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return compteC;
   }
 
/**
 * Cette méthode permet de créer un objet CompteEpargne avec un numéro de compte.
 * @param numCompte
 * @return un CompteEpargne
 */
   public CompteEpargne selectCompteEpargneByNumCompte (String numCompte){
       
	   CompteEpargne compteE = new CompteEpargne();
	   
        try {
            Connection cnx = Dao.seConnecter();
            
            String selectionByNumCompte = "select * from compte_epargne where upper(numcomptece)=upper('"+numCompte+"')";
            Statement state=cnx.createStatement();
            
            ResultSet rs=state.executeQuery(selectionByNumCompte);
            
            	while(rs.next()){
                
                float  solde = rs.getFloat(4);
                String dateouv= rs.getString(3);
                int idCli = rs.getInt(2);
                float tauxRem = rs.getFloat(5);
                    
                ClientDao cd = new ClientDao();
                
                Client client = cd.getClientByIdcli(idCli);
                
               compteE = new CompteEpargne(numCompte,solde,dateouv,client,tauxRem);
               
               
            }
            Dao.seDeconnecter(cnx);
            
            return compteE;
            
        } catch (SQLException ex) {
            Logger.getLogger(CompteDao.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
        return compteE;
   }
	

	
}
