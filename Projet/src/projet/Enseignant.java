/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.sql.SQLException;

/**
 *
 * @author lele1
 */
public class Enseignant extends Utilisateur {
    private  String nom;
    
    public Enseignant () throws SQLException
    { super();
      System.out.print("Coucou l'enseignant");
    }
}
