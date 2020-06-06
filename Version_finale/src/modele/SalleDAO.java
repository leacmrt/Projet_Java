/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author lele1
 */
public class SalleDAO {
    public ArrayList<String> gettoutlesnoms() throws SQLException //recupère la liste des noms de salles 
            {
             ArrayList<String> cars = new ArrayList<>();
             
             Connection myConnection;
             myConnection=Authentification.init();
             Statement statement;
             ResultSet resultat;
             statement = myConnection.createStatement();
             String sql="SELECT * FROM salle WHERE 1";  
             ResultSet result = statement.executeQuery(sql);
             int i=0;
             while (result.next())
             {
                String nom=result.getString("Nom_Salle");
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
             myConnection=Authentification.init();
             Statement statement;
             ResultSet resultat;
             statement = myConnection.createStatement();
             String sql="SELECT * FROM salle  WHERE Nom_Salle = '"+nom+"'";  
             ResultSet result = statement.executeQuery(sql);
             int i=0;
             while (result.next())
             {
               recup=result.getInt("ID");
               
             }
             
             System.out.println("ID: "+recup );
      
      return recup;
    }
}
