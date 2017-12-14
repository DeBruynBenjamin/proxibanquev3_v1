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
import fr.proxibanque.proxibanquev3.domaine.ClientEntreprise;
import fr.proxibanque.proxibanquev3.domaine.ClientParticulier;
import fr.proxibanque.proxibanquev3.domaine.Conseiller;

/**
 * @author Loriane
 * Cette classe permet la gestion de la base de données des clients.
 */
public class ClientDao {

	//update
	
	/**
	 * Cette méthode permet la mise à jour des données en base de données d'un client.
	 * @param client
	 */
	public void updateClient(Client client){
	        try {
	            Connection cnx=Dao.seConnecter();
	            
	            //Si on a affaire à un client entreprise
	            if (client.getTypeClient() == ClientEntreprise.TYPE_CLIENT_ENTREPRISE) {
	            	
	            	ClientEntreprise cliEnt = (ClientEntreprise) client;
	            	
	            	//RaisonSociale
	            	String majClient="update client set raisonsociale = '?' where idcli=?";
	            	PreparedStatement pstmt = cnx.prepareStatement(majClient);
	            	pstmt.setString(1, cliEnt.getRaisonSociale());
	            	pstmt.setInt(2, cliEnt.getIdCli());
	            	pstmt.executeUpdate();
	            	//SIRET
	            	String majClient2="update client set nSiret = '?' where idcli=?";
	            	pstmt = cnx.prepareStatement(majClient2);
	            	pstmt.setString(1, cliEnt.getnSiret());
	            	pstmt.setInt(2, cliEnt.getIdCli());
	            	pstmt.executeUpdate();
	            	//Adresse
	            	String majClient3="update client set adresse = '?' where idcli=?";
	            	pstmt = cnx.prepareStatement(majClient3);
	            	pstmt.setString(1, cliEnt.getAdresse());
	            	pstmt.setInt(2, cliEnt.getIdCli());
	            	pstmt.executeUpdate();
	            	//CodePostal
	            	String majClient4="update client set codepostal = '?' where idcli=?";
	            	pstmt = cnx.prepareStatement(majClient4);
	            	pstmt.setString(1, cliEnt.getCodePostal());
	            	pstmt.setInt(2, cliEnt.getIdCli());
	            	pstmt.executeUpdate();
	            	//Ville
	            	String majClient5="update client set ville = '?' where idcli=?";
	            	pstmt = cnx.prepareStatement(majClient5);
	            	pstmt.setString(1, cliEnt.getVille());
	            	pstmt.setInt(2, cliEnt.getIdCli());
	            	pstmt.executeUpdate();
	            	//Téléphone
	            	String majClient6="update client set telephone = '?' where idcli=?";
	            	pstmt = cnx.prepareStatement(majClient6);
	            	pstmt.setString(1, cliEnt.getTelephone());
	            	pstmt.setInt(2, cliEnt.getIdCli());
	            	pstmt.executeUpdate();
	            	//Email
	            	String majClient7="update client set email = '?' where idcli=?";
	            	pstmt = cnx.prepareStatement(majClient7);
	            	pstmt.setString(1, cliEnt.getEmail());
	            	pstmt.setInt(2, cliEnt.getIdCli());
	            	pstmt.executeUpdate();
	            	
	            	
	            }
	            else {
	            	
	            	ClientParticulier cliEnt = (ClientParticulier) client;
	            	
	            	//Nom
	            	String majClient="update client set nomcli = ? where idcli=?";
	            	PreparedStatement pstmt = cnx.prepareStatement(majClient);
	            	pstmt.setString(1, cliEnt.getNomCli());
	            	pstmt.setInt(2, cliEnt.getIdCli());
	            	pstmt.executeUpdate();
	            	//Prénom
	            	String majClient2="update client set prenomcli = ? where idcli=?";
	            	pstmt = cnx.prepareStatement(majClient2);
	            	pstmt.setString(1, cliEnt.getPrenomCli());
	            	pstmt.setInt(2, cliEnt.getIdCli());
	            	pstmt.executeUpdate();
	            	//Adresse
	            	String majClient3="update client set adresse = ? where idcli=?";
	            	pstmt = cnx.prepareStatement(majClient3);
	            	pstmt.setString(1, cliEnt.getAdresse());
	            	pstmt.setInt(2, cliEnt.getIdCli());
	            	pstmt.executeUpdate();
	            	//CodePostal
	            	String majClient4="update client set codepostal = ? where idcli=?";
	            	pstmt = cnx.prepareStatement(majClient4);
	            	pstmt.setString(1, cliEnt.getCodePostal());
	            	pstmt.setInt(2, cliEnt.getIdCli());
	            	pstmt.executeUpdate();
	            	//Ville
	            	String majClient5="update client set ville = ? where idcli=?";
	            	pstmt = cnx.prepareStatement(majClient5);
	            	pstmt.setString(1, cliEnt.getVille());
	            	pstmt.setInt(2, cliEnt.getIdCli());
	            	pstmt.executeUpdate();
	            	//Téléphone
	            	String majClient6="update client set telephone = ? where idcli=?";
	            	pstmt = cnx.prepareStatement(majClient6);
	            	pstmt.setString(1, cliEnt.getTelephone());
	            	pstmt.setInt(2, cliEnt.getIdCli());
	            	pstmt.executeUpdate();
	            	//Email
	            	String majClient7="update client set email = ? where idcli=?";
	            	pstmt = cnx.prepareStatement(majClient7);
	            	pstmt.setString(1, cliEnt.getEmail());
	            	pstmt.setInt(2, cliEnt.getIdCli());
	            	pstmt.executeUpdate();
	            	
	            }

	            
	            Dao.seDeconnecter(cnx);
	        } catch (SQLException ex) {
	            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
	        }
	   }
	
	
	
