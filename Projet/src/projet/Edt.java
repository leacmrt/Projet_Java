/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.System.exit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author lele1
 */
public class Edt {

    JTextField recherche1;
    Statement statement;
    ResultSet resultat,resultat1,resultat2;
    
    
    public Edt(View aThis) throws SQLException {
        essau(aThis);
    }
   
    public void  essau (JPanel la) throws SQLException
{
    JButton recherche;
    //JTextField recherche1;
    JButton btnCration = new JButton("Emploi du temps ");
    JButton deco=new JButton("Deconnexion ");
    
    recherche = new JButton("Recherche");//module de recherche
    recherche1 = new JTextField();
    System.out.print(recherche1.getText());
    
    deco.addActionListener(new Edt.DeconnexionListener() );
    recherche.addActionListener(new Edt.RechercheListener() ); 
        btnCration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Affichage emploi du temps ");
                 
                 
            }
        });
        btnCration.setBounds(10, 10, 150, 20); 
        la.add(btnCration);
        deco.setBounds(840,10, 130, 30);
        la.add(deco);
        
        recherche.setBounds(190, 10, 100, 20); 
        la.add(recherche);
        
        
        
        la.add(recherche1);
        recherche1.setBounds(290, 10, 150,20);
        ConnexionBDD essai= new ConnexionBDD();
        essai.chargecours(1);
        
        la.setVisible(true);
        
}
    public void recupdonnées()//recupere les données de la BDD 
    {
    
    }
    
    
    
    
    class DeconnexionListener extends JPanel implements ActionListener{ //pour se déconnecter
         
            @Override
            public void actionPerformed(ActionEvent e) {
                    ActionEvent a = null;
                    System.out.print("FERME MOI CETTE FENETRE");//test affichage oups
                    exit(0);
                    
                    
            }
             
        }
    
    
    
       class RechercheListener extends JPanel implements ActionListener{
         
            
            @Override
            public void actionPerformed(ActionEvent e) {
                    ActionEvent a = null;
                    String login2=recherche1.getText();
                    
                    System.out.println("Recherche dans BDD : " + login2);
                   
                   
            }

          
        }
}
