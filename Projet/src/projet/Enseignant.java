/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
      super(NewID,NewEmail,NewPass,NewNom,NewPrenom,NewDroit,NewID,0);
      this.ID_Enseignant=NewID;
      System.out.println("Coucou l'enseignant");//test affichage 
    }
    
    
    public ArrayList<String> gettoutlesnoms() throws SQLException
            {
             ArrayList<String> cars = new ArrayList<>();
             
             Connection myConnection;
             myConnection=ConnexionBDD.init();
             Statement statement;
             ResultSet resultat;
             statement = myConnection.createStatement();
             String sql="SELECT * FROM enseignant INNER JOIN utilisateur ON enseignant.ID_Utilisateur=utilisateur.ID WHERE 1";  
             ResultSet result = statement.executeQuery(sql);
             int i=0;
             while (result.next())
             {
                String nom=result.getString("Nom");
                if(!nom.isEmpty())
                {cars.add(nom);}
                
                i++;
             }
             
             for(int u=0;u<cars.size();u++)
             {
                 System.out.print(cars.get(u)+" ");
             }
             
             
            return cars;
            }
    
    public int gettryID(String nom) throws SQLException
    {
      int recup=0;
       Connection myConnection;
             myConnection=ConnexionBDD.init();
             Statement statement;
             ResultSet resultat;
             statement = myConnection.createStatement();
             String sql="SELECT * FROM enseignant INNER JOIN utilisateur ON enseignant.ID_Utilisateur=utilisateur.ID WHERE utilisateur.nom = '"+nom+"'";  
             ResultSet result = statement.executeQuery(sql);
             int i=0;
             while (result.next())
             {
               recup=result.getInt("ID_Utilisateur");
               
             }
             
             System.out.println("ID prof : "+recup );
      
      return recup;
    }
    
    
    
}
