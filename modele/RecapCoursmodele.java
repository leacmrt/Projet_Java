/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import controlleur.AuthentificationControleur;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author lele1
 */
public class RecapCoursmodele {
    
    AuthentificationControleur control;
    Connection myConnection;
        public void chargecoursRecap(JPanel la,int ID_utli1,int etat) throws SQLException
      {
          control = new AuthentificationControleur ();
          myConnection = Authentification.init();
          Statement util = null;
          ResultSet resulutil = null;
          ResultSet resulutil2 = null;
          
          int nbr_cours = 0; //nombre de cours
          
          System.out.println("OUI");
          ArrayList<String> noms = new ArrayList<String>();
          ArrayList<String> groupes = new ArrayList<String>();
     
          ArrayList<ArrayList<ArrayList<String>>> tab = new ArrayList<ArrayList<ArrayList<String>>>();
          util= myConnection.createStatement();
          
          String date_premiere_seance_Str = "";
                       String date_derniere_seance_Str = "";
                       String duree_total_Str = "";
                       String nb_seance_Str = "";
                       ArrayList<String> date_premiere_seance_tab = new ArrayList<String>();
                       ArrayList<String> date_derniere_seance_tab = new ArrayList<String>(); 
                       ArrayList<String> duree_total_tab = new ArrayList<String>();
                       ArrayList<String> nb_seance_tab = new ArrayList<String>();
          
          if(etat==0){
              
              
                   String sql = "SELECT Nom_Cours,Nom_Groupe,ID_Utilisateur FROM seance_groupe sg INNER JOIN seance s ON sg.ID_Seance=s.ID ";
                   sql+="INNER JOIN seance_enseignant se ON se.ID_Seance=sg.ID_Seance ";
                   sql+="INNER JOIN cours co ON s.ID_Cours =co.ID ";
                   sql+="INNER JOIN groupe ON sg.ID_Groupe = groupe.ID ";
                   sql+="INNER JOIN enseignant on enseignant.ID_Cours = co.ID ";
                   sql+="GROUP BY Nom_Cours,Nom_Groupe ";
                   sql += "HAVING ID_Utilisateur ='"+ID_utli1+"'";
                   resulutil = util.executeQuery(sql);
                   
                   
                   while (resulutil.next()) {  //Pour chaque cours avec un groupe
                       
                       
                       
                       
                       String nomCours = resulutil.getString("nom_Cours");
                       String id = resulutil.getString("ID_Utilisateur");
                       String nomGroupe = resulutil.getString("nom_Groupe");
                       nbr_cours = nbr_cours + 1;
                       System.out.println(nomCours +" "+nomGroupe);
                       
                       ArrayList<String> cours_groupe = new ArrayList<String>();
                       noms.add(nomCours);
                       groupes.add(nomGroupe);
                       
                       }
                       
                   for (int h = 0; h<nbr_cours;h++){
                       
                       String nomCours = noms.get(h);
                       String nomGroupe = groupes.get(h);
                       
                        String sql2 = "SELECT sg.ID_Seance,Heure_Debut, Heure_Fin, Date, Nom_Groupe, ID_Utilisateur, Nom_Cours, Etat  ";
                        sql2+=" FROM seance_groupe sg INNER JOIN seance s ON sg.ID_Seance=s.ID  ";
                        sql2+=" INNER JOIN cours ON s.ID_Cours = cours.ID  ";
                        sql2+=" INNER JOIN seance_enseignant se ON se.ID_Seance=sg.ID_Seance  ";
                        sql2+=" INNER JOIN enseignant ON enseignant.ID_Cours = s.ID_Cours ";
                        sql2+=" INNER JOIN groupe on groupe.ID = sg.ID_Groupe ";
                        sql2+=" GROUP BY ID_Seance ";
                        sql2+=" HAVING ID_Utilisateur='"+ID_utli1+"' AND Nom_Groupe='"+nomGroupe+"' AND Etat='passe' AND Nom_Cours='"+nomCours+"'";
                       resulutil2 = util.executeQuery(sql2);
                       
                       
                       //Ajout dans le tableau 
                       ArrayList<ArrayList<String>> tab2 = new ArrayList<ArrayList<String>>();
                       String nom_complet = nomCours+" "+nomGroupe;
                       ArrayList<String> nom_complet_tab = new ArrayList<String>();
                       nom_complet_tab.add(nom_complet);
                       tab2.add(nom_complet_tab);
                      
                       
                       String premiere_Seance = " ";
                       Date date_premiere_seance = new Date(9999,99,99);
                       Date date_derniere_seance = new Date(0000,00,00);
                       int nbr_seance = 0;
                       int duree_total = 0;
                       System.out.println("nbr seances "+nbr_seance);
                       
                       
                       while (resulutil2.next()) {  //Pour chaque sÃ©ance de ce cours
                           
                           
                           
                           int heure_debut = resulutil2.getInt("Heure_Debut");
                           int heure_fin = resulutil2.getInt("Heure_Fin");
                           Date date = resulutil2.getDate("Date");
                           int duree = heure_fin - heure_debut;
                           
                           nbr_seance = nbr_seance + 1;  //Calcul du nombre de sÃ©ances*
                           System.out.println("seance "+nbr_seance);
                           duree_total = duree_total + duree;  //Calcul de la durÃ©e totale
                           System.out.println("nbr seances "+nbr_seance);
                           
                           if(date.before(date_premiere_seance)){ //On rÃ©cupÃ¨re la date de premiÃ¨re sÃ©ance
                               date_premiere_seance = date;
                           }
                           
                           if(date.after(date_derniere_seance)){ //On rÃ©cupÃ¨re la date de premiÃ¨re sÃ©ance
                               date_derniere_seance = date;
                           }
                           
                           //Conversions en String
                           String heure_debut_Str = String.valueOf(heure_debut);
                           String heure_fin_Str = String.valueOf(heure_fin);
                           String date_Str = String.valueOf(date);
                           String duree_Str = String.valueOf(duree);
                           date_premiere_seance_Str = String.valueOf(date_premiere_seance);
                           date_derniere_seance_Str = String.valueOf(date_derniere_seance);
                           nb_seance_Str = String.valueOf(nbr_seance);
                           
                           //Ajout dans un tableau
                           ArrayList<String> donnees = new ArrayList<String>();
                           donnees.add(heure_debut_Str);
                           donnees.add(heure_fin_Str);
                           donnees.add(date_Str);
                           donnees.add(duree_Str);
                           

                           
                           //Ajout dans tab2
                           tab2.add(donnees);
                       } 
                       
                       duree_total_Str = String.valueOf(duree_total);

                       date_premiere_seance_tab.add(date_premiere_seance_Str);
                       date_derniere_seance_tab.add(date_derniere_seance_Str);
                       duree_total_tab.add(duree_total_Str);
                       nb_seance_tab.add(nb_seance_Str);
                      System.out.println("nbr seances "+nb_seance_Str);
                       
                       tab2.add(date_premiere_seance_tab);
                       tab2.add(date_derniere_seance_tab);
                       tab2.add(duree_total_tab);
                       tab2.add(nb_seance_tab);
                       
                       tab.add(tab2);
                       
                    
                   }
          }
          
           else if(etat==1)
                   {
                       
                       String sql = "SELECT Nom_Cours,ID_Utilisateur FROM seance_groupe sg INNER JOIN seance s ON sg.ID_Seance=s.ID ";
                   sql+="INNER JOIN seance_enseignant se ON se.ID_Seance=sg.ID_Seance ";
                   sql+="INNER JOIN cours co ON s.ID_Cours =co.ID ";
                   sql+="INNER JOIN groupe ON sg.ID_Groupe = groupe.ID ";
                   sql+="INNER JOIN etudiant on etudiant.ID_Groupe = groupe.ID ";
                   sql+="GROUP BY Nom_Cours ";
                   sql += "HAVING ID_Utilisateur ='"+ID_utli1+"'";
                   resulutil = util.executeQuery(sql);
                   
                   
                   while (resulutil.next()) {  //Pour chaque cours
                       
                       
                       
                       
                       String nomCours = resulutil.getString("nom_Cours");
                       String id = resulutil.getString("ID_Utilisateur");
                       nbr_cours = nbr_cours + 1;
                       System.out.println(nomCours);
                       
                       ArrayList<String> cours_groupe = new ArrayList<String>();
                       noms.add(nomCours);
                       
                       }
                       
                   for (int h = 0; h<nbr_cours;h++){
                       
                       System.out.println("laa");
                       
                       String nomCours = noms.get(h);
                       
                        String sql2 = "SELECT sg.ID_Seance,Heure_Debut, Heure_Fin, Date, ID_Utilisateur, Nom_Cours, Etat  ";
                        sql2+=" FROM seance_groupe sg INNER JOIN seance s ON sg.ID_Seance=s.ID  ";
                        sql2+=" INNER JOIN cours ON s.ID_Cours = cours.ID  ";
                        sql2+=" INNER JOIN seance_enseignant se ON se.ID_Seance=sg.ID_Seance  ";
                        sql2+=" INNER JOIN groupe on groupe.ID = sg.ID_Groupe ";
                        sql2+=" INNER JOIN etudiant on etudiant.ID_Groupe = groupe.ID  ";
                        sql2+=" GROUP BY ID_Seance ";
                        sql2+=" HAVING ID_Utilisateur='"+ID_utli1+"' AND Etat='passe' AND Nom_Cours='"+nomCours+"'";
                       resulutil2 = util.executeQuery(sql2);
                       
                       
                       //Ajout dans le tableau 
                       ArrayList<ArrayList<String>> tab2 = new ArrayList<ArrayList<String>>();
                       String nom_complet = nomCours;
                       ArrayList<String> nom_complet_tab = new ArrayList<String>();
                       nom_complet_tab.add(nom_complet);
                       tab2.add(nom_complet_tab);
                      
                       
                       Date date_premiere_seance = new Date(9999,99,99);
                       Date date_derniere_seance = new Date(0000,00,00);
                       int nbr_seance = 0;
                       int duree_total = 0;
                       System.out.println("nbr seances "+nbr_seance);
                       
                       
                       while (resulutil2.next()) {  //Pour chaque sÃ©ance de ce cours
                           
                           
                           
                           int heure_debut = resulutil2.getInt("Heure_Debut");
                           int heure_fin = resulutil2.getInt("Heure_Fin");
                           Date date = resulutil2.getDate("Date");
                           int duree = heure_fin - heure_debut;
                           
                           nbr_seance = nbr_seance + 1;  //Calcul du nombre de sÃ©ances*
                           System.out.println("seance "+nbr_seance);
                           duree_total = duree_total + duree;  //Calcul de la durÃ©e totale
                           System.out.println("nbr seances "+nbr_seance);
                           
                           if(date.before(date_premiere_seance)){ //On rÃ©cupÃ¨re la date de premiÃ¨re sÃ©ance
                               date_premiere_seance = date;
                           }
                           
                           if(date.after(date_derniere_seance)){ //On rÃ©cupÃ¨re la date de premiÃ¨re sÃ©ance
                               date_derniere_seance = date;
                           }
                           
                           //Conversions en String
                           String heure_debut_Str = String.valueOf(heure_debut);
                           String heure_fin_Str = String.valueOf(heure_fin);
                           String date_Str = String.valueOf(date);
                           String duree_Str = String.valueOf(duree);
                           date_premiere_seance_Str = String.valueOf(date_premiere_seance);
                           date_derniere_seance_Str = String.valueOf(date_derniere_seance);
                           nb_seance_Str = String.valueOf(nbr_seance);
                           
                           //Ajout dans un tableau
                           ArrayList<String> donnees = new ArrayList<String>();
                           donnees.add(heure_debut_Str);
                           donnees.add(heure_fin_Str);
                           donnees.add(date_Str);
                           donnees.add(duree_Str);
                           

                           
                           //Ajout dans tab2
                           tab2.add(donnees);
                       } 
                       
                       duree_total_Str = String.valueOf(duree_total);

                       date_premiere_seance_tab.add(date_premiere_seance_Str);
                       date_derniere_seance_tab.add(date_derniere_seance_Str);
                       duree_total_tab.add(duree_total_Str);
                       nb_seance_tab.add(nb_seance_Str);
                      System.out.println("nbr seances "+nb_seance_Str);
                       
                       tab2.add(date_premiere_seance_tab);
                       tab2.add(date_derniere_seance_tab);
                       tab2.add(duree_total_tab);
                       tab2.add(nb_seance_tab);
                       
                       tab.add(tab2);
                       
                    
                   }
                 
                   }
      
          control.recupcours(la,nbr_cours,tab);
          
      }
        
        
        public int rechercheRecapID(String envoie) 
      {    int IDRecup;
           myConnection = Authentification.init();
           try{
           Statement util = myConnection.createStatement();
           ResultSet resulutil = null;
           String sql = "SELECT * FROM utilisateur WHERE  CONCAT(Prenom, ' ', Nom) LIKE '"+envoie+"'";
                   resulutil = util.executeQuery(sql);
                   if(resulutil.next())
                   {
                       IDRecup=resulutil.getInt("ID");
                       return IDRecup;
                      
                    } else{ System.out.println("Pas dans la BDD");
                            JOptionPane.showMessageDialog(null,"Utilisateur introuvable","Error",JOptionPane.PLAIN_MESSAGE);
                            return 0;
                           }
           }catch (SQLException e4) {
             
                System.out.println(e4.getMessage());
            }
        return 0;
      }
        
        
        public int rechercheRecapEtat(String envoie) 
      {    int EtatRecup =1 ;
            String EtatRecup2;
           myConnection = Authentification.init();
           try{
           Statement util = myConnection.createStatement();
           ResultSet resulutil = null;
           String sql = "SELECT * FROM utilisateur WHERE  CONCAT(Prenom, ' ', Nom) LIKE '"+envoie+"'";
                   resulutil = util.executeQuery(sql);
                   if(resulutil.next())
                   {
                       EtatRecup2=resulutil.getString("Droit");
                       if (EtatRecup2=="0")
                       {EtatRecup = 3;}
                       if (EtatRecup2=="1")
                       {EtatRecup = 1;}
                       if (EtatRecup2=="2")
                       {EtatRecup = 0;}
                       if (EtatRecup2=="3")
                       {EtatRecup = 2;}
                       return EtatRecup;
                      
                    } else{ System.out.println("Pas dans la BDD");
                            JOptionPane.showMessageDialog(null,"Utilisateur introuvable","Error",JOptionPane.PLAIN_MESSAGE);
                            return  0;
                           }
           }catch (SQLException e4) {
             
                System.out.println(e4.getMessage());
            }
        return 0;
      }
        
      
      
}
