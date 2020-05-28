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
import javax.swing.JOptionPane;
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
    int ID_Utilisateur1;
    int EtatUT;
    JTextField recherche1;
    Statement statement;
    ResultSet resultat,resultat1,resultat2;
    private JComboBox liste1;
    
    
    public Edt(View aThis,int ID_Utilisateur,String droit,int etat) throws SQLException {
        essau(aThis,ID_Utilisateur,droit,etat);
    }
   
    public void  essau (JPanel la,int ID_Utilisateur,String droit,int etat) throws SQLException
{
    ConnexionBDD ici= new ConnexionBDD();
    int tmp_ID=ID_Utilisateur;
    String tmp_droit=droit;
    int tmp_etat=etat;
    
    JButton recherche;
    JButton btnCration = new JButton("Emploi du temps ");
    JButton deco=new JButton("Deconnexion ");
    //System.out.println("ID 0 : "+ID_Utilisateur);
    Edt.this.ID_Utilisateur1=ID_Utilisateur; 
    Edt.this.EtatUT=etat;
    
    recherche = new JButton("Recherche");//module de recherche faire en sorte que ce soit que pour un enseignant 
    recherche1 = new JTextField();
    System.out.print(recherche1.getText());
  
    Object[] elements = new Object[]{"Utilisateur", "Salle"};
    JComboBox liste1 = new JComboBox(elements);
    liste1.setSelectedItem("Utilisateur");
    liste1.setBounds(470, 10, 100, 20);
    deco.addActionListener(new Edt.DeconnexionListener() );
    
       
    recherche.addActionListener(new ActionListener() {
    
        @Override
        @SuppressWarnings("empty-statement")
        public void actionPerformed(ActionEvent e) {
                    ActionEvent a = null;
                    String selected= (String)liste1.getSelectedItem();
                    System.out.println("Selection : "+selected); 
                    
                   String login2=recherche1.getText();
                    System.out.println("Recherche dans BDD : " + login2);
            try {
                if("Utilisateur".equals(selected))
                {int nouveau = ici.recherche((String)login2);
                 int nouveau1 = ici.recherche1((String)login2);
                 System.out.println("nouveau = "+nouveau1);
                 if(nouveau!=0)//si on trouve quelqu'un
                 {
                 Edt.this.ID_Utilisateur1=nouveau;
                 Edt.this.EtatUT=nouveau1;
                 ici.chargecours(1,la,Edt.this.ID_Utilisateur1,Edt.this.EtatUT);
                 } else System.out.print("C'est pourtant égal à 0");
                
            } else  if("Salle".equals(selected)){ //ici recherche de salle 
                int nouveau = ici.rechercheSalle((String)login2); 
               
                 
                 if(nouveau!=0)//si on trouve une salle
                 {  System.out.println("la salle existe");
                    ici.chargecours(semaine, la,nouveau,2);
                 }
                }
            
            } catch (SQLException ex) {
                Logger.getLogger(Edt.class.getName()).log(Level.SEVERE, null, ex);
            }
                   
                   
            }

          
        });
    
        btnCration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Affichage emploi du temps ");
                try {  
                    ici.chargecours(1,la,tmp_ID,tmp_etat);
                     JOptionPane.showMessageDialog(null,"Votre emploi du temps","Success",JOptionPane.PLAIN_MESSAGE);
                } catch (SQLException ex) {
                    Logger.getLogger(Edt.class.getName()).log(Level.SEVERE, null, ex);
                }
                 
            }
        });
        
        
         
        int plus=20;
        ici.chargecours(1,la,Edt.this.ID_Utilisateur1,Edt.this.EtatUT);
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
                    ici.chargecours(nombre,la,Edt.this.ID_Utilisateur1,Edt.this.EtatUT);
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
        //System.out.println(droit);
        
        la.add(liste1);
        //if("Non".equals(droit))
        {recherche.setBounds(470, 30, 100, 20); 
        la.add(recherche);
        

        
        
        la.add(recherche1);
        recherche1.setBounds(320, 10, 150,20);}
       
       
          la.setVisible(true);
        
}
   
     
    class DeconnexionListener extends JPanel implements ActionListener{ //pour se déconnecter
         
            @Override
            public void actionPerformed(ActionEvent e) {
                    ActionEvent a = null;
                    System.out.print("FERME MOI CETTE FENETRE");//test affichage oups
                    exit(0);
                    
                    
            }
             
        }
    
    
    public JComboBox getListe1(){ //pour récuppérer l'action de la liste 
		return liste1;
	}
}

