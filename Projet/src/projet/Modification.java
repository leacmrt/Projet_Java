/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import static java.lang.Integer.parseInt;
import static java.lang.System.exit;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
     String jour1,cours1,salle1,enseignant1,Etat1,groupe1;
     int id_prof,id_cours,id_groupe,id_salle;
     
     
     private JTextField Date = new JTextField(" Date"); 
     private JTextField Semaine = new JTextField(" Semaine"); 
     private JTextField Heuredeb = new JTextField(" Heure de debut"); 
     private JTextField HeureFin= new JTextField(" Heure de fin");
     
    public Modification()
    {}
     
     
    public Modification(View aThis,int ID_Utilisateur,String droit,int etat) throws SQLException
    {
    JButton etu= new JButton("ETUDIANT");
    JButton co = new JButton("COURS ");
    JButton se=new JButton(" SEANCE ");
    JButton ajout= new JButton("AJOUTER");
    JButton deco=new JButton("Deconnexion ");
    JCheckBox check1 = new JCheckBox("Nom");
    JCheckBox check2 = new JCheckBox("Type");
     
      //JTextField ID = new JTextField("ID"); 
      
     

    Cours recu = new Cours();   
    ArrayList<String> nomcours= recu.gettoutlesnoms();
    JComboBox choixcours = new JComboBox();
    
    for(int y=0;y<nomcours.size();y++)
    {
        choixcours.addItem(nomcours.get(y));
    }
    
    Groupe recuper = new Groupe();   
    ArrayList<String> nomgroupe= recuper.gettoutlesnoms();
    JComboBox choixgroupe = new JComboBox();
    
    for(int y=0;y<nomgroupe.size();y++)
    {
        choixgroupe.addItem(nomgroupe.get(y));
    }
    
    
    Enseignant recup1 = new Enseignant();   
    ArrayList<String> nomenseignant= recup1.gettoutlesnoms();
    JComboBox choixenseignant= new JComboBox();
    
    for(int y=0;y<nomenseignant.size();y++)
    {
        choixenseignant.addItem(nomenseignant.get(y));
    }
    
    
    Salle recup = new Salle();   
    ArrayList<String> nomsalle= recup.gettoutlesnoms();
    JComboBox choixsalle = new JComboBox();
    
    for(int y=0;y<nomsalle.size();y++)
    {
        choixsalle.addItem(nomsalle.get(y));
    }
    
    deco.addActionListener(new Modification.DeconnexionListener() );
    JComboBox choix = new JComboBox();
    choix.addItem("Ajouter");
    choix.addItem("Supprimer");
    choix.addItem("Modifier");//sois modifier type sois nom 
    
    JComboBox jours = new JComboBox();
    jours.addItem("Lundi");
    jours.addItem("Mardi");
    jours.addItem("Mercredi");
    jours.addItem("Jeudi");
    jours.addItem("Vendredi");
    jours.addItem("Samedi");
    
    JComboBox etat1 = new JComboBox();
    etat1.addItem("Attente");
    etat1.addItem("Validee");
    etat1.addItem("Annulee");
    
    
    choix.addActionListener(new ActionListener() {
        private Object Modification;
        @Override
        public void actionPerformed(ActionEvent e) {
                    ActionEvent a = null;
                    String selected= (String)choix.getSelectedItem();
                    System.out.println("Selection : "+selected);
                    
                    if("Modifier".equals(selected))
            {
               x=0;
               y=0;
               Date.setVisible(false);
               jours.setVisible(false);
               Semaine.setVisible(false);
               Heuredeb.setVisible(false);
               HeureFin.setVisible(false);
               etat1.setVisible(false);
               choixcours.setVisible(false);
               choixgroupe.setVisible(false);
               choixenseignant.setVisible(false);
               choixsalle.setVisible(false);
               ajout.setVisible(false);
               aThis.revalidate(); 
               

            }
                  else if("Ajouter".equals(selected))
            {
                
                x=90;
                y=110;
                Date.setVisible(true);
               jours.setVisible(true);
               Semaine.setVisible(true);
               Heuredeb.setVisible(true);
               HeureFin.setVisible(true);
               etat1.setVisible(true);
               choixcours.setVisible(true);
               choixgroupe.setVisible(true);
               choixenseignant.setVisible(true);
               choixsalle.setVisible(true);
               ajout.setVisible(true);
                Date.setBounds(300,330, x, 30);
                jours.setBounds(420,330, x, 30);
                Semaine.setBounds(530,330, x, 30);
                Heuredeb.setBounds(650,330, y, 30);
                HeureFin.setBounds(790,330, x, 30);
                etat1.setBounds(300,430, x, 30);
                choixcours.setBounds(420,430, x, 30);
                choixgroupe.setBounds(530,430, x, 30);
                choixenseignant.setBounds(650,430, x, 30);
                choixsalle.setBounds(790,430, x, 30);
                ajout.setBounds(530,500, x, 30);
                jour1="Lundi";
                aThis.validate();
                 jours.addActionListener(new ActionListener()
            {
                
              
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        jour1= (String)jours.getSelectedItem();
                       System.out.println("Selection : "+jour1);
                    }
            });
               
                  choixgroupe.addActionListener(new ActionListener()
            {
                
              
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        groupe1= (String)choixgroupe.getSelectedItem();
                       System.out.println("Selection : "+groupe1);
                        try {
                            id_groupe=recuper.getsqlID(groupe1);
                        } catch (SQLException ex) {
                            Logger.getLogger(Modification.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
            });
                 
                 
                 
                 
                 etat1.addActionListener(new ActionListener()
            {
                
              
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        Etat1= (String)etat1.getSelectedItem();
                       System.out.println("Selection : "+Etat1);
                       
                    }
            });
                 
                 
                 choixenseignant.addActionListener(new ActionListener()
            {
                
              
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        enseignant1= (String)choixenseignant.getSelectedItem();
                       System.out.println("Selection : "+enseignant1);
                        try {
                            id_prof=recup1.gettryID(enseignant1);
                        } catch (SQLException ex) {
                            Logger.getLogger(Modification.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
            });
                 
               
                 choixcours.addActionListener(new ActionListener()
            {
                
              
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        cours1= (String)choixcours.getSelectedItem();
                       System.out.println("Selection : "+cours1);
                        try {
                            id_cours=recu.gettryID(cours1);
                        } catch (SQLException ex) {
                            Logger.getLogger(Modification.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
            });
                 
                 choixsalle.addActionListener(new ActionListener()
            {
                
              
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        salle1= (String)choixsalle.getSelectedItem();
                        try {
                            id_salle=recup.gettryID(salle1);
                        } catch (SQLException ex) {
                            Logger.getLogger(Modification.class.getName()).log(Level.SEVERE, null, ex);
                        }
                       System.out.println("Selection : "+salle1);
                    }
            });
                 
                 
                ajout.addActionListener (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
               
                try {
                    boolean ajouter =ajout(aThis,Date,jour1,Semaine,Heuredeb,HeureFin,Etat1,cours1,id_groupe,enseignant1,salle1,id_prof,id_cours,id_salle);
                    if(ajouter==true)
            { Date.setVisible(false);
               jours.setVisible(false);
               Semaine.setVisible(false);
               Heuredeb.setVisible(false);
               HeureFin.setVisible(false);
               etat1.setVisible(false);
               choixcours.setVisible(false);
               choixgroupe.setVisible(false);
               choixenseignant.setVisible(false);
               choixsalle.setVisible(false);
               ajout.setVisible(false);
            }
                } catch (SQLException ex) {
                    Logger.getLogger(Modification.class.getName()).log(Level.SEVERE, null, ex);
                }
                 
            }   
            }); }
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
    aThis.add(deco);
    aThis.add(choix1);
    aThis.add(choix);
    aThis.add(etu);
    aThis.add(co);
    aThis.add(se);
    aThis.validate();
    
    //aThis.add(ID);
    aThis.add(Date);
    aThis.add(jours);
    aThis.add(Semaine);
    aThis.add(Heuredeb);
    aThis.add(HeureFin);
    aThis.add(etat1);
    aThis.add(choixcours);
    aThis.add(choixgroupe);
    aThis.add(choixenseignant);
    aThis.add(choixsalle);
    
   
    aThis.add(ajout);
    aThis.add(check1);
    aThis.add(check2);
    aThis.validate();
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
    
     public JTextField getSemaine() {
        return Semaine;
    }
      public JTextField getDate() {
        return Date;
    }
       public JTextField getHeureDeb() {
        return Heuredeb;
    }
        public JTextField getHeureFin() {
        return HeureFin;
    }
    
     private boolean ajout(View aThis,JTextField Date, String jour1, JTextField Semaine, JTextField Heuredeb, JTextField HeureFin, String Etat, String cours, int Groupe,String Prof,String Salle,int id_prof,int id_cours,int id_salle) throws SQLException
     {
             
            
             Connection myConnection;
             myConnection=ConnexionBDD.init();
             Statement statement,statement2,statementhu,statementha,statementho,statementhi;
             ResultSet resultat,resultathu,resultatha,resultatho,resultathi;
             
           
            String date=getDate().getText();
            

          // String date1 = new SimpleDateFormat("yyyy-MM-dd").format(getDate().getText());
            
            int Semaine1 = 0,heuredeb = 0,heurefin = 0;
            String semaine=getSemaine().getText();
            String heuredeb1=getHeureDeb().getText();
            String heurefin1=getHeureFin().getText();
            
            for(int u=1;u<51;u++)
            { 
                if(String.valueOf(u).equals(semaine))
                { 
                    Semaine1=u;
                    break;
                }
            } 
             
            for(int u=8;u<19;u++)
            { 
                if(String.valueOf(u).equals(heuredeb1))
                { 
                    heuredeb=u;
                    break;
                }
            } 
            
            for(int u=8;u<21;u++)
            { 
                if(String.valueOf(u).equals(heurefin1))
                { 
                    heurefin=u;
                    break;
                }
            } 
             
             int jour=7;
           
             if("Lundi".equals(jour1))  
             {jour=0;}
             
             if("Mardi".equals(jour1))
             {jour=1;}
             
             if("Mercredi".equals(jour1))
             {jour=2;}
             
             if("Jeudi".equals(jour1))
             {jour=3;}
             
             if("Vendredi".equals(jour1))
             {jour=4;}
             
             if("Samedi".equals(jour1))
             {jour=5;}
             
            System.out.println("jour = "+jour);
             
             String test = "02/01/20";//essai format date
             String format = "dd-MM-yyyy";
             SimpleDateFormat sdf = new SimpleDateFormat(format);
             sdf.setLenient(false);
             
             System.out.println(heuredeb+" "+heurefin);
             
            
             
             if(Semaine1!=0&&(((8==heuredeb))&&(10==heurefin))||((10==heuredeb)&&(12==heurefin))||((12==heuredeb)&&(14==heurefin))||((14==heuredeb)&&(16==heurefin))||((16==heuredeb)&&(18==heurefin))||((18==heuredeb)&&(20==heurefin)))
             {    
            
                 //vérification qu'une séance ne se passe pas déjà en même temps : groupe
                statementhu = myConnection.createStatement();
                String sqlhu = "SELECT * FROM seance INNER JOIN seance_groupe ON seance.ID=seance_groupe.ID_Seance WHERE ID_Groupe ='"+Groupe+"' AND Heure_Debut='"+heuredeb+"' AND Heure_Fin='"+heurefin+"' AND Jour='"+jour+"' AND Semaine='"+Semaine1+"'";
                resultathu = statementhu.executeQuery(sqlhu);
                 
                if(resultathu.next()){   JOptionPane.showMessageDialog(null,"Horraires déjà remplies pour ce Groupe!","Error",JOptionPane.PLAIN_MESSAGE);   }
                 
                else{
                 
                  //vérification qu'une séance ne se passe pas déjà en même temps : salle 
                statementha = myConnection.createStatement();
                String sqlha = "SELECT * FROM seance INNER JOIN seance_salle ON seance.ID=seance_salle.ID_Seance WHERE ID_Salle ='"+id_salle+"' AND Heure_Debut='"+heuredeb+"' AND Heure_Fin='"+heurefin+"' AND Jour='"+jour+"' AND Semaine='"+Semaine1+"'";
                resultatha = statementha.executeQuery(sqlha);
                 
                if(resultatha.next()){   JOptionPane.showMessageDialog(null,"La salle est déjà remplie!","Error",JOptionPane.PLAIN_MESSAGE);   }
                 
                else{
                    
                   //vérification qu'une séance ne se passe pas déjà en même temps : salle 
                statementho = myConnection.createStatement();
                String sqlho = "SELECT * FROM seance INNER JOIN seance_enseignant ON seance.ID=seance_enseignant.ID_Seance WHERE ID_Enseignant ='"+id_prof+"' AND Heure_Debut='"+heuredeb+"' AND Heure_Fin='"+heurefin+"' AND Jour='"+jour+"' AND Semaine='"+Semaine1+"'";
                resultatho = statementho.executeQuery(sqlho);
                 
                if(resultatho.next()){   JOptionPane.showMessageDialog(null,"Cet enseignant n'est pas libre pour cette semaine et ces horraires !","Error",JOptionPane.PLAIN_MESSAGE);   }
                 
                else{   
                
                   //vérification qu'une séance ne se passe pas déjà en même temps : salle 
                statementhi = myConnection.createStatement();
                String sqlhi = "SELECT * FROM seance se INNER JOIN seance_enseignant see ON se.ID=see.ID_Seance INNER JOIN enseignant en  ON en.ID_Utilisateur= see.ID_Enseignant WHERE en.ID_Cours ='"+id_cours+"' AND ID_Utilisateur='"+id_prof+"'";
                resultathi = statementhi.executeQuery(sqlhi);
                 
                if(!resultathi.next()){   JOptionPane.showMessageDialog(null,"Cet enseignant n'enseigne pas ce cours !","Error",JOptionPane.PLAIN_MESSAGE);   }
                 
                else{        
                      
                 
             statement = myConnection.createStatement();
             String sql = "INSERT INTO seance(`Date`, `Jour`, `Semaine`, `Heure_Debut`, `Heure_Fin`, `Etat`, `ID_Cours`, `ID_Type`) VALUES ('"+date+"','"+jour+"','"+Semaine1+"','"+heuredeb+"','"+heurefin+"','"+Etat+"','"+id_cours+"','1')";
             Seance nouvelle = new Seance(date,jour,Semaine1,heuredeb,heurefin,Etat,id_cours,1);
             
              PreparedStatement statement3 = myConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
              statement3.executeUpdate();

              ResultSet rs = statement3.getGeneratedKeys();
             if (rs != null && rs.next()) {
             long generatedId= rs.getLong(1);
          
             //Seance nouvelle = new Seance("2020-06-12",0,1,14,16,"attente",1,1);
             
            
             //int idseance = nouvelle.getidsql("2020-06-12",0,1,14,16,"attente",1,1);
             String sql1="INSERT INTO seance_enseignant(`ID_Seance`, `ID_Enseignant`) VALUES ('"+generatedId +"','"+id_prof+"')";
             String sql2="INSERT INTO seance_groupe(`ID_Seance`, `ID_Groupe`) VALUES ('"+generatedId +"',+'"+Groupe+"')";
             String sql3="INSERT INTO seance_salle(`ID_Seance`, `ID_Salle`) VALUES ('"+generatedId +"','"+id_salle+"')";  
             
             
             //int update = statement.executeUpdate(sql);
             int update1 = statement.executeUpdate(sql1);
             int update2 = statement.executeUpdate(sql2);
             int update3 = statement.executeUpdate(sql3);
             System.out.println("tu veux ajouter !");
             
             JOptionPane.showMessageDialog(null,"La séance a bien été ajouté !","Success",JOptionPane.PLAIN_MESSAGE);
             return true;
             
             }}}}}}else {   JOptionPane.showMessageDialog(null,"Erreur entrée Heure, incompatible ou les horraires ne se suivent pas","Error",JOptionPane.PLAIN_MESSAGE);
              return false;}
        return false;
           
       
   
}
     
     public void modifseance(String titre )
     { System.out.println("ça modifie ");
      JFrame essai = new JFrame();
      essai.setTitle("MODIFICATION");
      essai.setSize(new Dimension(800,600));
      essai.setLocationRelativeTo(null);
      //essai.setResizable(false);
      JLabel titre1 = new JLabel("SEANCE "+titre);
      titre1.setBounds(350,20,100,30);
      essai.add(titre1);
      essai.setVisible(true);
     }
}
