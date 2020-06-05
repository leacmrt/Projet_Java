
package vue;

/**
 *
 * @author lele1
 */
import controlleur.AuthentificationControleur;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import static java.lang.System.exit;
import java.sql.Connection;
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
import modele.Authentification;
import modele.CoursDAO;
import modele.EnseignantDAO;
import modele.GroupeDAO;
import modele.SalleDAO;
import modele.Seance;


/**
 *
 * @author lele1
 */
class Modification extends JPanel implements ItemListener{
    int x=90;
     int y=110;
     String jour1,cours1,salle1,enseignant1,Etat1,groupe1;
     int id_prof,id_cours,id_groupe,id_salle;
     AuthentificationControleur control;
     
     private JTextField Date = new JTextField(" Date"); 
     private JTextField Semaine = new JTextField(" Semaine"); 
     private JTextField Heuredeb = new JTextField(" Heure de debut"); 
     private JTextField HeureFin= new JTextField(" Heure de fin");
     
    public Modification()
    {}
     
     
    public Modification(View aThis,int ID_Utilisateur,String droit,int etat) throws SQLException
    {
        control= new AuthentificationControleur();
    JButton etu= new JButton("ETUDIANT");
    JButton co = new JButton("COURS ");
    JButton se=new JButton(" SEANCE ");
    JButton ajout= new JButton("AJOUTER");
    JButton deco=new JButton("Deconnexion ");
    JCheckBox check1 = new JCheckBox("Nom");
    JCheckBox check2 = new JCheckBox("Type");
     
      //JTextField ID = new JTextField("ID"); 
      
     

    CoursDAO recu = new CoursDAO();   
    ArrayList<String> nomcours= recu.gettoutlesnoms();
    JComboBox choixcours = new JComboBox();
    
    for(int y=0;y<nomcours.size();y++)
    {
        choixcours.addItem(nomcours.get(y));
    }
    
    GroupeDAO recuper = new GroupeDAO();   
    ArrayList<String> nomgroupe= recuper.gettoutlesnoms();
    JComboBox choixgroupe = new JComboBox();
    
    for(int y=0;y<nomgroupe.size();y++)
    {
        choixgroupe.addItem(nomgroupe.get(y));
    }
    
    
    EnseignantDAO recup1 = new EnseignantDAO();   
    ArrayList<String> nomenseignant= recup1.gettoutlesnoms();
    JComboBox choixenseignant= new JComboBox();
    
    for(int y=0;y<nomenseignant.size();y++)
    {
        choixenseignant.addItem(nomenseignant.get(y));
    }
    
    
    SalleDAO recup = new SalleDAO();   
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

                    boolean ajouter =control.ajout(aThis,Date,jour1,Semaine,Heuredeb,HeureFin,Etat1,cours1,id_groupe,enseignant1,salle1,id_prof,id_cours,id_salle);
                
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
    
    
}
