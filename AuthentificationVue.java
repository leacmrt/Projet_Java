/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controlleur.AuthentificationControleur;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.System.exit;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import modele.Authentification;

/**
 *
 * @author lele1
 */
public class AuthentificationVue extends JFrame implements Observer{
     
    JLabel login,mdp;
    JTextField login1,mdp1;
    JButton valider,annuler;
    String Login, motdepasse;
    AuthentificationControleur op;
    
         
    public AuthentificationVue() throws SQLException{
         
        super();
        this.op = new AuthentificationControleur(0);
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
        annuler = new JButton(" Quitter");
         
         
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
        annuler.addActionListener(new AnnulerListener());
        //op.addObserver(this); 
        
        this.setVisible(true);
      
     
    }

    @Override
    public void update(Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
                   
                    
                    
                try {
                    //logger.login1(login1.getText(), mdp1.getText());
                    op.verification(AuthentificationVue.this,login2,password);
                } catch (SQLException ex) {
                    Logger.getLogger(AuthentificationVue.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
            }

          
        }
        
        
        class AnnulerListener extends JFrame implements ActionListener{
         
            @Override
            public void actionPerformed(ActionEvent e) {
                    ActionEvent a = null;
                    System.out.print("FERME MOI CETTE FENETRE");//test affichage oups
                    exit(0);
                    
                    
            }
             
        }
        
        
      
        
}

