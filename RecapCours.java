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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author roro
 */
public class RecapCours {
    
    int ID_Utilisateur1;
    int EtatUT;
    
    public RecapCours (View aThis,int ID_Utilisateur,String droit,int etat) throws SQLException
{
    ConnexionBDD connexion = new ConnexionBDD();
    RecapCours.this.ID_Utilisateur1=ID_Utilisateur; 
    RecapCours.this.EtatUT=etat;
    
    
    //chargecoursRecap(JPanel la,int ID_utli1,int etat)
    
    JButton btnCration = new JButton("Recaputatif des cours ");
    
    JButton deco=new JButton("Deconnexion ");
    deco.addActionListener(new DeconnexionListener() );
        btnCration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Affichage du recapitulatif des cours ");
                 
                 
            }
        });
        
        
        btnCration.setBounds(50, 10, 200, 20);
        deco.setBounds(800, 10, 150, 20);

        
        aThis.add(btnCration);
        aThis.add(deco);

        
        connexion.chargecoursRecap(aThis,ID_Utilisateur,etat);
        
}

    
      
  

     
   
    
    class DeconnexionListener extends JPanel implements ActionListener{ //pour se déconnecter
         
            @Override
            public void actionPerformed(ActionEvent e) {
                    ActionEvent a = null;
                    System.out.print("FERME MOI CETTE FENETRE");//test affichage oups
                    exit(0);
                    
                    
            }
             
        }
}
    