/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.sql.SQLException;

/**
 *
 * @author lele1
 */
public class Utilisateur {

    /**
     * @param args the command line arguments
     */
 
   private  int ID;
   private String EMAIL ,PASSWD, Nom, PRENOM, DROIT;
   public ConnexionBDD connec;
   
   public Utilisateur() throws SQLException
   { 
       connec = new ConnexionBDD();
   } //constructeur par défaut 
   
   //faire un autre contruscteur qui récupère donner avec la BDD
    
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, SQLException {
        //Utilisateur essai= new Utilisateur();
        Authentification essai1 = new Authentification();
    }
    
    public void connexion()
    {
    }
    
}
  //https://o7planning.org/fr/11071/creez-une-application-de-connexion-simple-et-securisez-les-pages-avec-java-servlet-filter