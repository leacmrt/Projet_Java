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
public class Enseignant extends Utilisateur {
    private String nom;
    private int ID_Enseignant;
    private int[] ID_Cours;//car un prof peut avoir plusieurs cours 
    
    
    public Enseignant () throws SQLException
    { super();
      
    }
    
    public Enseignant(int NewID,String NewEmail,String NewPass, String NewNom,String NewPrenom,String NewDroit) throws SQLException
    {
      super(NewID,NewEmail,NewPass,NewNom,NewPrenom,NewDroit);
      NewID = ID_Enseignant;
      System.out.println("Coucou l'enseignant");//test affichage 
    }
}
