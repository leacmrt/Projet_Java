package vue;

/**
 *
 * @author lele1
 */
import controlleur.AuthentificationControleur;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static java.lang.System.exit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import modele.Authentification;
import modele.CoursDAO;
import modele.EnseignantDAO;
import modele.GroupeDAO;
import modele.SalleDAO;
import modele.Seance;
import modele.SeanceDAO;


/**
 *
 * @author lele1
 */
class Modification extends JPanel implements ItemListener{
    int x=90;
     int y=110;
     String jour1,cours1,salle1,enseignant1,Etat1,groupe1,seance1,ind;
     int id_prof,id_cours,id_groupe,id_salle;
     AuthentificationControleur control;
     Statement statement;
     
     private JTextField Date = new JTextField(" Date"); 
     private JTextField Semaine = new JTextField(" Semaine"); 
     private JTextField Heuredeb = new JTextField(" Heure de debut"); 
     private JTextField HeureFin= new JTextField(" Heure de fin");
     private JTextField t = new JTextField(10);
     
    public Modification()
    {}
     
     
    public Modification(View aThis,int ID_Utilisateur,String droit,int etat) throws SQLException, IOException
    {
        control= new AuthentificationControleur();
    JButton etu= new JButton("CHOISISSEZ PARMIS COURS ET SEANCE");
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
    choix.addItem("Modifier Etat");//sois modifier type sois nom 
    
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
                    
                    if("Modifier Etat".equals(selected))
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
               
                JFrame Modifierseance = new JFrame();
               Modifierseance.setTitle("Modification des seances");
               Modifierseance.setSize(400,300);
               Modifierseance.setLocationRelativeTo(null);
               Modifierseance.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               Modifierseance.setVisible(true);
               
               JPanel panModif = new JPanel();
               Modifierseance.setContentPane(panModif);
               JLabel labeltitre = new JLabel("Modification de l'etat de la seance : "+"\n");
               panModif.add(labeltitre);
               SeanceDAO la= new SeanceDAO();
               
               JComboBox seance = new JComboBox();
                        try {
                            
                            ArrayList<String> recupsean = la.recupseance();
                            for (int y=0;y<recupsean.size();y++)
               {
                   System.out.println(recupsean.get(y));
                   seance.addItem(recupsean.get(y));
               }
                        } catch (SQLException ex) {
                            Logger.getLogger(Modification.class.getName()).log(Level.SEVERE, null, ex);
                        }
               
              
              JLabel labelid = new JLabel("Saisir la seance à modifier"+"\n");
              panModif.add(labelid);
              panModif.add(seance);
                 seance.addActionListener(new ActionListener()
            {
                
              
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        ArrayList<String> idresult;
                        try {
                            idresult = la.recupseanceid();
                            int y = seance.getSelectedIndex();
                        seance1= (String)seance.getSelectedItem();
                        ind = idresult.get(y);
                       System.out.println("Selection : "+ind);
                        } catch (SQLException ex) {
                            Logger.getLogger(Modification.class.getName()).log(Level.SEVERE, null, ex);
                        }
                         
                    }
            });
               
              JTextField id = new JTextField(10);
             // panModif.add(id);
              JLabel labeletat = new JLabel("Saisir le nouveau etat de la seance : "+"\n");
              panModif.add(labeletat);
              JTextField etatfutur = new JTextField(10);
              panModif.add(etatfutur);
              JButton OK = new JButton("OK");
              panModif.add(OK);
              
              JLabel espace= new JLabel("      "
                      
                      + "   "+"\n");
               espace.setPreferredSize(new Dimension(70,100));
              panModif.add(espace);
              JButton deco;
                 deco=new JButton("RETOUR");
                deco.setMargin(new Insets(0, 0, 0, 0));
                deco.setPreferredSize(new Dimension(60,60));
                deco.addActionListener(new Modification.retourListener(panModif) );
                panModif.add(deco);
 
