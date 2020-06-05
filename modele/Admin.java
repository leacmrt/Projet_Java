/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author lele1
 */
import java.sql.SQLException;

/**
 *
 * @author lele1
 */
public  class Admin extends Utilisateur {
    
    private  String nom;
    private int ID_Etudiant, ID_Groupe;
   private static int etat=1;
    
    public Admin () throws SQLException
    { super();
      
    }
    public Admin(int NewID,String NewEmail,String NewPass, String NewNom,String NewPrenom,String NewDroit) throws SQLException
    {
       
      super(NewID,NewEmail,NewPass,NewNom,NewPrenom,NewDroit,NewID,3);
      this.ID_Etudiant=NewID;
      System.out.println("Coucou l'admin "+NewDroit);
      
       
    }
    
    
    public int getEtat()
    {return etat;}
}
