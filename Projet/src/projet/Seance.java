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

/**
 *
 * @author lele1
 */
public class Seance {
    private int id ;
    private int date;
    private int jour;
    private int semaine1;
    private int Heure_deb;
    private int Heure_fin;   
    private String etat;
    private int id_cours;
    private int id_groupe;
    
    public Seance(int NewID,int NewDate,int NewJour, int NewSemaine,int NewDeb,int NewFin,String NewEtat,int NewIDc,int NewIDg)
    {
    NewID= id ;
    NewDate= date;
    NewJour=jour;
    NewSemaine= semaine1;
    NewDeb=Heure_deb;
    NewFin= Heure_fin;   
    NewEtat= etat;
    NewIDc= id_cours;
    NewIDg= id_groupe;
    System.out.println("nouvelle seance");
    }
    
    
    public int getid()
    { return this.id;}
    
    public int getidsql(int NewDate,int NewJour, int NewSemaine,int NewDeb,int NewFin,String NewEtat,int NewIDc,int NewIDt) throws SQLException
    {
        int recup=0;
       Connection myConnection;
             myConnection=ConnexionBDD.init();
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
             
             System.out.println("ID prof : "+recup );
      
      return recup;
    }
     public int getdate()
    { return this.date;} 
    
     public int getjour()
    { return this.jour;}
     
      public int getsemaine1()
    { return this.semaine1;}
      
       public int getHeure_deb()
    { return this.Heure_deb;}
       
         public int getHeure_fin()
    { return this.Heure_fin;}
         
        public String getetat()
    { return this.etat;}
        
         public int getid_cours()
    { return this.id_cours;}
         
          public int getid_groupe()
    { return this.id_groupe;}
          
          public void setjour(int nouveau)
    {     this.jour=nouveau;}
           public void setdate(int nouveau)
    {     this.date=nouveau;}
           
            public void setseamine1(int nouveau)
    {     this.semaine1=nouveau;}
            
             public void setHeure_deb(int nouveau)
    {     this.Heure_deb=nouveau;}
             
              public void setHeure_fin(int nouveau)
    {     this.Heure_fin=nouveau;}
              
          public void setetat(String nouveau)
    {     this.etat=nouveau;}
          
         
}