                OK.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                      
                        try{
                      String idseance ;
                      idseance = id.getText();
                      String etatseance ;
                      etatseance = etatfutur.getText();
                       Connection myConnection;
                       myConnection=Authentification.init();
                        //System.out.print("co okkkkk");
                       statement = myConnection.createStatement();
                       statement.executeUpdate("UPDATE seance SET Etat='"+etatseance+"' WHERE ID='"+ind+"'");
                        //while(rst.next()){
                        //JLabel jour = new JLabel(rst.getInt("Jour")+"\t");
                        //}
                        Modifierseance.setVisible(false);
                  
                       } catch (SQLException e4) {
             
                        System.out.println(e4.getMessage());
                       }

                    } 
                }); 
              
               

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
                  
                   
                  else if("Supprimer".equals(selected))
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
               choixenseignant.setVisible(true);
               choixsalle.setVisible(false);
               ajout.setVisible(false);
               aThis.revalidate(); 
               
               JFrame Supprimerseance = new JFrame();
               Supprimerseance.setTitle("Supprimer un groupe et ou un enseignant");
               Supprimerseance.setSize(400,400);
               Supprimerseance.setLocationRelativeTo(null);
               Supprimerseance.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               Supprimerseance.setVisible(true);
               
               JPanel panSup = new JPanel();
               Supprimerseance.setContentPane(panSup);
               JLabel labeltitre = new JLabel("Suppression du groupe et ou de l'enseignant: "+"\n");
               panSup.add(labeltitre);
               
                JButton deco;
                SeanceDAO la= new SeanceDAO();
               
               JComboBox seance = new JComboBox();
                        try {
                            
                            ArrayList<String> recupsean = la.recupseance();
                            for (int y=0;y<recupsean.size();y++)
               {
                   System.out.println(recupsean.get(y));
                   seance.addItem(recupsean.get(y));
               }
                        } catch (SQLException ex) {
                            Logger.getLogger(Modification.class.getName()).log(Level.SEVERE, null, ex);
                        }
              
              JLabel labelid = new JLabel("Saisir la seance à modifier : "+"\n");
              panSup.add(labelid);
               panSup.add(seance);
                 seance.addActionListener(new ActionListener()
            {
                
              
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        ArrayList<String> idresult;
                        try {
                            idresult = la.recupseanceid();
                            int y = seance.getSelectedIndex();
                        seance1= (String)seance.getSelectedItem();
                        ind = idresult.get(y);
                       System.out.println("Selection : "+ind);
                        } catch (SQLException ex) {
                            Logger.getLogger(Modification.class.getName()).log(Level.SEVERE, null, ex);
                        }
                         
                    }
            });
               
              JTextField id = new JTextField(10);
              //panSup.add(id);
              JLabel labelgroupe = new JLabel("Voulez vous supprimer le groupe de la seance ? O/N : "+"\n");
              panSup.add(labelgroupe);
              JTextField supgroupe = new JTextField(10);
              panSup.add(supgroupe);
              JLabel labelenseignant = new JLabel("Voulez vous supprimer l'enseignant de la seance ? O/N: "+"\n");
              panSup.add(labelenseignant);
              JTextField enseignant = new JTextField(10);
              panSup.add(enseignant);
              JButton OK = new JButton("OK");
              panSup.add(OK);
             
              JLabel espace= new JLabel("      "
                      
                      + "   "+"\n");
               espace.setPreferredSize(new Dimension(70,100));
              panSup.add(espace);
               deco=new JButton("RETOUR");
                deco.setMargin(new Insets(0, 0, 0, 0));
                deco.setPreferredSize(new Dimension(60,60));
                deco.addActionListener(new Modification.retourListener(panSup) );
              panSup.add(deco);
 
                OK.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                      
                      try{
                      String idseance ;
                      idseance = id.getText();
                      String groupe ;
                      groupe = supgroupe.getText();
                      String supenseigant ;
                      supenseigant = enseignant.getText();
                      
                      
                      control.supprimer(groupe,ind,supenseigant);
                       
                       
                                if( groupe.equals("O")){

                               // statement.execute("DELETE FROM groupe WHERE ID='"+idseance+"'");
                                Supprimerseance.setVisible(false);
                                }
                                else if(supenseigant.equals("O"))
                                {
                               // statement.execute("DELETE FROM seance_enseignant WHERE ID_Seance='"+idseance+"'");
                                Supprimerseance.setVisible(false);
                                }
                                else if(groupe.equals("N"))
                                {
                                    System.out.println("Vous supprimez pas de groupe");
                                     Supprimerseance.setVisible(false);
                                }
                                else if(supenseigant.equals("N") )
                                {
                                    System.out.println("Vous supprimez pas d'enseignant");
                                     Supprimerseance.setVisible(false);
                                }
                                else if(supenseigant.equals("N")== false ||supenseigant.equals("O")== false)
                                {
                                 JFrame erreur = new JFrame();
                                 JPanel er = new JPanel();
                                 erreur.setContentPane(er);
                                 erreur.setSize(300,300);
                                 erreur.setLocationRelativeTo(null);
                                 erreur.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                 erreur.setVisible(true);
                                 JLabel laberr = new JLabel("Saisir erronÃ© veuillez recommencer avec O/N "+"\n");
                                 er.add(laberr);
                                 //erreur.setVisible(false);
                                
                                }

                                  
                       } catch (SQLException e4) {
             
                        System.out.println(e4.getMessage());
                       }

                    } 
                }); }
            else{ x=0;
               y=0;}
               
                    
        }


    });
    
    
    JComboBox choix1 = new JComboBox();
    choix1.addItem("Ajouter Enseignant");
    choix1.addItem("Ajouter Groupe");
    choix1.addItem("Modifier TYPE Cours");
    
   choix1.addActionListener(new ActionListener() {
            private Object Modification;
        @Override
        public void actionPerformed(ActionEvent e) {
                    ActionEvent a = null;
                    String selected= (String)choix1.getSelectedItem();
                    System.out.println("Selection : "+selected);
                    
                    if("Modifier TYPE Cours".equals(selected))
            {
                System.out.println("ON MODIFIE le type de cours ");
                JButton deco;
                SeanceDAO la= new SeanceDAO();
               
               JComboBox seance = new JComboBox();
                        try {
                            
                            ArrayList<String> recupsean = la.recupseance();
                            for (int y=0;y<recupsean.size();y++)
               {
                   System.out.println(recupsean.get(y));
                   seance.addItem(recupsean.get(y));
               }
                        } catch (SQLException ex) {
                            Logger.getLogger(Modification.class.getName()).log(Level.SEVERE, null, ex);
                        }
              
                
              JFrame ModifType = new JFrame();
               ModifType.setTitle("Modification du type de cours");
               ModifType.setSize(300,300);
               ModifType.setLocationRelativeTo(null);
               ModifType.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               ModifType.setVisible(true);
               
               JPanel panModiftype = new JPanel();
               ModifType.setContentPane(panModiftype);
               
              JLabel labeltitre = new JLabel("Modification de l'etat de la seance : "+"\n");
              panModiftype.add(labeltitre);
               
              
              JLabel labelid = new JLabel("Saisir la seance a modifier : "+"\n");
              panModiftype.add(labelid);
              JTextField id = new JTextField(10);
              //panModiftype.add(id);
              panModiftype.add(seance);
                 seance.addActionListener(new ActionListener()
            {
                
              
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        ArrayList<String> idresult;
                        try {
                            idresult = la.recupseanceid();
                            int y = seance.getSelectedIndex();
                        seance1= (String)seance.getSelectedItem();
                        ind = idresult.get(y);
                       System.out.println("Selection : "+ind);
                        } catch (SQLException ex) {
                            Logger.getLogger(Modification.class.getName()).log(Level.SEVERE, null, ex);
                        }
                         
                    }
            });
              JLabel labeletat = new JLabel("Saisir le nouveau type du cours TD/TP/Cours : "+"\n");
              panModiftype.add(labeletat);
              JTextField type = new JTextField(10);
              panModiftype.add(type);
              JButton OK = new JButton("OK");
              panModiftype.add(OK);
              
              JLabel espace= new JLabel("      "
                      
                      + "   "+"\n");
               espace.setPreferredSize(new Dimension(70,100));
              panModiftype.add(espace);
              
              //JButton deco;
                deco=new JButton("RETOUR");
                deco.setMargin(new Insets(0, 0, 0, 0));
                deco.setPreferredSize(new Dimension(60,60));
                deco.addActionListener(new Modification.retourListener(panModiftype) );
              panModiftype.add(deco);
 
                OK.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                      
                        try{
                            
                      
                      String idseance ;
                      idseance = id.getText();
                      String NouveauType ;
                      NouveauType = type.getText();
                     
                      control.modifetat(NouveauType,ind);
                     
                        //while(rst.next()){
                        //JLabel jour = new JLabel(rst.getInt("Jour")+"\t");
                        //}
                        ModifType.setVisible(false);
                  
                       } catch (SQLException e4) {
             
                        System.out.println(e4.getMessage());
                       }

                    } 
                }); 
              
   
            } 
                 
                   if("Ajouter Enseignant".equals(selected))
            {

               JFrame AjoutEnseignant = new JFrame();
               AjoutEnseignant.setTitle("Ajouter un enseignant");
               AjoutEnseignant.setSize(600,600);
               AjoutEnseignant.setLocationRelativeTo(null);
               AjoutEnseignant.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               AjoutEnseignant.setVisible(true);
                SeanceDAO la= new SeanceDAO();
               
               JComboBox seance = new JComboBox();
                        try {
                            
                            ArrayList<String> recupsean = la.recupseance();
                            for (int y=0;y<recupsean.size();y++)
               {
                   System.out.println(recupsean.get(y));
                   seance.addItem(recupsean.get(y));
               }
                        } catch (SQLException ex) {
                            Logger.getLogger(Modification.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        
               JPanel ajout = new JPanel();
               AjoutEnseignant.setContentPane(ajout);
               
              JLabel labeltitre = new JLabel("Ajouter un enseignant : "+"\n");
              
              ajout.add(labeltitre);
              
              JLabel labelid = new JLabel("Saisir la seance a laquelle on ajoute l'enseignant: "+"\n");
              ajout.add(labelid);
              ajout.add(seance);
               seance.addActionListener(new ActionListener()
            {
                
              
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        ArrayList<String> idresult;
                        try {
                            idresult = la.recupseanceid();
                            int y = seance.getSelectedIndex();
                        seance1= (String)seance.getSelectedItem();
                        ind = idresult.get(y);
                       System.out.println("Selection : "+ind);
                        } catch (SQLException ex) {
                            Logger.getLogger(Modification.class.getName()).log(Level.SEVERE, null, ex);
                        }
                         
                    }
            });
               
              JTextField id = new JTextField(10);
             // ajout.add(id);
              JLabel labenseignant = new JLabel("Saisir l'enseignant: "+"\n");
              ajout.add(labenseignant);
              ajout.add(choixenseignant);
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
              JTextField en = new JTextField(10);
             // ajout.add(en);
              JButton OK = new JButton("OK");
              ajout.add(OK);
              
              JLabel espace= new JLabel("      "
                      + "        "
                      + "   "+"\n"+"\n");
               espace.setPreferredSize(new Dimension(100,100));
              ajout.add(espace);
              JButton deco;
                 deco=new JButton("RETOUR");
                deco.setMargin(new Insets(0, 0, 0, 0));
                deco.setPreferredSize(new Dimension(60,60));
                deco.addActionListener(new Modification.retourListener(ajout) );
              ajout.add(deco);
 
                OK.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                      
                        try{
                      String idseance ;
                      idseance = id.getText();
                      String NouveauEn ;
                      NouveauEn = en.getText();
                      
                      control.ajout_enseignant(String.valueOf(id_prof),ind);
                     
                        //JLabel jour = new JLabel(rst.getInt("Jour")+"\t");
                        //}
                        AjoutEnseignant.setVisible(false);
                  
                       } catch (SQLException e4) {
             
                        System.out.println(e4.getMessage());
                       }

                    } 
                }); 
              
   
            } if("Ajouter Groupe".equals(selected))
            {
                   System.out.println("Vous voulez ajouter un groupe à une seance!");
                   JFrame AjoutEnseignant = new JFrame();
               AjoutEnseignant.setTitle("Ajouter un enseignant");
               AjoutEnseignant.setSize(600,600);
               AjoutEnseignant.setLocationRelativeTo(null);
               AjoutEnseignant.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               AjoutEnseignant.setVisible(true);
                SeanceDAO la= new SeanceDAO();
               
               JComboBox seance = new JComboBox();
                        try {
                            
                            ArrayList<String> recupsean = la.recupseance();
                            for (int y=0;y<recupsean.size();y++)
               {
                   System.out.println(recupsean.get(y));
                   seance.addItem(recupsean.get(y));
               }
                        } catch (SQLException ex) {
                            Logger.getLogger(Modification.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        
               JPanel ajout = new JPanel();
               AjoutEnseignant.setContentPane(ajout);
               
              JLabel labeltitre = new JLabel("Ajouter un Groupe à une séance : "+"\n");
              
              ajout.add(labeltitre);
              
              JLabel labelid = new JLabel("Saisir la seance: "+"\n");
              ajout.add(labelid);
              ajout.add(seance);
               seance.addActionListener(new ActionListener()
            {
                
              
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        ArrayList<String> idresult;
                        try {
                            idresult = la.recupseanceid();
                            int y = seance.getSelectedIndex();
                        seance1= (String)seance.getSelectedItem();
                        ind = idresult.get(y);
                       System.out.println("Selection : "+ind);
                        } catch (SQLException ex) {
                            Logger.getLogger(Modification.class.getName()).log(Level.SEVERE, null, ex);
                        }
                         
                    }
            });
               
              JTextField id = new JTextField(10);
             // ajout.add(id);
              JLabel labenseignant = new JLabel("Saisir le groupe: "+"\n");
              ajout.add(labenseignant);
              ajout.add(choixgroupe);
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
              JTextField en = new JTextField(10);
             // ajout.add(en);
              JButton OK = new JButton("OK");
              ajout.add(OK);
              
              JLabel espace= new JLabel("      "
                      + "        "
                      + "   "+"\n"+"\n");
               espace.setPreferredSize(new Dimension(100,100));
              ajout.add(espace);
              JButton deco;
                 deco=new JButton("RETOUR");
                deco.setMargin(new Insets(0, 0, 0, 0));
                deco.setPreferredSize(new Dimension(60,60));
                deco.addActionListener(new Modification.retourListener(ajout) );
              ajout.add(deco);
 
                OK.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                      
                       
                     
   
                        try {
                            control.ajout_groupe(String.valueOf(id_groupe),ind);
                        } catch (SQLException ex) {
                            Logger.getLogger(Modification.class.getName()).log(Level.SEVERE, null, ex);
                        }
                     
                        //JLabel jour = new JLabel(rst.getInt("Jour")+"\t");
                        //}
                        AjoutEnseignant.setVisible(false);
                  
                      
             
                        
                       
                    } 
                }); 
              
            }
            }
        });
   
    
    choix.setBounds(150,330, 130, 30);
    choix1.setBounds(150,220, 130, 30);
    deco.setBounds(900,10, 130, 30);
    etu.setBounds(370,50,300, 30);
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
    BufferedImage myPicture = ImageIO.read(new File("ece.jpg"));
    ImageIcon image = new ImageIcon(myPicture);
    JLabel jlabel = new JLabel(image);
    jlabel.setBounds(100,450,150,150);
    aThis.add(jlabel);
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
    
     
       private static class retourListener implements ActionListener {

        private JPanel ajout;

        private retourListener(JPanel ajout) {
           this.ajout=ajout; //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

       @Override
            public void actionPerformed(ActionEvent e) {
                    ActionEvent a = null;
                    System.out.print("FERME MOI CETTE FENETRE");//test affichage oups
                     JComponent comp = (JComponent) e.getSource();
  Window win = SwingUtilities.getWindowAncestor(comp);
  win.dispose(); 
//this.ajout.pack();
                    
                    
            }
    }
 
            

           
     public void modifseance(String titre)
     { 
      JFrame essai = new JFrame();
      essai.setTitle("MODIFICATION");
      essai.setSize(new Dimension(800,600));
      essai.setLocationRelativeTo(null);
      //essai.setResizable(false);
      JLabel titre1 = new JLabel("SEANCE "+titre);
      titre1.setBounds(200,20,200,30);
      essai.add(titre1);
  //    essai.setVisible(true);
      
      JPanel pan = new JPanel();
     
      //pan.setBackground(Color.red);
      JButton boutonmodifnom = new JButton("MODIFIER NOM du cours");
  
      
      pan.setLayout(null); //on annule la position par defaults des widgets
      pan.add(titre1) ;
      pan.add(boutonmodifnom);
      boutonmodifnom.setBounds(200,100,200,60);
    
     essai.setContentPane(pan);
      essai.setVisible(true);
      
      boutonmodifnom.addActionListener(new ActionListener()
        {
          @Override
          public void actionPerformed(ActionEvent e)
          {
              JFrame modifnom = new JFrame();
              modifnom.setTitle("MODIFICATION NOM");
              modifnom.setSize(new Dimension(800,600));
              JPanel pann = new JPanel();
              JLabel lab = new JLabel("Saisissez le nouveau nom du cours : ");
              pann.add(lab);
              pann.add(t);
              JButton OK = new JButton("OK");
              pann.add(OK);
              System.out.println("Hey CA ROULE");
              modifnom.setContentPane(pann);
              modifnom.setVisible(true);
              

              OK.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                  
                       try{
                       String Changement;
                       Changement = t.getText();
                       
                       control.modif_nomcours(Changement,titre.split("-")[0].substring(1));
                       
                       
                       System.out.println("requete modif");
                        System.out.println(titre.split("-")[0].substring(1));
                       //JOptionPane.showConfirmDialog(null,"MODIFIER un autre nom de cours ? ");
                       modifnom.setVisible(false);
                       essai.setVisible(false);
                  
                       } catch (SQLException e4) {
             
                        System.out.println(e4.getMessage());
                       }

                    } 
                }); 
          }
 
        });
}
    
    
}
