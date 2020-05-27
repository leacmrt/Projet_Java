/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

/**
 *
 * @author lele1
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class Onglet extends JFrame {
  private JTabbedPane onglet;
   
  public Onglet(int ID_Utilisateur,String droit,int etat) throws SQLException{
   
      
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(new Dimension(1050,800));
    this.setLocationRelativeTo(null);
    this.setResizable(false);
        setTitle("Accueil");
    Color fond=new Color(128,188,216);
    
    View edt = new View(this,fond,ID_Utilisateur,droit,etat); //page emploi du temps
    View recap = new View(this,Color.blue,ID_Utilisateur,droit,etat); // page recapitulatif de cours 
    View modification = new View(this,Color.lightGray,ID_Utilisateur,droit,etat);
    
//Création de plusieurs Panneau
    View[] tPan = {edt , recap,modification};
      
    //Création de notre conteneur d'onglets
    onglet = new JTabbedPane();
    int i = 0;
    String titre= "";
    for(View pan : tPan){
    if(i==0) titre = "Emploi du temps ";
    if(i==1) titre = "Recapitulatif des cours ";
    if(i==2) titre = "Modifications";
      
      onglet.addTab(titre, pan);
      i++;
    }
    
    this.getContentPane().setLayout(new GridLayout(1,2));
 
		
 
		
    //On passe ensuite les onglets au content pane
    this.getContentPane().add(onglet);
    //this.pack();
    this.setVisible(true);
  }
}