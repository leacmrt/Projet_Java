/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import controlleur.AuthentificationControleur;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author lele1
 */
public  class Utilisateur extends JFrame{
  
   private AuthentificationControleur control = new AuthentificationControleur(); 
   private JPanel contentPane; //pour affichage
   private  int ID;
   private static int etat;
   private String Email ,Passwd, Nom, Prenom, Droit,Entier;
   public Authentification connec;
   
   public Utilisateur() throws SQLException
   {} //constructeur par défaut 
   
   
    public Utilisateur(int NewID,String NewEmail,String NewPass, String NewNom,String NewPrenom,String NewDroit,int ID_Groupe,int NewEtat) throws SQLException
   { 
       
       this.ID=NewID;
       this.Email=NewEmail;
       this.Passwd=NewNom;
       this.Nom=NewNom;
       this.Prenom=NewPrenom;
       this.Droit=NewDroit;
       Utilisateur.etat=NewEtat;
    
       control.chargefenetre(NewID,ID_Groupe,NewDroit,NewEtat);
       
       
   
   }

   public String getDroit()
   { return this.Droit;
   }
   
   public int getetat()
   { return this.etat;
   }
}