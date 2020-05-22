/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.awt.Color;

/**
 *
 * @author lele1
 */
public class Cours {
    private int ID;
    private String Nom;
    private Color  Couleur;
    
    public Cours(int NewID,String NewNom, Color NewCouleur)
    {NewID=ID;
     NewNom=Nom;
     NewCouleur=Couleur;
     System.out.println("nouveau cours");
    }

    public Color getCouleur()
    { return Couleur;}
            
    
}
