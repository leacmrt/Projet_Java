/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author lele1
 */
public class Cours {
    private int ID;
    private String Nom;
    private Color  Couleur;
    
    public Cours()
    {}
    
    public Cours(int NewID,String NewNom, Color NewCouleur)
    {NewID=ID;
     NewNom=Nom;
     NewCouleur=Couleur;
     System.out.println("nouveau cours");
    }

    public Color getCouleur()
    { return Couleur;}
         
    
    public ArrayList<String> gettoutlesnoms() throws SQLException
            {
             ArrayList<String> cars = new ArrayList<>();
             
             Connection myConnection;
             myConnection=ConnexionBDD.init();
             Statement statement;
             ResultSet resultat;
             statement = myConnection.createStatement();
             String sql="SELECT * FROM cours WHERE 1";  
             ResultSet result = statement.executeQuery(sql);
             int i=0;
             while (result.next())
             {
                String nom=result.getString("Nom_Cours");
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
             String sql="SELECT * FROM cours WHERE Nom_Cours = '"+nom+"'";  
             ResultSet result = statement.executeQuery(sql);
             int i=0;
             while (result.next())
             {
               recup=result.getInt("ID");
               
             }
             
             System.out.println("ID Cours : "+recup );
      
      return recup;
    }
}
