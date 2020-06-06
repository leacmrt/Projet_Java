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
import java.lang.String;
import java.util.List;

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
     
     public ArrayList<String> recupseance() throws SQLException
    {
        int recup=0;
        ArrayList<String> lis = new ArrayList<>();
        String recupnom=null;
       List<List<String>> listOfLists = new ArrayList<>();
       Connection myConnection;
             myConnection=Authentification.init();
             Statement statement;
             ResultSet resultat;
             
             statement = myConnection.createStatement();
             String sql="SELECT * FROM seance se INNER JOIN cours co ON se.ID_Cours=co.ID INNER JOIN seance_groupe seg ON se.ID=seg.ID_Seance INNER JOIN seance_enseignant see ON see.ID_Seance=se.ID INNER JOIN enseignant en ON en.ID_Utilisateur=see.ID_Enseignant INNER JOIN groupe gr ON gr.ID=seg.ID_Groupe INNER JOIN utilisateur ut ON ut.ID=en.ID_Utilisateur WHERE NOT Etat= 'passe'";  
             ResultSet result = statement.executeQuery(sql);
             int i=0;
             while (result.next())
             {  
               recupnom= result.getString("Nom_Cours");
               recupnom+=" - "+result.getString("Nom_Groupe")+" - "+result.getString("Date");
               System.out.println("mon la ="+result.getString("Nom"));
               recup=result.getInt("ID_Seance");
                lis.add(String.valueOf(recupnom));
               //lis.add(String.valueOf(recup));
               //listOfLists.add(lis);
             }
             
             System.out.println("ID SEANCE: "+recup );
        return lis;
    }
     
      public ArrayList<String> recupseanceid() throws SQLException
    {
        int recup=0;
        ArrayList<String> lis = new ArrayList<>();
        String recupnom=null;
       List<List<String>> listOfLists = new ArrayList<>();
       Connection myConnection;
             myConnection=Authentification.init();
             Statement statement;
             ResultSet resultat;
             
             statement = myConnection.createStatement();
             String sql="SELECT * FROM seance se INNER JOIN cours co ON se.ID_Cours=co.ID INNER JOIN seance_groupe seg ON se.ID=seg.ID_Seance INNER JOIN seance_enseignant see ON see.ID_Seance=se.ID INNER JOIN enseignant en ON en.ID_Utilisateur=see.ID_Enseignant INNER JOIN groupe gr ON gr.ID=seg.ID_Groupe INNER JOIN utilisateur ut ON ut.ID=en.ID_Utilisateur WHERE NOT Etat= 'passe'";  
             ResultSet result = statement.executeQuery(sql);
             int i=0;
             while (result.next())
             {  
               
               recup=result.getInt("ID_Seance");
               lis.add(String.valueOf(recup));
               //listOfLists.add(lis);
             }
             
             System.out.println("ID SEANCE: "+recup );
        return lis;
    }
     
     public void setid(int nouveau)
    {     this.id=nouveau;}
}
