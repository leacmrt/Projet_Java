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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author lele1
 */
public class EnseignantRef extends Utilisateur {
    private String nom;
    private int ID_Enseignant;
    private int[] ID_Cours;//car un prof peut avoir plusieurs cours 
    
    
    public EnseignantRef () throws SQLException
    { super();
      
    }
    
    public EnseignantRef(int NewID,String NewEmail,String NewPass, String NewNom,String NewPrenom,String NewDroit) throws SQLException
    {
      super(NewID,NewEmail,NewPass,NewNom,NewPrenom,NewDroit,NewID,2);
      this.ID_Enseignant=NewID;
      System.out.println("Coucou l'enseignant Ref");//test affichage 
    } 

    
    
}

