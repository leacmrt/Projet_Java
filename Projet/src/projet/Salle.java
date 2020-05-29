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
public class Salle {
    private int ID, ID_Site, Capacite;
    private String Nom;
    
    
    public Salle(){} //constructeur par défaut 
    
    public Salle(int NewID,int NewID_Site,int NewCapacite,String NewNom)
    {
       this.ID=NewID;
       this.ID_Site=NewID_Site;
       this.Capacite=NewCapacite;
       this.Nom=NewNom;
       System.out.println("Création de la salle "+this.Nom);    
    }
    
    
    public ArrayList<String> gettoutlesnoms() throws SQLException //recupère la liste des noms de salles 
            {
             ArrayList<String> cars = new ArrayList<>();
             
             Connection myConnection;
             myConnection=ConnexionBDD.init();
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
    
}
