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
   private String Email ,Passwd, Nom, Prenom, Droit;
   public ConnexionBDD connec;
   
   public Utilisateur() throws SQLException
   {} //constructeur par défaut 
   
   
    public Utilisateur(int NewID,String NewEmail,String NewPass, String NewNom,String NewPrenom,String NewDroit) throws SQLException
   { 
       
       NewID=ID;
       NewEmail=Email;
       NewPass=Passwd;
       NewNom=Nom;
       NewPrenom=Prenom;
       NewDroit=Droit;
       System.out.println("Bonjour nouvel utilisateur"); //test affichage
       
       //Edt EDT = new Edt();
       Onglet onglet = new Onglet(NewID);
       
   
   } //constructeur Normal


}
  //https://o7planning.org/fr/11071/creez-une-application-de-connexion-simple-et-securisez-les-pages-avec-java-servlet-filter