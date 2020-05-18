/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
/**
 *
 * @author lele1
 */
public class Authentification extends JFrame {
     
    JLabel login,mdp;
    JTextField login1,mdp1;
    //JPasswordField mdp1;
    JButton valider,annuler;
    String Login, motdepasse;
    static  Logger logger; 
         
    public Authentification(){
         
        super();
        this.setTitle(" CONNEXION WEBAPPS ");
        this.setSize(new Dimension(400,200));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
         
         
        login = new JLabel("Login");
        login1 = new JTextField();
        System.out.print(login1.getText());
        
        mdp = new JLabel("Mot de Passe");
        mdp1 = new JPasswordField();
         
        valider = new JButton("Valider ");
        annuler = new JButton(" Annuler");
         
         
        Container contenu = this.getContentPane();
        contenu.setLayout(null);
         
        contenu.add(login);
        login.setBounds(20, 20, 100, 20);
         
        contenu.add(login1);
        login1.setBounds(150, 20, 150, 20);
         
        contenu.add(mdp);
        mdp.setBounds(22, 55, 100, 20);
         
        contenu.add(mdp1);
        mdp1.setBounds(150, 55, 150, 20);
         
        contenu.add(valider);
        valider.setBounds(125,100 ,77 ,20 );
         
        contenu.add(annuler);
        annuler.setBounds(225, 100, 82, 20);
         Login = login1.getText();
         motdepasse = mdp.getText();
        valider.addActionListener(new ValiderListener());  
         
        this.setVisible(true);
     
    }
         

 
        class ValiderListener extends JFrame implements ActionListener{
         
            @Override
            public void actionPerformed(ActionEvent e) {
                    ActionEvent a = null;
                    String login2=login1.getText();
                    String password=mdp1.getText();
                    System.out.println("Bonjour");
                    System.out.println("login=" + login2);
                    System.out.println("password=" + password);
                    
                    
                    
                    //logger.login1(login1.getText(), mdp1.getText());
                    ConnexionBDD ta;
                try {
                    ta = new ConnexionBDD();
                    ta.verification(login2,password);
                } catch (SQLException ex) {
                    Logger.getLogger(Authentification.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
            }
             
        }
        
      
        
}
