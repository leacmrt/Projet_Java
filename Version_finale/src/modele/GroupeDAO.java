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
public class GroupeDAO {
    public ArrayList<String> gettoutlesnoms() throws SQLException
            {
             ArrayList<String> cars = new ArrayList<>();
             
             Connection myConnection;
             myConnection=Authentification.init();
             Statement statement;
             ResultSet resultat;
             statement = myConnection.createStatement();
             String sql="SELECT * FROM groupe INNER JOIN promotion ON groupe.ID_Promotion=promotion.ID WHERE 1";  
             ResultSet result = statement.executeQuery(sql);
             int i=0;
             while (result.next())
             {
                String nom=result.getString("Nom_Groupe");
                //nom+=" - ";
                //nom+=result.getString("promotion.Nom");
                
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
    
    
    
    public int getsqlID(String nom) throws SQLException
    {
       int recup=0;
       Connection myConnection;
             myConnection=Authentification.init();
             Statement statement;
             ResultSet resultat;
             statement = myConnection.createStatement();
             String sql="SELECT * FROM groupe WHERE Nom_Groupe= '"+nom+"'";  
             ResultSet result = statement.executeQuery(sql);
             int i=0;
             while (result.next())
             {
               recup=result.getInt("ID");
               
             }
             
             System.out.println("ID groupe : "+recup );
      
      return recup;
    }
}
