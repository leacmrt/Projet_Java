/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controlleur.AuthentificationControleur;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.System.exit;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import modele.Authentification;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import modele.RecapCoursmodele;
        

/**
 *
 * @author lele1
 */
public class RecapCoursVue {
    
    int ID_Utilisateur1;
    int EtatUT;
    int taillemax;
    
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
        
        
        
        recherche.addActionListener((ActionEvent e) -> {
            ActionEvent a = null;
            String nomrecherche = recherche1.getText();
            String s ="e";
            try{
                int idrecherche = control.recherche3((String)nomrecherche);
                int etatrecherche = control.recherche4((String)nomrecherche);
                if ((idrecherche>=0)&&(etatrecherche>=0)){
                    aThis.updateUI();
                    
                    System.out.println(idrecherche+" HEHO "+etatrecherche);
                    RecapCoursVue.this.ID_Utilisateur1=idrecherche;
                    RecapCoursVue.this.EtatUT=etatrecherche;
                    //System.out.println(RecapCoursVue.this.ID_Utilisateur1+" lololl");
                    rec.chargecoursRecap(aThis, RecapCoursVue.this.ID_Utilisateur1, RecapCoursVue.this.EtatUT,0);
                    
                } else System.out.println("Non"+idrecherche+" "+etatrecherche);
            } catch (SQLException ex) {}
        });
        
    }else{
        
        rec.chargecoursRecap(aThis,ID_Utilisateur,etat,1);  }
    
    //chargecoursRecap(JPanel la,int ID_utli1,int etat)
    
    JButton btnCration = new JButton("Recaputatif des cours ");
    
    
    JButton deco=new JButton("Deconnexion ");
    deco.addActionListener(new DeconnexionListener() );
        btnCration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Affichage du recapitulatif des cours ");
                 aThis.repaint();
                 
            }
        });
        
        
        btnCration.setBounds(50, 10, 200, 20);
        deco.setBounds(800, 10, 150, 20);

        
        aThis.add(btnCration);
        aThis.add(deco);      
        
}

    
   
 public void affichage(JPanel la ,int nbr_cours,ArrayList<ArrayList<ArrayList<String>>> tab,int recherche)
        {     
            la.updateUI();
            
            la.repaint(-1);
            
            JComboBox jcombo = null;
            
            
           for (int u=0;u<tab.size();u++)
           { System.out.print(tab.get(u));}
           
               
            JLabel cours=new JLabel("cours");
                cours.setBounds(120, 60, 220, 40); 
                JLabel premier=new JLabel("premiere seance");
                premier.setBounds(320, 60, 420, 40); 
                JLabel dernier=new JLabel("derniere seance");
                dernier.setBounds(530, 60, 630, 40); 
                JLabel nbrcours=new JLabel("nombre de seances");
                nbrcours.setBounds(680, 60, 780, 40); 
                JLabel total=new JLabel("temps total");
                total.setBounds(850, 60, 950, 40); 
                System.out.print("t'as trouvé des cours?");
                
                la.add(cours);
                la.add(premier);
                la.add(dernier);
                la.add(nbrcours);
                la.add(total);
                
                
          for (int j = 0;j < nbr_cours;j++){
                 
              System.out.println("on rentre dedans");
              
             
                int size = tab.get(j).size();
                DefaultComboBoxModel model = new DefaultComboBoxModel();
                jcombo = new JComboBox();
                
                //Titre de la JcomboBox = Infos gÃ©nÃ©rales du cours
                String titre = tab.get(j).get(0).get(0)+"                                             "
                        +tab.get(j).get(size-4).get(j)+"                                             "
                        + tab.get(j).get(size-3).get(j)+"                                              "
                        +tab.get(j).get(size-1).get(j)+"                                            "
                        +tab.get(j).get(size-2).get(j)+" h";
                
               jcombo.addItem(titre);
                
               //model.addElement(titre);
               
                String info = new String();

               
                //Infos des diffÃ©rentes sÃ©ances
                for (int k = 1; k < size-4 ; k++){
                    
                    info = "            Date:  "+tab.get(j).get(k).get(2)+"                                                     DÃ©but:  "
                            +tab.get(j).get(k).get(0)+" h                                                          Fin:  "
                            +tab.get(j).get(k).get(1)+" h                                                          Duree:  "
                            +tab.get(j).get(k).get(3)+" h";
                    
                    jcombo.addItem(info);
                    //model.addElement(info);
                }

                //settaillemax(jcombo.getComponentCount());
                //System.out.println("taille max:"+gettaillemax());
                jcombo.setEditable(true);
               
                jcombo.setEnabled(true);
                jcombo.setVisible(true);
                
                //jcombo.setModel(model);
               // jcombo.validate();
                jcombo.setBounds(100, 100+(40*j), 850, 40);
                la.add(jcombo);
                
                
                
                          JComboBox jcombo2 = new JComboBox();

        
          }
          if(recherche==0)//si on fait une recherche , adapter l'affichage
          {   
          if(!tab.isEmpty())
          {
         System.out.println("taille : "+tab.size()+" - "+jcombo.getComponentCount());
                if(jcombo.getComponentCount()>tab.size())
                    for(int o=4;o>=tab.size();o--)
          { System.out.println("probleme de taille");
          jcombo = new JComboBox();
          JComboBox jcombo2 = new JComboBox();
          String bonjour ="   ";
          jcombo2.addItem(bonjour);
            jcombo.addItem(bonjour);
            jcombo.setEditable(true);
            jcombo.setEnabled(true);
            
            jcombo.setVisible(true);
            jcombo.setBounds(100, 100+(40*(o)), 850, 40);
            la.add(jcombo);
           
          }}
          
          if(tab.isEmpty())
          {
         System.out.println("cest vide"+gettaillemax());
            for(int j=0;j<5;j++)   
          { System.out.println("probleme de taille");
          jcombo = new JComboBox();
          String bonjour ="   ";
            jcombo.addItem(bonjour);
             jcombo.setEditable(true);
            jcombo.setEnabled(true);
            jcombo.setVisible(true);
            jcombo.setBounds(100, 100+(40*j), 850, 40);
            la.add(jcombo);
          }}
          
          }
          
         
       }
          
  

     
   
    
    class DeconnexionListener extends JPanel implements ActionListener{ //pour se dÃ©connecter
         
            @Override
            public void actionPerformed(ActionEvent e) {
                    ActionEvent a = null;
                    System.out.print("FERME MOI CETTE FENETRE");//test affichage oups
                    exit(0);
                    
                    
            }
             
        }
    
   public void settaillemax(int newh)
   { RecapCoursVue.this.taillemax=newh;
   }
   
   public int gettaillemax()
   {  return RecapCoursVue.this.taillemax;
   }
}
    
