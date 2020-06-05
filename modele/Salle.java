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
    
    
    
    
}
