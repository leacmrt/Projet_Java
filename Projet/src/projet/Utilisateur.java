/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author lele1
 */
public class Utilisateur extends JFrame{

   private JPanel contentPane; //pour affichage
   private  int ID;
   private static int etat;
   private String Email ,Passwd, Nom, Prenom, Droit,Entier;
   public ConnexionBDD connec;
   
   public Utilisateur() throws SQLException
   {} //constructeur par défaut 
   
   
    public Utilisateur(int NewID,String NewEmail,String NewPass, String NewNom,String NewPrenom,String NewDroit,int ID_Groupe,int NewEtat) throws SQLException
   { 
       
       this.ID=NewID;
       this.Email=NewEmail;
       this.Passwd=NewNom;
       this.Nom=NewNom;
       this.Prenom=NewPrenom;
       this.Droit=NewDroit;
       this.etat=NewEtat;
       
       System.out.println("Bonjour nouvel utilisateur"+ getetat()); //test affichage
       
       //Edt EDT = new Edt();
       Onglet onglet = new Onglet(ID_Groupe,getDroit(),NewEtat);
       
   
   } //constructeur Normal

   public String getDroit()
   { return Droit;
   }
   
   public int getetat()
   { return etat;
   }
}
  //https://o7planning.org/fr/11071/creez-une-application-de-connexion-simple-et-securisez-les-pages-avec-java-servlet-filter