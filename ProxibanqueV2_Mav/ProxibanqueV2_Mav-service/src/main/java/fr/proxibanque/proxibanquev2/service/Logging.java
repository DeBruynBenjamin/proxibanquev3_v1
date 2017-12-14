/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.proxibanque.proxibanquev2.service;

import org.apache.log4j.Logger;

import fr.proxibanque.proxibanquev2.domaine.Virement;

/**
 *
 * @author Admin
 */



public class Logging {

    private static Logging uniqueInstance;// Stockage de l'unique instance de cette classe.
    private String log;// Chaine de caractères représentant les messages de log.
    final static Logger logger = Logger.getLogger(Logging.class);

    // Constructeur en privé (donc inaccessible à l'extérieur de la classe).
    private Logging() {
        log = new String();
    }

    // Méthode statique qui sert de pseudo-constructeur (utilisation du mot clef "synchronized" pour le multithread).
    public static synchronized Logging getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Logging();
        }
        return uniqueInstance;
    }

    // Méthode qui permet d'ajouter un message de log
    public void genererLog(Virement virement) {
        // On ajoute également le message au log.
        this.log = virement.toString();
        logger.info(log);

    }

}
