/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

/**
 *
 * @author lele1
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import static java.lang.System.exit;
import java.sql.SQLException;
import javax.swing.JPanel;

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
public View(Onglet ong, Color color, int ID_Utilisateur,String droit,int etat) throws SQLException, IOException
{    
    this.setLayout(null);
    this.color = color;
     this.message = "Contenu du panneau N°" + (++COUNT);
    
    if (COUNT==1)
    {this.message=" ";
    edt= new Edt(this,ID_Utilisateur,droit,etat);
     
    this.repaint();
    
    }
        
    if (COUNT==2)
    {
   this.message=" "; 
        RecapCoursVue recapcours = new RecapCoursVue(this,ID_Utilisateur,droit,etat);
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


}

 
    class DeconnexionListener extends JPanel implements ActionListener{ //pour se déconnecter
         
            @Override
            public void actionPerformed(ActionEvent e) {
                    ActionEvent a = null;
                    System.out.print("FERME MOI CETTE FENETRE");//test affichage oups
                    exit(0);
                    
                    
            }
             
        }
       

 