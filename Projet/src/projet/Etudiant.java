/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.awt.EventQueue;
import java.sql.SQLException;

/**
 *
 * @author lele1
 */
public  class Etudiant extends Utilisateur {
    
    private  String nom;
    private int ID_Etudiant, ID_Groupe;
   private static int etat=1;
    
    public Etudiant () throws SQLException
    { super();
      
    }
    public Etudiant(int NewID,String NewEmail,String NewPass, String NewNom,String NewPrenom,String NewDroit,int NewID_Groupe) throws SQLException
    {
       
      super(NewID,NewEmail,NewPass,NewNom,NewPrenom,"Oui",NewID_Groupe,etat);
      this.ID_Etudiant=NewID;
      this.ID_Groupe=NewID_Groupe;
      System.out.println("Coucou l'étudiant "+NewDroit);
      
       
    }
    
    
    public int getEtat()
    {return etat;}
}
