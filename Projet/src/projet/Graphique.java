/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

/**
 *
 * @author lele1
 */
import java.awt.*; 
import java.awt.event.*; 
import static java.lang.System.exit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import javax.swing.*; 
import org.jfree.chart.*; 
import org.jfree.chart.plot.*; 
import org.jfree.data.*; 
import org.jfree.data.general.DefaultPieDataset;

public class Graphique extends JFrame { 
  private JPanel pnl; 
       static Connection myConnection; 
       Statement statement,statement2;
       ResultSet resultat,resultat2;
       int attente, passe,tp,td,compte, total,poo,web,anglais;
       String groupe_titre,nom_salle;
       Label titre = new Label();
       Label titre1 = new Label();
       JTextArea tot;
       
  public Graphique(int id,int repport) throws SQLException { 
        this.tot = new JTextArea("");
    addWindowListener(new WindowAdapter() { 
      public void windowClosing(WindowEvent e) { 
        dispose(); 
        System.exit(0); 
      } 
    }); 
    pnl = new JPanel(new BorderLayout()); 
    setContentPane(pnl); 
    setSize(800, 600); 
     this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
   int x=0;
    this.setLocationRelativeTo(null);
    this.setResizable(false);
    //this.setLayout(null); 
JPanel panel = new JPanel(new GridLayout(0,2)); // 2 colonnes
    
    panel.add(titre);
    panel.add(titre1);
    JButton deco=new JButton("Retour");
    
    deco.addActionListener(new Graphique.DeconnexionListener() );
   
     Connection myConnection;
     myConnection=ConnexionBDD.init();
    // myConnection = init();
     
    if(repport==1)
    {
               System.out.println("coucou je suis là");
        
            
                statement = myConnection.createStatement();
                String sql = "SELECT * FROM cours WHERE 1";
                resultat = statement.executeQuery(sql);
                 
                  while (resultat.next()) {
                    DefaultPieDataset pieDataset = new DefaultPieDataset();    
                    String nom = resultat.getString("Nom_Cours");
                    
                   String sql1 = "SELECT * FROM cours co INNER JOIN seance se ON co.ID=se.ID_Cours INNER JOIN seance_groupe seg ON seg.ID_Seance=se.ID WHERE Nom_Cours='"+nom+"' AND ID_Groupe='"+id+"'";
                  statement2 = myConnection.createStatement();
                  int groupe = 0; 
                   resultat2 = statement2.executeQuery(sql1);
                   while (resultat2.next()) {
                       
                       groupe= resultat2.getInt("ID_Groupe");
                        String Etat = resultat2.getString("Etat");
                       if("passe".equals(Etat))
                       { passe++;}
                       else attente++;
                   
                   }
                   
                   System.out.println("passe = "+passe+ " Attente : "+attente );
                   groupe_titre=" Statistique du TD n° "+String.valueOf(groupe);
                  // titre = new JTextArea(" Statistique du TD n° "+String.valueOf(groupe));
                   
                   pieDataset.setValue(passe+" h Complétées ", passe); 
                   pieDataset.setValue(attente+ " h Restantes ",attente);  
                   int chiffre=passe + attente;
                   total+=chiffre;
                   JFreeChart pieChart = ChartFactory.createPieChart("Cours : "+nom+" : "+chiffre+" h" , 
                   pieDataset, true, false, false); 
                   
                   tot.setText("Total heures :"+total+" h");

                   ChartPanel cPanel = new ChartPanel(pieChart); 
                   cPanel.setBounds(10, 10+x ,60,60);
                   panel.add(cPanel); 
                   x+=30;
   
                }
    }
    
    else if(repport==3)
    {System.out.println("coucou salle");
     
                /*statement = myConnection.createStatement();
                String sql = "SELECT * FROM cours WHERE 1";
                resultat = statement.executeQuery(sql);
                 
                  while (resultat.next()) {
                    DefaultPieDataset pieDataset = new DefaultPieDataset();    
                    String nom = resultat.getString("Nom_Cours");*/
                  DefaultPieDataset pieDataset = new DefaultPieDataset();    
                  String sql1 = "SELECT * FROM cours co INNER JOIN seance se ON co.ID=se.ID_Cours INNER JOIN seance_salle seg ON seg.ID_Seance=se.ID INNER JOIN salle sa ON sa.ID=seg.ID_Salle WHERE ID_Salle='"+id+"'";
                  statement2 = myConnection.createStatement();
                  int groupe = 0; 
                   resultat2 = statement2.executeQuery(sql1);
                   while (resultat2.next()) {
                       
                       nom_salle= resultat2.getString("Nom_Salle");
                        String nomcours = resultat2.getString("Nom_Cours");
                       if("POO JAVA".equals(nomcours))
                       { poo++;}
                       if("WEB DYNAMIQUE".equals(nomcours))
                       { web++;}
                       if("ANGLAIS".equals(nomcours))
                       { anglais++;}
                   
                   }
                   
                   System.out.println("passe = "+passe+ " Attente : "+attente );
                   groupe_titre=" Statistique de la salle  ";
                  // titre = new JTextArea(" Statistique du TD n° "+String.valueOf(groupe));
                   
                   pieDataset.setValue(poo+" h JAVA ",poo); 
                   pieDataset.setValue(web+ " h WEB ",web); 
                   pieDataset.setValue(anglais+" h ANGLAIS ", anglais); 
                   
                   int chiffre=poo+anglais+web;
                   total+=chiffre;
                   JFreeChart pieChart = ChartFactory.createPieChart("Salle: "+nom_salle+" : "+chiffre+" h" , 
                   pieDataset, true, false, false); 
                   
                   tot.setText("Total heures :"+total+" h");

                   ChartPanel cPanel = new ChartPanel(pieChart); 
                   cPanel.setBounds(10, 10+x ,60,60);
                   panel.add(cPanel); 
                   x+=30;//}
    
    
    
    
    }
    
    else  if(repport==2)
    {System.out.println("coucou Enseignant");
    
     statement = myConnection.createStatement();
                String sql = "SELECT * FROM cours co INNER JOIN enseignant en ON co.ID=en.ID_Cours WHERE ID_Utilisateur ='"+id+"'";
                resultat = statement.executeQuery(sql);
                 
                  while (resultat.next()) {
                    DefaultPieDataset pieDataset = new DefaultPieDataset();    
                    String nom = resultat.getString("Nom_Cours");
                    //int id = resultat.getInt("ID");
                    
                    String sql1 = "SELECT * FROM cours co INNER JOIN enseignant en ON co.ID=en.ID_Cours INNER JOIN seance_enseignant see ON see.ID_Enseignant=en.ID_Utilisateur INNER JOIN seance se ON se.ID=see.ID_Seance WHERE ID_Utilisateur ='"+id+"' AND Nom_Cours ='"+nom+"'";
                   //String sql1 = "SELECT * FROM cours INNER JOIN seance ON cours.ID=seance.ID_Cours WHERE Nom_Cours='"+nom+"' ";
                  statement2 = myConnection.createStatement();
                   
                   resultat2 = statement2.executeQuery(sql1);
                   while (resultat2.next()) {
                        int ID = resultat2.getInt("ID_Type");
                       if(ID==1)
                       { tp++;}
                       else td++;
                   
                   }
                   total=tp+td;
                   System.out.println("TP= "+tp+ " TD: "+td );
                  groupe_titre="Vos type de cours ";
                   
                   pieDataset.setValue(tp+" h de TP ", tp); 
                   pieDataset.setValue(td+ " h de TD",td); 
                   int chiffre=tp + td;
                   
                   JFreeChart pieChart = ChartFactory.createPieChart("Cours : "+nom+" : "+chiffre+" h" , 
                   pieDataset, true, false, false); 
                   
                 

                   ChartPanel cPanel = new ChartPanel(pieChart); 
                   
                   panel.add(cPanel); 
    
                  }
    
    
    }
    deco.setSize(20, 50);
  deco.setBounds(300,10, 130, 30);
 panel.add(deco);  
   
getContentPane().add(panel);
panel.setName(groupe_titre);
 titre.setText(" "+groupe_titre+" :");
 titre1.setText("\n total " + total +" heures de cours ");
 
 titre.setBackground(Color.white);
 titre.setFont(new Font("Arial", Font.CENTER_BASELINE,33));
 titre1.setBackground(Color.white);
 titre1.setFont(new Font("Arial", Font.HANGING_BASELINE,26));
 
 
 
 


  } 

  
    class DeconnexionListener extends JPanel implements ActionListener{ //pour se déconnecter
         
            @Override
            public void actionPerformed(ActionEvent e) {
                    ActionEvent a = null;
                    System.out.print("FERME MOI CETTE FENETRE");//test affichage oups
                    dispose();
                    
                    
            }
             
        }
  
  
  
  
  
}