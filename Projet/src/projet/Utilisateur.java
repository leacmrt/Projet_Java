/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.System.exit;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author lele1
 */
public class Utilisateur extends JFrame{

  
   private  int ID;
   private String Email ,Passwd, Nom, Prenom, Droit;
   public ConnexionBDD connec;
   
   public Utilisateur() throws SQLException
   { 
       connec = new ConnexionBDD();
   } //constructeur par défaut 
   
   
    public Utilisateur(int NewID,String NewEmail,String NewPass, String NewNom,String NewPrenom,String NewDroit) throws SQLException
   { 
       NewID=ID;
       NewEmail=Email;
       NewPass=Passwd;
       NewNom=Nom;
       NewPrenom=Prenom;
       NewDroit=Droit;
       System.out.println("Bonjour nouvel utilisateur"); //test affichage
       
      testAffiche();
        
        
        
   } //constructeur Normal
   
    
    
    public void connexion()
    {
    }
    
    
    public void testAffiche()
    {
        
        JLabel felicitation;
        JButton deconnexion;
        this.setTitle(" WEBAPPS ");
        this.setSize(new Dimension(600,400));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        felicitation = new JLabel("FELICITATION! "); 
        JLabel felicitation2 = new JLabel("ICI SERA TON EMPLOI DU TEMPS ");
        deconnexion = new JButton("Deconnexion");
        Container contenu  = this.getContentPane();
        contenu.setLayout(null);
         
        contenu.add(felicitation);
        contenu.add(felicitation2);
        contenu.add(deconnexion);
        deconnexion.setBounds(250, 300, 120, 20);
        deconnexion.addActionListener(new Utilisateur.DeconnexionListener());
        felicitation.setBounds(260, 20, 100, 20);
        felicitation2.setBounds(210, 100, 200, 20);
        this.setVisible(true);
    
    }
    
    class DeconnexionListener extends JFrame implements ActionListener{
         
            @Override
            public void actionPerformed(ActionEvent e) {
                    ActionEvent a = null;
                    System.out.print("FERME MOI CETTE FENETRE");//test affichage oups
                    exit(0);
                    
                    
            }
             
        }
        
}
  //https://o7planning.org/fr/11071/creez-une-application-de-connexion-simple-et-securisez-les-pages-avec-java-servlet-filter