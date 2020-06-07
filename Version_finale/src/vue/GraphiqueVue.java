
package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author lele1
 */
public class GraphiqueVue extends JFrame{
  private JPanel pnl; 
  static Connection myConnection; 
  Statement statement,statement2,statement3;
  ResultSet resultat,resultat2,resultat3;
   int total =0;
   String groupe_titre;  
       Label titre = new Label();
       Label titre1 = new Label();
       JTextArea tot; 
       JButton deco;
       DefaultPieDataset pieDataset = new DefaultPieDataset();
   JPanel panel = new JPanel(new GridLayout(0,2)); // 2 colonnes  
   
  
    
      public GraphiqueVue(int id,int repport) throws SQLException { 
        
      }

    public GraphiqueVue() {
     this.tot = new JTextArea("");
    addWindowListener(new WindowAdapter() { 
      public void windowClosing(WindowEvent e) { 
        dispose(); 
        System.exit(0); 
      } 
    }); 
    pnl = new JPanel(new BorderLayout()); 
    pnl.setBackground(Color.white);
    setContentPane(pnl); 
    setSize(800, 700); 
     this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
   int x=0;
    this.setLocationRelativeTo(null);
    this.setResizable(false);
    this.setLayout(null); 
    //JPanel panel = new JPanel(new GridLayout(0,2)); // 2 colonnes
   
    panel.add(titre);
    panel.add(titre1);
    //ImageIcon water = new ImageIcon("fleche.png");
    
    deco=new JButton("RETOUR");
    deco.setMargin(new Insets(0, 0, 0, 0));
    deco.setPreferredSize(new Dimension(60,60));
    deco.addActionListener(new GraphiqueVue.DeconnexionListener() );
    }

    public void afficheetudiant( ArrayList <Integer> passe,  ArrayList <Integer>   attente,ArrayList<String> nom,int groupe) throws SQLException {
       GraphiqueVue la = new GraphiqueVue();
      
    for(int u=0;u<(nom.size());u++)
    {
       DefaultPieDataset pieDataset1 = new DefaultPieDataset();
        System.out.println(nom.size());
       pieDataset1.setValue(passe.get(u)+" h Complétées ", passe.get(u));
       pieDataset1.setValue(attente.get(u)+ " h Restantes ", attente.get(u));
       int chiffre=passe.get(u) + attente.get(u);
       total+=chiffre;
       JFreeChart pieChart = ChartFactory.createPieChart("Cours : "+nom.get(u)+" : "+chiffre+" h" ,
       pieDataset1, true, false, false);
       //tot.setText("Total heures :"+total+" h");
        ChartPanel cPanel = new ChartPanel(pieChart);
        panel.add(cPanel);
       System.out.println("passe = "+passe.get(u)+ " Attente : "+attente.get(u) );
       groupe_titre=" Statistique du TD n° "+String.valueOf(groupe);               
    }
    affichefin(panel);

    
    
    }
    
    
    public void affichesalle(String nom_salle,int poo,int web,int anglais,ArrayList<String> noms, ArrayList<Integer> cap)
    { GraphiqueVue jojo = new GraphiqueVue();
     DefaultPieDataset pieDataset = new DefaultPieDataset();
    // System.out.println("passe = "+passe+ " Attente : "+attente );
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
              
              DefaultCategoryDataset dataset = new DefaultCategoryDataset();
              for(int j=0;j<noms.size();j++)
              {dataset.addValue(cap.get(j),"Nombre Eleve ", noms.get(j));
              }
              JFreeChart barChart = ChartFactory.createBarChart("Site E1", "",
                      "Capacité", dataset, PlotOrientation.VERTICAL, true, true, false);
              ChartPanel cPanel1 = new ChartPanel(barChart);
              panel.add(cPanel1);
              //cPanel.setBounds(10, 10+x ,60,60);
              panel.add(cPanel);
              affichefin(panel);
    }
    
    public void afficheenseignant(ArrayList<String> nom,ArrayList<Integer> tp,ArrayList<Integer>td)
    {
        GraphiqueVue jojo = new GraphiqueVue();
     DefaultPieDataset pieDataset1 = new DefaultPieDataset();
      for(int u=0;u<(nom.size());u++)
    {
     //System.out.println("TP= "+tp+ " TD: "+td );
                      groupe_titre="Vos type de cours ";
                      
                      pieDataset1.setValue(tp.get(u)+" h de TP ", tp.get(u));
                      pieDataset1.setValue(td.get(u)+ " h de TD",td.get(u));
                      int chiffre1=tp.get(u) + td.get(u);
                      
                      JFreeChart pieChart2 = ChartFactory.createPieChart("Cours : "+nom.get(u)+" : "+chiffre1+" h" ,
                              pieDataset1, true, false, false);
                      
                      
                      
                      ChartPanel cPanelo = new ChartPanel(pieChart2);
                      
                      panel.add(cPanelo);}
    affichefin(panel);
    }
    
      public void affichefin(JPanel panel)
      {getContentPane().add(panel);
    getContentPane().add(deco);

panel.setName(groupe_titre);
 titre.setText(" "+groupe_titre+" :");
 titre1.setText("\n total " + total +" heures de cours ");
 
 titre.setBackground(Color.white);
 titre.setFont(new Font("Arial", Font.CENTER_BASELINE,33));
 titre1.setBackground(Color.white);
 titre1.setFont(new Font("Arial", Font.HANGING_BASELINE,26));
 panel.setBounds(0,30,750, 600);
 deco.setBounds(350,0,80,35);
 deco.setBackground(Color.red);
 //pnl.add(deco);
 
 this.setVisible(true);}
       class DeconnexionListener extends JPanel implements ActionListener{ //pour se déconnecter
         
            @Override
            public void actionPerformed(ActionEvent e) {
                    ActionEvent a = null;
                    System.out.print("FERME MOI CETTE FENETRE");//test affichage oups
                    dispose();
                    
                    
            }
             
        }
  
  
  
  
}
