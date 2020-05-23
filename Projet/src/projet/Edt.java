/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.System.exit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lele1
 */
public class Edt {
    int semaine=1;
    JTextField recherche1;
    Statement statement;
    ResultSet resultat,resultat1,resultat2;
    
    
    public Edt(View aThis,int ID_Utilisateur,String droit) throws SQLException {
        essau(aThis,ID_Utilisateur,droit);
    }
   
    public void  essau (JPanel la,int ID_Utilisateur,String droit) throws SQLException
{
    ConnexionBDD ici= new ConnexionBDD();
    
    JButton recherche;
    JButton btnCration = new JButton("Emploi du temps ");
    JButton deco=new JButton("Deconnexion ");
    
          
    recherche = new JButton("Recherche");//module de recherche faire en sorte que ce soit que pour un enseignant 
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
        
        
         
        int plus=19;
        ici.chargecours(1,la,ID_Utilisateur);
        for(int h=1;h<51;h++)
        {
            int nombre =h;
        String text = Integer.toString(h); 
        
        JButton jour = new JButton(text);
       
        jour.setFont(new Font("Arial", Font.PLAIN, 10)); 
        jour.setBounds(plus, 60, 20, 20); 
        jour.setMargin(new Insets(0, 0, 0, 0));
        la.add(jour);
        
        jour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try { 
                    ici.chargecours(nombre,la,ID_Utilisateur);
                } catch (SQLException ex) {
                    Logger.getLogger(Edt.class.getName()).log(Level.SEVERE, null, ex);
                }
                 
                 
            }
            
           
        });
        
        plus+=20;
        
        }
        
        btnCration.setBounds(10, 10, 150, 20); 
        la.add(btnCration);
        deco.setBounds(900,10, 130, 30);
        la.add(deco);
        System.out.println(droit);
        
        if(!"".equals(droit))
        {recherche.setBounds(470, 10, 100, 20); 
        la.add(recherche);
        

        
        
        la.add(recherche1);
        recherche1.setBounds(320, 10, 150,20);}
       
       
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
