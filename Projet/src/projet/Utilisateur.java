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

  
   private  int ID;
   private String Email ,Passwd, Nom, Prenom, Droit;
   public ConnexionBDD connec;
   
   public Utilisateur() throws SQLException
   { 
       connec = new ConnexionBDD();
   } //constructeur par défaut 
   
   
    public Utilisateur(int NewID,String NewEmail,String NewPass, String NewNom,String NewPrenom,String NewDroit) throws SQLException
   { 
       NewID=ID;
       NewEmail=Email;
       NewPass=Passwd;
       NewNom=Nom;
       NewPrenom=Prenom;
       NewDroit=Droit;
       System.out.println("Bonjour nouvel utilisateur"); //test affichage
      
   } //constructeur Normal
   
    
    
    public void connexion()
    {
    }
    
}
  //https://o7planning.org/fr/11071/creez-une-application-de-connexion-simple-et-securisez-les-pages-avec-java-servlet-filter