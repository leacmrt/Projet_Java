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
         
    
    
}
