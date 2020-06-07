/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.System.exit;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author lele1
 */
public class View extends JPanel {
    
    private JPanel contentPane,contentPane1;
    private Color color = Color.black;
    private static int COUNT = 0;
    private String message = "";
    Edt edt;
    Modification modification;
   
  public View(){}

  
    /**
     * Launch the application.
     * @param ong
     * @param color
     */
public View(Onglet ong, Color color, int ID_Utilisateur,String droit,int etat) throws SQLException
{    
    this.setLayout(null);
    this.color = color;
     this.message = "Contenu du panneau N°" + (++COUNT);
    
    if (COUNT==1)
    {this.message=" ";
    edt= new Edt(this,ID_Utilisateur,droit,etat);
     
    this.repaint();
    
    }
        
    if (COUNT==2)//on va ameliorer ça plus tard
    {this.message=" "; 
        RecapCours recapcours = new RecapCours(this,ID_Utilisateur,droit,etat);
        this.repaint();
    }
         
    if (COUNT==3)
    {this.message=" ICI SERONT LES MODIFICATIONS FAIT PAR L'ADMINISTRATEUR "; 
     modification = new Modification(this,ID_Utilisateur,droit,etat);
     this.repaint();
    }

    
  
    }

    @Override
    public void paintComponent(Graphics g){
    g.setColor(this.color);
    g.fillRect(0, 0, this.getWidth(), this.getHeight());
    g.setColor(Color.white);
    g.setFont(new Font("Arial", Font.BOLD, 15));
    g.drawString(this.message, 40, 20);
  }




 

public void  essau1 (JPanel la)
{
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
        
        la.add(btnCration);
        la.add(deco);
        
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
 
