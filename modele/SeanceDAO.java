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

/**
 *
 * @author lele1
 */
public class SeanceDAO {
    
     private int id ;
    private int date;
    private String Dte;
    private int jour;
    private int semaine1;
    private int Heure_deb;
    private int Heure_fin;   
    private String etat;
    private int id_cours;
    private int id_groupe;
    private int id_type;
    
    
     public int getidsql(String NewDate,int NewJour, int NewSemaine,int NewDeb,int NewFin,String NewEtat,int NewIDc,int NewIDt) throws SQLException
    {
        int recup=0;
       Connection myConnection;
             myConnection=Authentification.init();
             Statement statement;
             ResultSet resultat;
             statement = myConnection.createStatement();
             String sql="SELECT * FROM seance WHERE Date = '"+NewDate+"' AND Jour='"+NewJour+"' AND Semaine ='"+NewSemaine+"' AND Heure_Debut='"+NewDeb+"' AND Heure_Fin='"+NewFin+"' AND Etat= '"+NewEtat+"' AND ID_Cours ='"+NewIDc+"' AND ID_Type ='"+NewIDt+"'";  
             ResultSet result = statement.executeQuery(sql);
             int i=0;
             while (result.next())
             {
               recup=result.getInt("ID");
               
             }
             
             System.out.println("ID SEANCE: "+recup );
      setid(recup);
      
      return recup;
    }
     
     public void setid(int nouveau)
    {     this.id=nouveau;}
}
