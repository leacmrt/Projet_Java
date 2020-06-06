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
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.Border;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
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
    int mois, semainet,jour,annee;
    int nombre =0;
    
    
    public Edt(View aThis,int ID_Utilisateur,String droit,int etat) throws SQLException {
        essau(aThis,ID_Utilisateur,droit,etat);
    }

    public Edt() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    public void  essau (JPanel la,int ID_Utilisateur,String droit,int etat) throws SQLException
{
    
 
     SimpleDateFormat formater = null;

    Date aujourdhui = new Date(System.currentTimeMillis());
    Calendar cal = Calendar.getInstance(Locale.FRANCE);
     cal.setTime(aujourdhui);
 
    semaine=cal.get(Calendar.WEEK_OF_YEAR);
    formater = new SimpleDateFormat("dd-MM-yy");
    System.out.println(formater.format(aujourdhui));
control = new AuthentificationControleur();
    Authentification ici= new Authentification();
    int tmp_ID=ID_Utilisateur;
    String tmp_droit=droit;
    int tmp_etat=etat;
    if("Oui".equals(droit))
    {Edt.this.id_repport=1;}
    if("Non".equals(droit))
    {Edt.this.id_repport=2;}
     System.out.println("droit"+droit);
    System.out.println("id repport"+Edt.this.id_repport);
    JTextArea datt =new JTextArea("DATE : "+formater.format(aujourdhui)+"   SEMAINE "+semaine);
    
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
    liste1.setBounds(470, 5, 100, 20);
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
                 
                 control.chargecours(semaine,la,Edt.this.ID_Utilisateur1,Edt.this.EtatUT);
                 } else System.out.print("C'est pourtant égal à 0");
                
            } else  if("Salle".equals(selected)){ //ici recherche de salle 
                int nouveau = control.rechercheSalle((String)login2); 
               System.out.println("tu slect salle");
                 
                 if(nouveau!=0)//si on trouve une salle
                 { 
                     Edt.this.id_repport=3;
                     Edt.this.ID_Utilisateur1=nouveau;
                     Edt.this.EtatUT=2;
                     System.out.println("la salle existe");
                     control.chargecours(semaine, la,Edt.this.ID_Utilisateur1,2);
                    
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
                    control.chargecours(semaine,la,tmp_ID,tmp_etat);
                } catch (SQLException ex) {
                    Logger.getLogger(Edt.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(null,"Votre emploi du temps","Success",JOptionPane.PLAIN_MESSAGE);
                 
            }
        });
         
        int plus=20;
         
         control.chargecours(semaine,la,Edt.this.ID_Utilisateur1,Edt.this.EtatUT);
      JTextArea janvier = new JTextArea("       Janv.");
      JTextArea fevrier = new JTextArea("      Févr.");
      JTextArea mars = new JTextArea("      Mars");
      JTextArea avril = new JTextArea("        Avr.");
      JTextArea mai = new JTextArea("       Mai");
      JTextArea juin = new JTextArea("       Juin");
      JTextArea juillet = new JTextArea("       Juil.");
      JTextArea aout = new JTextArea("    Août");
      JTextArea septembre = new JTextArea("       Sept");
      JTextArea octobre = new JTextArea("        Oct.");
      JTextArea novembre = new JTextArea("    Nov.");
      JTextArea decembre= new JTextArea("        Dec.");
        javax.swing.border.Border border = BorderFactory.createLineBorder(Color.BLACK);
      
      aout.setBounds(19, 42,80, 18);
      aout.setBackground(Color.lightGray);
      aout.setBorder(border);
      la.add(aout);
      septembre.setBounds((int) 102, 42,80, 18);
      septembre.setBackground(Color.lightGray);
      septembre.setBorder(border);
      la.add(septembre);
      octobre.setBounds((int) 185, 42,80, 18);
      octobre.setBackground(Color.lightGray);
      octobre.setBorder(border);
      la.add(octobre);
      novembre.setBounds(268, 42,80, 18);
      novembre.setBackground(Color.lightGray);
      novembre.setBorder(border);
      la.add(novembre);
      decembre.setBounds((int) 351, 42,80, 18);
      decembre.setBackground(Color.lightGray);
      decembre.setBorder(border);
      la.add(decembre);
      janvier.setBounds((int) 434, 42,80, 18);
      janvier.setBackground(Color.lightGray);
      janvier.setBorder(border);
      la.add(janvier);
      fevrier.setBounds((int) 517, 42,80, 18);
      fevrier.setBackground(Color.lightGray);
      fevrier.setBorder(border);
      la.add(fevrier);
      mars.setBounds(600, 42,80, 18);
      mars.setBackground(Color.lightGray);
      mars.setBorder(border);
      la.add(mars);
      avril.setBounds((int) 683, 42,80, 18);
      avril.setBackground(Color.lightGray);
      avril.setBorder(border);
      la.add(avril);
      mai.setBounds((int) 766, 42,80, 18);
      mai.setBackground(Color.lightGray);
      mai.setBorder(border);
      la.add(mai);
      juin.setBounds((int) 849, 42,80, 18);
      juin.setBackground(Color.lightGray);
      juin.setBorder(border);
      la.add(juin);
      juillet.setBounds((int) 932, 42,80, 18);
      juillet.setBackground(Color.lightGray);
      juillet.setBorder(border);
      la.add(juillet);
      
         
        for(int h =1;h<54;h++)
        {   final int labelText; 
            int hu = 1;  
            if(h>=23)
            { hu =h-22;}
            
            else if(h<23)
            { hu=h+30;  }
        String text = Integer.toString(hu); 
        labelText=hu;
        JButton jour2 = new JButton(text);
       
        jour2.setFont(new Font("Arial", Font.PLAIN, 10)); 
        jour2.setBounds(plus, 60, 20, 20); 
        jour2.setMargin(new Insets(0, 0, 0, 0));
        la.add(jour2);
        
        jour2.addActionListener(new ActionListener() {
  @Override
                public void actionPerformed(ActionEvent ae) {
try {
                //int ju=hu;
                control.chargecours(labelText,la,Edt.this.ID_Utilisateur1,Edt.this.EtatUT);
            } catch (SQLException ex) {
                Logger.getLogger(Edt.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.print("t'as cliqué sur "+labelText);
            System.out.print("etat l:"+Edt.this.EtatUT);

                }   
            });
        
        plus+=19;
        
        
        }
        
        
        report.setBorderPainted(true);
        report.setBackground(Color.cyan);
        report.setFont(new Font("Arial", Font.CENTER_BASELINE,15));
        report.setBounds(430,690, 120, 40); 
        btnCration.setBounds(10, 10, 150, 20); 
        la.add(btnCration);
        la.add(report);
        datt.setBounds(650, 15, 230, 20);
        datt.setBackground(new Color(128,188,216));
        datt.setFont((new Font("Arial", Font.BOLD,15)));
        
        deco.setBounds(900,10, 130, 27);
        la.add(datt);
        la.add(deco);
        //System.out.println(droit);
        
        
        if ((etat == 2)||(etat == 3)){
        la.add(liste1);
        //if("Non".equals(droit))
        {recherche.setBounds(470, 25 , 100, 20); 
        la.add(recherche);
        
        la.add(recherche1);
        recherche1.setBounds(320,10, 150,20);
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

