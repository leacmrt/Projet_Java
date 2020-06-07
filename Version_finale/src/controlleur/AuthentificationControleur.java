/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import modele.Authentification;
import modele.EdtModele;
import modele.Enseignant;
import modele.EnseignantRef;
import modele.Admin;
import modele.Etudiant;
import modele.Graphique;
import vue.AuthentificationVue;
import vue.Edt;
import vue.GraphiqueVue;
import vue.Onglet;
import vue.RecapCoursVue;
import vue.View;
import modele.RecapCoursmodele;
/**
 *
 * @author lele1
 */
public class AuthentificationControleur {
Authentification modele;
Onglet onglet ;
Graphique graph;
GraphiqueVue graphique;
EdtModele Edtt;
Edt EDT;
RecapCoursVue vue ;



     public AuthentificationControleur(int u) throws SQLException {
        this.modele = new Authentification();
    }

    public AuthentificationControleur() throws SQLException {
        
    }
    public void verification(AuthentificationVue la ,String login2, String password) throws SQLException {
        this.modele.verification(la, login2, password);
    }
    
    
    public void Etudiant(int ID, String Email, String Passwd, String Prenom, String Nom, String Droit, int ID_Groupe) throws SQLException {
       System.out.println("on avance l'étudiant");
       Etudiant etu = new Etudiant(ID,Email,Passwd,Prenom,Nom,Droit,ID_Groupe);
    }

    public void Enseignant(int ID, String Email, String Passwd, String Prenom, String Nom, String Droit) throws SQLException {
      System.out.println("on avance l'enseignant");
      Enseignant ens = new Enseignant(ID,Email,Passwd,Prenom,Nom,Droit);
    }
    
    public void EnseignantRef(int ID, String Email, String Passwd, String Prenom, String Nom, String Droit) throws SQLException {
      System.out.println("on avance l'enseignant");
      EnseignantRef ensRef = new EnseignantRef(ID,Email,Passwd,Prenom,Nom,Droit);
    }
    
    public void Admin(int ID, String Email, String Passwd, String Prenom, String Nom, String Droit) throws SQLException {
      System.out.println("on avance l'enseignant");
      Admin admin = new Admin(ID,Email,Passwd,Prenom,Nom,Droit);
    }
        
    public void chargefenetre(int ID_Utilisateur,int ID_Groupe, String droit, int NewEtat) throws SQLException {
         onglet = new Onglet(ID_Utilisateur,ID_Groupe,droit ,NewEtat);
    }

    public void graphique(int ID_Utilisateur1, int id_repport) {
          
                  try {
                      System.out.println("hello graphique"+id_repport);
                     graph = new Graphique(ID_Utilisateur1,id_repport);
             } catch (SQLException ex) {
                 Logger.getLogger(Edt.class.getName()).log(Level.SEVERE, null, ex);
             }
                  
        
    }

   

    public void rempliretudiant( ArrayList <Integer>   pa, ArrayList <Integer>  at, ArrayList <String> re, int groupe) throws SQLException {
       graphique = new GraphiqueVue(); 
        graphique.afficheetudiant(pa,at,re,groupe);
    }
    
    
     public void remplirenseignant( ArrayList <String> re,ArrayList <Integer>   pa, ArrayList <Integer>  at, ArrayList <Integer>  cor) throws SQLException {
       graphique = new GraphiqueVue(); 
        graphique.afficheenseignant(re,pa,at,cor);
    }
     
      public void remplirsalle (String nom,int poo,int web,int anglais, ArrayList<String> noms, ArrayList<Integer> cap) throws SQLException {
       graphique = new GraphiqueVue(); 
        graphique.affichesalle(nom,poo,web,anglais,noms,cap);
    }

    public int recherche(String string) throws SQLException {
      Edtt= new EdtModele();
      int result = Edtt.recherche(string);
   return result; }

    public int recherche1(String string) throws SQLException {
      Edtt= new EdtModele();
      int result = Edtt.recherche1(string);
   return result;  
    }
    
   public int recherche3(String string) throws SQLException {
      RecapCoursmodele recapcoursmodele= new RecapCoursmodele();
      int result = recapcoursmodele.rechercheRecapID(string);
   return result;  
    }
   
   public int recherche4(String string) throws SQLException {
      RecapCoursmodele recapcoursmodele= new RecapCoursmodele();
      int result = recapcoursmodele.rechercheRecapEtat(string);
   return result;  
    }

    public int rechercheSalle(String string) throws SQLException {
        Edtt= new EdtModele();
      int result = Edtt.rechercheSalle(string);
   return result;
    }

    public void chargecours(int i, JPanel la, int ID_Utilisateur1, int EtatUT) throws SQLException {
        Edtt= new EdtModele();
        System.out.println("pouquoi tout bug"+EtatUT);
        Edtt.chargecours(i,la,ID_Utilisateur1,EtatUT);
    }

    public void tableau(JTable table, JPanel la, Object[][] data) {
       EDT = new Edt();
       EDT.tableau(table, la, data);
    }

    public void recupcours(JPanel la, int nbr_cours, ArrayList<ArrayList<ArrayList<String>>> tab,int recherche) throws SQLException {
        vue = new RecapCoursVue(1);
        System.out.println("tu es là?");
        vue.affichage(la, nbr_cours, tab,recherche);
    }

   public boolean ajout(View aThis,JTextField Date, String jour1, JTextField Semaine, JTextField Heuredeb, JTextField HeureFin, String Etat, String cours, int Groupe,String Prof,String Salle,int id_prof,int id_cours,int id_salle) throws SQLException
   {
     Edtt= new EdtModele();
     boolean ou = Edtt.ajout(aThis, Date, jour1, Semaine, Heuredeb, HeureFin, Etat, cours, Groupe, Prof, Salle, id_prof, id_cours, id_salle);
     System.out.println("modif :");
   return ou;
   }

    public void modifetat(String NouveauType, String idseance) throws SQLException {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Edtt= new EdtModele();
        Edtt.modifetat(NouveauType,idseance);
    }

    public void ajout_enseignant(String NouveauEn, String idseance) throws SQLException {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       Edtt= new EdtModele();
       Edtt.ajout_enseignant(NouveauEn,idseance);
    }

    public void modif_nomcours(String Changement, String substring) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Edtt= new EdtModele();
       Edtt.modif_nomcours(Changement,substring);
        
    }

    public void supprimer(String groupe, String idseance, String supenseigant) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Edtt= new EdtModele();
       Edtt.supprimer(groupe,idseance,supenseigant);
    }

    public void ajout_groupe(String valueOf, String ind) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         Edtt= new EdtModele();
       Edtt.ajout_groupe(valueOf,ind);
    }

    

    
}
