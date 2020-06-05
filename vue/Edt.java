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
import controlleur.AuthentificationControleur;
import java.awt.Color;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import modele.Authentification;
import modele.Graphique;

/**
 *
 * @author lele1
 */
public class Edt {
    int semaine=1;
    int ID_Utilisateur1;
    int EtatUT;
    int id_repport=0;
    JTextField recherche1;
    Statement statement;
    ResultSet resultat,resultat1,resultat2;
    private JComboBox liste1;
    AuthentificationControleur control;
    
    
    public Edt(View aThis,int ID_Utilisateur,String droit,int etat) throws SQLException {
        essau(aThis,ID_Utilisateur,droit,etat);
    }

    public Edt() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    public void  essau (JPanel la,int ID_Utilisateur,String droit,int etat) throws SQLException
{
control = new AuthentificationControleur();
    Authentification ici= new Authentification();
    int tmp_ID=ID_Utilisateur;
    String tmp_droit=droit;
    int tmp_etat=etat;
    if("1".equals(droit))
    {Edt.this.id_repport=1;}
    if("2".equals(droit))
    {Edt.this.id_repport=2;}
    
    
    JButton recherche;
    JButton btnCration = new JButton("Emploi du temps ");
    JButton deco=new JButton("Deconnexion ");
    JButton report= new JButton("Repporting ");
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
                {
                
                 int nouveau = control.recherche((String)login2);
                 int nouveau1 = control.recherche1((String)login2);
                 System.out.println("nouveau = "+nouveau1);
                 if(nouveau!=0)//si on trouve quelqu'un
                 {
                 Edt.this.ID_Utilisateur1=nouveau;
                 Edt.this.EtatUT=nouveau1;
                 
                 if(nouveau1==1)
                 {Edt.this.id_repport=1;}
                 if(nouveau1==0)
                 {Edt.this.id_repport=2;}
                 
                 control.chargecours(1,la,Edt.this.ID_Utilisateur1,Edt.this.EtatUT);
                 } else System.out.print("C'est pourtant égal à 0");
                
            } else  if("Salle".equals(selected)){ //ici recherche de salle 
                int nouveau = control.rechercheSalle((String)login2); 
               
                 
                 if(nouveau!=0)//si on trouve une salle
                 { 
                      Edt.this.id_repport=3;
                     Edt.this.ID_Utilisateur1=nouveau;
                     System.out.println("la salle existe");
                     control.chargecours(semaine, la,nouveau,2);
                    
                 }
                }
            
           } catch (SQLException ex) {
               Logger.getLogger(Edt.class.getName()).log(Level.SEVERE, null, ex);
            }
                   
                   
           }

          
      });
    
    
        report.addActionListener(new ActionListener() {
         @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("REPPORTING ");
                control.graphique(Edt.this.ID_Utilisateur1,id_repport);
                
                   // ici.repporting();
                    
                 
                 
            }
        });
    
        btnCration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Affichage emploi du temps ");
                try {
                    control.chargecours(1,la,tmp_ID,tmp_etat);
                } catch (SQLException ex) {
                    Logger.getLogger(Edt.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(null,"Votre emploi du temps","Success",JOptionPane.PLAIN_MESSAGE);
                 
            }
        });
         
        int plus=20;
         control.chargecours(1,la,Edt.this.ID_Utilisateur1,Edt.this.EtatUT);
        
        for(int h =1;h<51;h++)
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
                    control.chargecours(nombre,la,Edt.this.ID_Utilisateur1,Edt.this.EtatUT);
                } catch (SQLException ex) {
                    Logger.getLogger(Edt.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.print("t'as cliqué sur "+nombre);
                 
                 
            }
            
           
        });
        
        plus+=20;
        
        }
        
        
        report.setBorderPainted(true);
        report.setBackground(Color.cyan);
        report.setFont(new Font("Arial", Font.CENTER_BASELINE,15));
        report.setBounds(430,690, 120, 40); 
        btnCration.setBounds(10, 10, 150, 20); 
        la.add(btnCration);
        la.add(report);
        deco.setBounds(900,10, 130, 30);
        la.add(deco);
        //System.out.println(droit);
        
        
        if ((etat == 2)||(etat == 3)){
        la.add(liste1);
        //if("Non".equals(droit))
        {recherche.setBounds(470, 30, 100, 20); 
        la.add(recherche);
        
        la.add(recherche1);
        recherche1.setBounds(320, 10, 150,20);
        }}
       
          la.setVisible(true);
        
}
   
    public void tableau(JTable table, JPanel la,Object[][] data)
     {
        // System.out.println("DATA :");
          
         int i = 0;
               JLabel h1=new JLabel("8h30");
               h1.setBounds(20, 100, 50, 20); 
               la.add(h1);
               JLabel h2=new JLabel("10h");
               h2.setBounds(26, 165, 50, 20); 
               la.add(h2);
               JLabel h8=new JLabel("10h15");
               h8.setBounds(15, 195, 50, 20); 
               la.add(h8);
               JLabel h3=new JLabel("11h45");
               h3.setBounds(15, 265, 50, 20); 
               la.add(h3);
               
               JLabel h12=new JLabel("12h");
               h12.setBounds(26, 295, 50, 20); 
               la.add(h12);
               
               JLabel h10=new JLabel("13h30");
               h10.setBounds(15, 365, 50, 20); 
               la.add(h10);
               
               JLabel h4=new JLabel("13h45");
               h4.setBounds(15, 395, 50, 20); 
               la.add(h4);
               
               JLabel h5=new JLabel("15h15");
               h5.setBounds(15, 465, 50, 20); 
               la.add(h5);
               
               JLabel h6=new JLabel("15h30");
               h6.setBounds(15, 500, 50, 20); 
               la.add(h6);
               
               JLabel h11=new JLabel("17h");
               h11.setBounds(26, 565, 50, 20); 
               la.add(h11);
               
               JLabel h13=new JLabel("17h15");
               h13.setBounds(15, 595, 50, 20); 
               la.add(h13);
               
               JLabel h7=new JLabel("18h45");
               h7.setBounds(15, 660, 50, 20); 
               la.add(h7);
               
               
          for(int u=0;u<11;u++)
          {if(u==1||u==3||u==5||u==7||u==9){
              table.setRowHeight(u,15);
          }
          else table.setRowHeight(u,84);
          }
          
          //table.setColumnSelectionAllowed(true);
          table.setShowGrid(true);
          table.setShowVerticalLines(true);
         
          JScrollPane pane = new JScrollPane(table);
          pane.setBounds(50,80,970,600);
          la.add(pane);                   }
    
     
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