	//select
	/**
	 * Cette méthode permet d'obtenir la liste des clients associés à un conseiller spécifique.
	 * @param conseiller
	 * @return une liste d'objets Client
	 */
	public List<Client> getListCliByCons(Conseiller conseiller){
	       try {
	    	   
	    	   List<Client> clients =new ArrayList<>();
	    	   
	            // On cree une connexion a une base de donnee parametree ds la classe Dao
	            Connection cnx = Dao.seConnecter();
	            
	            Statement stat1 = cnx.createStatement();
	            
	            String sql = "select * from client where upper(logincons)=upper('"+conseiller.getLoginCons()+"')";
	            ResultSet rs=stat1.executeQuery(sql);

	            
	            while(rs.next()){
	                
	            	if(rs.getString(2).equalsIgnoreCase("entreprise")) {
	            		
	            		int idcli = rs.getInt(1);
		                String raisons = rs.getString(5);
		                String siret = rs.getString(6);
		                String adresse = rs.getString(7);
		                String codepostal = rs.getString(8);
		                String ville = rs.getString(9);
		                String telephone = rs.getString(10);
		                String email = rs.getString(11);
		                
		               ClientEntreprise client = new ClientEntreprise(idcli,0,adresse,codepostal,ville,telephone,email,conseiller,raisons,siret);
		               clients.add(client);
		               
	            	}
	            	
	            	else {
	            		
	            		int idcli = rs.getInt(1);
		                String prenomcli = rs.getString(3);
		                String nomcli = rs.getString(4);
		                String adresse = rs.getString(7);
		                String codepostal = rs.getString(8);
		                String ville = rs.getString(9);
		                String telephone = rs.getString(10);
		                String email = rs.getString(11);
		                
		               ClientParticulier client = new ClientParticulier(idcli,0,adresse,codepostal,ville,telephone,email,conseiller,nomcli,prenomcli);
		               clients.add(client);
	            	}
	                
	            }
	            
	            Dao.seDeconnecter(cnx);
	            
	            return clients;
	             
	                    } catch (SQLException ex) {
	            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
	        }
	       
	       return null;
	   }
	
	
	/**
	 * Cette méthode permet d'obtenir un objet Client à partir d'un idClient.
	 * @param idcli
	 * @return Client
	 */
	public Client getClientByIdcli(int idcli){
	       try {
	    	   
	            // On cree une connexion a une base de donnee parametree ds la classe Dao
	            Connection cnx = Dao.seConnecter();
	            
	            Statement stat1 = cnx.createStatement();
	            
	            String sql = "select * from client where idcli="+idcli;
	            ResultSet rs=stat1.executeQuery(sql);

	            
	            while(rs.next()){
	                
	            	if(rs.getString(2).equalsIgnoreCase("entreprise")) {
	            		
	            		int idclient = rs.getInt(1);
		                String raisons = rs.getString(5);
		                String siret = rs.getString(6);
		                String adresse = rs.getString(7);
		                String codepostal = rs.getString(8);
		                String ville = rs.getString(9);
		                String telephone = rs.getString(10);
		                String email = rs.getString(11);
		                
		               ClientEntreprise client = new ClientEntreprise(idclient,0,adresse,codepostal,ville,telephone,email,null,raisons,siret);
		               return client;
		               
	            	}
	            	
	            	else {
	            		
	            		int idclient = rs.getInt(1);
		                String prenomcli = rs.getString(3);
		                String nomcli = rs.getString(4);
		                String adresse = rs.getString(7);
		                String codepostal = rs.getString(8);
		                String ville = rs.getString(9);
		                String telephone = rs.getString(10);
		                String email = rs.getString(11);
		                
		               ClientParticulier client = new ClientParticulier(idclient,0,adresse,codepostal,ville,telephone,email,null,nomcli,prenomcli);
		               return client;
	            	}
	                
	            }
	            
	            Dao.seDeconnecter(cnx);
	             
	        } catch (SQLException ex) {
	            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
	        }
	       
	       return null;
	   }
	
}
