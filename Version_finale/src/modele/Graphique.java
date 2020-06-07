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
import controlleur.AuthentificationControleur;
import java.awt.*; 
import java.awt.event.*; 
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.*; 
import org.jfree.chart.*; 
import org.jfree.chart.plot.*; 
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import vue.GraphiqueVue;

public class Graphique  { 
  private JPanel pnl; 
       static Connection myConnection; 
       Statement statement,statement2,statement3;
       ResultSet resultat,resultat2,resultat3;
       int attente, passe,tp,td,co,compte, total,poo,web,anglais,id_Site,groupe = 0;
       String groupe_titre,nom_salle;
       Label titre = new Label();
       Label titre1 = new Label();
       JTextArea tot;
       
       AuthentificationControleur control = new  AuthentificationControleur();
  public Graphique(int id,int repport) throws SQLException { 
        
     System.out.println(repport+"repport");
     if(repport==2)
     {repport=0;}   
   
     Connection myConnection;
     myConnection=Authentification.init();
    // myConnection = init();
      switch (repport) {
          case 1:
              {
                  System.out.println("coucou je suis là");
                  statement = myConnection.createStatement();
                  String sql = "SELECT * FROM cours WHERE 1";
                  resultat = statement.executeQuery(sql);
                  ArrayList<String> re = new ArrayList<>();
                  ArrayList<Integer> pa =  new ArrayList<>();
                  ArrayList<Integer> at =  new ArrayList<>();
                  
                  int j=0;
                  while (resultat.next()) {
                     
                      String nom = resultat.getString("Nom_Cours");
                      re.add(nom);
                      String sql1 = "SELECT * FROM cours co INNER JOIN seance se ON co.ID=se.ID_Cours INNER JOIN seance_groupe seg ON seg.ID_Seance=se.ID WHERE Nom_Cours='"+nom+"' AND ID_Groupe='"+id+"'";
                      statement2 = myConnection.createStatement();
                      
                      resultat2 = statement2.executeQuery(sql1);
                      while (resultat2.next()) {
                          
                          groupe= resultat2.getInt("ID_Groupe");
                          String Etat = resultat2.getString("Etat");
                          if("passe".equals(Etat))
                          { passe++;}
                          else attente++;
                           //control.rempliretudiant(passe,attente,nom,groupe);
                      }
                      System.out.println("pa ="+passe);
                      System.out.println("at ="+attente);
                      pa.add(passe);
                      at.add(attente);
                      // titre = new JTextArea(" Statistique du TD n° "+String.valueOf(groupe));
                      
                     
                     j++;
                     passe=0;
                     attente=0;
                      
                  }  control.rempliretudiant(pa,at,re,groupe);
                  break;
              }
          case 3:
          { System.out.println("coucou salle");
             
              String sql1 = "SELECT * FROM cours co INNER JOIN seance se ON co.ID=se.ID_Cours INNER JOIN seance_salle seg ON seg.ID_Seance=se.ID INNER JOIN salle sa ON sa.ID=seg.ID_Salle WHERE ID_Salle='"+id+"'";
              statement2 = myConnection.createStatement();
              int groupe = 0;
              resultat2 = statement2.executeQuery(sql1);
              while (resultat2.next()) {
                  id_Site= resultat2.getInt("ID_Site");
                  nom_salle= resultat2.getString("Nom_Salle");
                  String nomcours = resultat2.getString("Nom_Cours");
                  if("POO JAVA".equals(nomcours))
                  { poo++;}
                  if("WEB DYNAMIQUE".equals(nomcours))
                  { web++;}
                  if("ANGLAIS".equals(nomcours))
                  { anglais++;}
                  
              }
            
              
              // Capacité des salles pour un site
              String sql5 = "SELECT * FROM salle INNER JOIN site ON salle.ID_Site=site.ID WHERE ID_Site='"+Graphique.this.id_Site+"'";
              statement3= myConnection.createStatement();
              int site = 0;
              ArrayList<String> noms = new ArrayList<>();
              ArrayList<Integer> cap = new ArrayList<>();
              
              resultat3 = statement3.executeQuery(sql5);
              int h=0;
              while (resultat3.next()) {
                  
                  String nom_salle=resultat3.getString("Nom_Salle");
                  noms.add(nom_salle);
                  int capacite=resultat3.getInt("Capacite");
                  cap.add(capacite);
                  
                  //h+=100;
                  
              }
              
              control.remplirsalle(nom_salle,poo,web,anglais,noms,cap);
             
              break;
          }
          case 0:
              {
                  System.out.println("coucou Enseignant");
                  statement = myConnection.createStatement();
                  String sql = "SELECT * FROM cours co INNER JOIN enseignant en ON co.ID=en.ID_Cours WHERE ID_Utilisateur ='"+id+"'";
                  resultat = statement.executeQuery(sql);
                  ArrayList<String> re = new ArrayList<>();
                  ArrayList<Integer> pa =  new ArrayList<>();
                  ArrayList<Integer> at =  new ArrayList<>();
                  ArrayList<Integer> cor =  new ArrayList<>();
                  
                  while (resultat.next()) {
                     
                      String nom = resultat.getString("Nom_Cours");
                      //int id = resultat.getInt("ID");
                      re.add(nom);
                      
                      String sql12 = "SELECT * FROM cours co INNER JOIN enseignant en ON co.ID=en.ID_Cours INNER JOIN seance_enseignant see ON see.ID_Enseignant=en.ID_Utilisateur INNER JOIN seance se ON se.ID=see.ID_Seance WHERE ID_Utilisateur ='"+id+"' AND Nom_Cours ='"+nom+"'";
                      //String sql1 = "SELECT * FROM cours INNER JOIN seance ON cours.ID=seance.ID_Cours WHERE Nom_Cours='"+nom+"' ";
                      statement2 = myConnection.createStatement();
                      
                      resultat2 = statement2.executeQuery(sql12);
                      while (resultat2.next()) {
                          int ID = resultat2.getInt("ID_Type");
                          if(ID==1)
                          { tp++;}
                          if(ID==2)
                          { td++;}
                          if(ID==3)
                          {co++;}    
                          
                      }
                      pa.add(tp);
                      at.add(td);
                      cor.add(co);
                      
                      total=tp+td;
                      control.remplirenseignant(re,pa,at,cor);
                      
                  }
                  break;
              }
          default:
              break;
        
      }
 

   
   


  } 

  
   
  
}