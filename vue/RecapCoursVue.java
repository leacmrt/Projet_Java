/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controlleur.AuthentificationControleur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.System.exit;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import modele.Authentification;
import modele.RecapCoursmodele;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import modele.RecapCoursmodele;
        

/**
 *
 * @author lele1
 */
public class RecapCoursVue {
    
    int ID_Utilisateur1;
    int EtatUT;
    
    public RecapCoursVue(int o) throws SQLException{
        System.out.println("coucou ça marche");
    }

    
    public RecapCoursVue (View aThis,int ID_Utilisateur,String droit,int etat) throws SQLException
{
    RecapCoursmodele rec = new RecapCoursmodele();
    Authentification connexion = new Authentification();
    RecapCoursVue.this.ID_Utilisateur1=ID_Utilisateur; 
    RecapCoursVue.this.EtatUT=etat;
    AuthentificationControleur control = new AuthentificationControleur();

    
    
    
    //module de recherche faire en sorte que ce soit que pour admin
    JButton recherche;
    recherche = new JButton("Recherche");
     JTextField recherche1 = new JTextField();
     
        if ((etat == 2)||(etat == 3)){
        //if("Non".equals(droit))
        recherche.setBounds(570, 10, 100, 20); 
        aThis.add(recherche);
        aThis.add(recherche1);
        recherche1.setBounds(420, 10, 150,20);
        
        
        
        recherche.addActionListener(new ActionListener() { 
        @Override    
        @SuppressWarnings("empty-statement")
                 
        public void actionPerformed(ActionEvent e) {
            
        ActionEvent a = null;
        String nomrecherche = recherche1.getText();
        String s ="e";
        try{
        int idrecherche = control.recherche3((String)nomrecherche);
        int etatrecherche = control.recherche4((String)nomrecherche);
        if ((idrecherche>=0)&&(etatrecherche>=0)){
            System.out.println(idrecherche+" "+etatrecherche);
            RecapCoursVue.this.ID_Utilisateur1=idrecherche;
            RecapCoursVue.this.EtatUT=etatrecherche;
            rec.chargecoursRecap(aThis,ID_Utilisateur1,EtatUT);      
        } else System.out.println("Non"+idrecherche+" "+etatrecherche);
        } catch (SQLException ex) {}
        }});
        
        
        
        
        
        
        
        
        }
        
        
    
        
        
        
        
     
    
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

        
        rec.chargecoursRecap(aThis,ID_Utilisateur,etat);
        
}

    
   
 public void affichage(JPanel la ,int nbr_cours,ArrayList<ArrayList<ArrayList<String>>> tab)
        {JLabel cours=new JLabel("cours");
                cours.setBounds(120, 60, 220, 40); 
                JLabel premier=new JLabel("premiere seance");
                premier.setBounds(320, 60, 420, 40); 
                JLabel dernier=new JLabel("derniere seance");
                dernier.setBounds(530, 60, 630, 40); 
                JLabel nbrcours=new JLabel("nombre de seances");
                nbrcours.setBounds(680, 60, 780, 40); 
                JLabel total=new JLabel("temps total");
                total.setBounds(850, 60, 950, 40); 
                
                
                la.add(cours);
                la.add(premier);
                la.add(dernier);
                la.add(nbrcours);
                la.add(total);
                
          for (int j = 0;j < nbr_cours;j++){
              
              
                JComboBox jcombo = new JComboBox();
                int size = tab.get(j).size();
                
                
                
                
                
                //Titre de la JcomboBox = Infos gÃ©nÃ©rales du cours
                String titre = tab.get(j).get(0).get(0)+"                                             "
                        +tab.get(j).get(size-4).get(j)+"                                             "
                        + tab.get(j).get(size-3).get(j)+"                                              "
                        +tab.get(j).get(size-1).get(j)+"                                            "
                        +tab.get(j).get(size-2).get(j)+" h";
                
                jcombo.addItem(titre);
                String info = new String();

               
                //Infos des diffÃ©rentes sÃ©ances
                for (int k = 1; k < size-4 ; k++){
                    
                    info = "            Date:  "+tab.get(j).get(k).get(2)+"                                                     DÃ©but:  "
                            +tab.get(j).get(k).get(0)+" h                                                          Fin:  "
                            +tab.get(j).get(k).get(1)+" h                                                          Duree:  "
                            +tab.get(j).get(k).get(3)+" h";
                    
                    jcombo.addItem(info);
                }

                
                
                jcombo.setEditable(false);
                jcombo.setEnabled(true);
                jcombo.setVisible(true);
                jcombo.setBounds(100, 100+(40*j), 850, 40);
                    
                la.add(jcombo);
                
                          JComboBox jcombo2 = new JComboBox();

        
          } }
          
  

     
   
    
    class DeconnexionListener extends JPanel implements ActionListener{ //pour se dÃ©connecter
         
            @Override
            public void actionPerformed(ActionEvent e) {
                    ActionEvent a = null;
                    System.out.print("FERME MOI CETTE FENETRE");//test affichage oups
                    exit(0);
                    
                    
            }
             
        }
}
    
