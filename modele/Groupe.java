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
public class Groupe {
    private int ID ,ID_Promotion;
    private String Nom;
    
    public Groupe(int NewID,int NewID_Promotion,String NewNom)
    {
     this.ID=NewID;
     this.ID_Promotion=NewID_Promotion;
     this.Nom=NewNom;
     
    }

   public Groupe() {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
