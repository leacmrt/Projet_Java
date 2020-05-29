/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import static java.lang.System.exit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static projet.ConnexionBDD.myConnection;

/**
 *
 * @author lele1
 */
class Modification extends JPanel implements ItemListener{
    int x=90;
     int y=110;
    public Modification(View aThis,int ID_Utilisateur,String droit,int etat)
    {
    JButton etu= new JButton("ETUDIANT");
    JButton co = new JButton("COURS ");
    JButton se=new JButton(" SEANCE ");
    JButton deco=new JButton("Deconnexion ");
     JCheckBox check1 = new JCheckBox("Nom");
     JCheckBox check2 = new JCheckBox("Type");
     
      JTextField ID = new JTextField("ID"); 
      JTextField Date = new JTextField(" Date"); 
      JTextField Jour= new JTextField(" Jour"); 
      JTextField Semaine = new JTextField(" Semaine"); 
      JTextField Heuredeb = new JTextField(" Heure de debut"); 
      JTextField HeureFin= new JTextField(" Heure de fin"); 
      JTextField Etat= new JTextField(" Etat"); 
      JTextField cours = new JTextField(" Cours"); 
      JTextField Groupe= new JTextField(" Groupe(s)");
      JTextField Prof= new JTextField(" Professeur ");
       JTextField Salle= new JTextField(" Salle");

    deco.addActionListener(new Modification.DeconnexionListener() );
    JComboBox choix = new JComboBox();
    choix.addItem("Ajouter");
    choix.addItem("Supprimer");
    choix.addItem("Modifier");//sois modifier type sois nom 
    
    
  
    choix.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
                    ActionEvent a = null;
                    String selected= (String)choix.getSelectedItem();
                    System.out.println("Selection : "+selected);
                    
                    if("Modifier".equals(selected))
            {
               x=0;
               y=0;
               repaint();
            
            }
                  else if("Ajouter".equals(selected))
            {
                x=90;
                y=110;
                //ID.setBounds(140,330, 90, 30); 
                Date.setBounds(300,330, x, 30);
                Jour.setBounds(420,330, x, 30);
                Semaine.setBounds(530,330, x, 30);
                Heuredeb.setBounds(650,330, y, 30);
                HeureFin.setBounds(790,330, x, 30);
                Etat.setBounds(300,430, x, 30);
                cours.setBounds(420,430, x, 30);
                Groupe.setBounds(530,430, x, 30);
                Prof.setBounds(650,430, x, 30);
                Salle.setBounds(790,430, x, 30);
                        try {
                            ajout(Date,Jour,Semaine,Heuredeb,HeureFin,Etat,cours,Groupe,Prof,Salle);
                            
                        } catch (SQLException ex) {
                            Logger.getLogger(Modification.class.getName()).log(Level.SEVERE, null, ex);
                        }
                  
            }
            else{ x=0;
               y=0;}
               
                    
        }


    });
    
    
    JComboBox choix1 = new JComboBox();
    choix1.addItem("Ajouter Enseignant");
    choix1.addItem("Supprimer");
    choix1.addItem("Modifier");
    
     choix1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
                    ActionEvent a = null;
                    String selected= (String)choix1.getSelectedItem();
                    System.out.println("Selection : "+selected);
                    
                    if("Modifier".equals(selected))
            {
                System.out.println("ON MODIFIE");
               check1.setBounds(300,220, 70, 20);
               check2.setBounds(400,220, 70, 20);
               
              
            
            }
        }});
   
    
    choix.setBounds(150,330, 130, 30);
    choix1.setBounds(150,220, 130, 30);
    deco.setBounds(900,10, 130, 30);
    etu.setBounds(10,50, 130, 30);
    co.setBounds(10,220, 130, 30);
    se.setBounds(10,330, 130, 30);
    
    aThis.add(ID);
    aThis.add(Date);
    aThis.add(Jour);
    aThis.add(Semaine);
    aThis.add(Heuredeb);
    aThis.add(HeureFin);
    aThis.add(Etat);
    aThis.add(cours);
    aThis.add(Groupe);
    aThis.add(Prof);
    aThis.add(Salle);
    aThis.add(deco);
    aThis.add(choix1);
    aThis.add(choix);
    aThis.add(etu);
    aThis.add(co);
    aThis.add(se);
    aThis.add(check1);
    aThis.add(check2);
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    class DeconnexionListener extends JPanel implements ActionListener{ //pour se déconnecter
         
            @Override
            public void actionPerformed(ActionEvent e) {
                    ActionEvent a = null;
                    System.out.print("FERME MOI CETTE FENETRE");//test affichage oups
                    repaint();
                    
                    exit(0);
                    
                    
            }
             
        }
    
     private void ajout(JTextField Date, JTextField Jour, JTextField Semaine, JTextField Heuredeb, JTextField HeureFin, JTextField Etat, JTextField cours, JTextField Groupe,JTextField Prof,JTextField Salle) throws SQLException {
             
             Connection myConnection;
             myConnection=ConnexionBDD.init();
             Statement statement;
             ResultSet resultat;
             
             statement = myConnection.createStatement();
             String sql = "INSERT INTO seance(`Date`, `Jour`, `Semaine`, `Heure_Debut`, `Heure_Fin`, `Etat`, `ID_Cours`, `ID_Type`) VALUES ('2020-07-18','6','4','8','10','passe','2','1')";
             String sql1="INSERT INTO seance_enseignant(`ID_Seance`, `ID_Enseignant`) VALUES ('7','2')";
             String sql2="INSERT INTO seance_groupe(`ID_Seance`, `ID_Groupe`) VALUES ('7','1')";
             String sql3="INSERT INTO seance_salle(`ID_Seance`, `ID_Salle`) VALUES ('7','3')";  
             int update = statement.executeUpdate(sql);
             int update1 = statement.executeUpdate(sql1);
             int update2 = statement.executeUpdate(sql2);
             int update3 = statement.executeUpdate(sql3);
             
        }
   
}
