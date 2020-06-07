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
        ArrayList<Integer> lis2 = new ArrayList<>();
        String recupnom=null;
       
       Connection myConnection;
             myConnection=Authentification.init();
             Statement statement,statement1,statement2;
             ResultSet resultat;
             
             statement =statement1=statement2=myConnection.createStatement();
             String sql="SELECT * FROM seance se INNER JOIN cours co ON se.ID_Cours=co.ID  WHERE NOT Etat= 'passe'"; 
             
         try (ResultSet result = statement.executeQuery(sql)) {
             int i=0;
             while (result.next())
             {
                 recupnom= result.getString("Nom_Cours");
                 recupnom+=" - "+" - "+result.getString("Date");
                 // System.out.println("mon la ="+result.getString("Nom"));
                 recup=result.getInt("ID");
                 lis2.add(recup);
                 
                 lis.add(String.valueOf(recupnom));
                 //lis.add(String.valueOf(recup));
                 //listOfLists.add(lis);
             }
             
             for(int t=0;t<lis2.size();t++)
             { String sql1="SELECT * FROM seance_enseignant WHERE ID_Seance= '"+lis2.get(t)+"'";
              
                 ResultSet result1 = statement1.executeQuery(sql1);
                 if(!result1.next())
                 {
                     String u=lis.get(t);
                     u+= " - sans enseignant";
                     lis.set(t, u);
                 }
                 
             } 
             
             for(int t=0;t<lis2.size();t++){
            String sql2="SELECT * FROM seance_groupe WHERE ID_Seance= '"+lis2.get(t)+"'";
             ResultSet result2= statement2.executeQuery(sql2);
                 if(!result2.next())
                 {
                     
                    String u=lis.get(t);
                     u+= " - sans groupe";
                     lis.set(t, u);
                     System.out.println(lis.get(t));
                 }
              }
            
         }
             
          
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
             Statement statement,statement1,statement2;
             ResultSet resultat,resultat1,resultat2;
             
             statement = statement1=statement2=myConnection.createStatement();
             String sql="SELECT * FROM seance se INNER JOIN cours co ON se.ID_Cours=co.ID  WHERE NOT Etat= 'passe'";
             //String sql="SELECT * FROM seance se INNER JOIN cours co ON se.ID_Cours=co.ID INNER JOIN seance_groupe seg ON se.ID=seg.ID_Seance INNER JOIN seance_enseignant see ON see.ID_Seance=se.ID INNER JOIN enseignant en ON en.ID_Utilisateur=see.ID_Enseignant INNER JOIN groupe gr ON gr.ID=seg.ID_Groupe INNER JOIN utilisateur ut ON ut.ID=en.ID_Utilisateur WHERE NOT Etat= 'passe'";  
             ResultSet result = statement.executeQuery(sql);
             int i=0;
             while (result.next())
             {  
               
               recup=result.getInt("ID");
               lis.add(String.valueOf(recup));
               //listOfLists.add(lis);
             }
            /* String sql1="SELECT DISTINCT Seance.ID_Seance,Nom_Cours,Nom_Groupe,Date FROM seance se INNER JOIN cours co ON se.ID_Cours=co.ID INNER JOIN seance_groupe seg ON se.ID=seg.ID_Seance INNER JOIN groupe gr ON gr.ID=seg.ID_Groupe INNER JOIN seance_enseignant WHERE NOT Etat= 'passe' AND seance_enseignant.ID_Seance!=se.ID";  
             ResultSet result1 = statement1.executeQuery(sql1);
             
             while (result1.next())
             {  
               
               recup=result1.getInt("Seance.ID_Seance");
               lis.add(String.valueOf(recup));
               //listOfLists.add(lis);
             }*/
             
             
             System.out.println("ID SEANCE: "+recup );
        return lis;
    }
     
     public void setid(int nouveau)
    {     this.id=nouveau;}
}
