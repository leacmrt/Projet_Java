/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;



import java.awt.Color;
import static java.awt.Color.blue;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.StringTokenizer;
import static javafx.scene.paint.Color.GREY;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JComboBox;
/**
 *
 * @author lele1
 */
public class ConnexionBDD implements ActionListener {
    
    int typeU; // 1 = étudiant , 2= Enseignant 
    static JTextField login1,mdp1 ;  
    Statement statement;
    ResultSet resultat,resultat1,resultat2;
    int ID;
    String Email ,Passwd, Nom, Prenom, Droit;
    
    
      static Connection myConnection; 
      
      public ConnexionBDD() throws SQLException{}
      
 
      
      public void verification(Authentification connct,String login, String mdp)
      {
          
         myConnection = init();
            try{
              
                statement = myConnection.createStatement();
                String sql = "SELECT * FROM utilisateur WHERE Mail ='"+login+"'";
                resultat = statement.executeQuery(sql);
                 
                if(resultat.next()){
                     
                    String motDePasse = resultat.getString("Mdp");
         
                if(motDePasse.equals(mdp)){
                   
                    
                    JOptionPane.showMessageDialog(null,"Connexion réussie ! Bienvenue "+login,"Success",JOptionPane.PLAIN_MESSAGE);
                    connct.dispose(); //on ferme la fenetre d'avant
                   
                    ID= resultat.getInt("ID");
                    Email=resultat.getString("Mail");
                    Passwd= resultat.getString("Mdp");
                    Droit=resultat.getString("Droit");
                    Prenom= resultat.getString("Prenom");
                    Nom=resultat.getString("Nom");
                    
                    
                    System.out.println("ID de l'utilisateur = "+ ID);
                    System.out.println("Droit de l'utilisateur = "+ Droit);
                    
                    
                    if("Oui".equals(Droit)){//si l'utilisateur est un étudiant
                    statement = myConnection.createStatement();
                    String sql1 = "SELECT * FROM etudiant WHERE ID_Utilisateur ='"+ID+"'";
                    resultat = statement.executeQuery(sql1);
                 
                    if(resultat.next()){
                     
                    int ID_Groupe = resultat.getInt("ID_Groupe");
                    
                    Etudiant nouveau = new Etudiant(ID,Email,Passwd,Prenom,Nom,Droit,ID_Groupe);}
                    }
                    
                  
                    if("Non".equals(Droit)){ //Si l'utilsateur est un enseignant
                     
                    Enseignant nouveau = new Enseignant(ID,Email,Passwd,Prenom,Nom,Droit);
                    }
                    
                    
                    
                
                    
                }else {
                     
                    JOptionPane.showMessageDialog(null,"Mot de passe incorrect ! ","Error",1);
                }
                }else {
                     
                    JOptionPane.showMessageDialog(null,"Login incorrect ! ","Error",1);
                }
 
                     myConnection.close();
         
            }catch (SQLException e4) {
             
                System.out.println(e4.getMessage());
            }
      }
      
      
      public int recherche(String envoie) throws SQLException
      {    int IDRecup;
           myConnection = init();
           System.out.println("ici :"+envoie);
           try{
           Statement util = myConnection.createStatement();
           ResultSet resulutil = null;
           String sql = "SELECT * FROM utilisateur WHERE  CONCAT(Prenom, ' ', Nom) LIKE '"+envoie+"'";
                   resulutil = util.executeQuery(sql);
                   if(resulutil.next())
                   {
                       IDRecup=resulutil.getInt("ID");
                       System.out.println("ID recherche ICI  : "+IDRecup);
                       return IDRecup;
                      
                    } else{ System.out.println("Pas dans la BDD");
                            JOptionPane.showMessageDialog(null,"Utilisateur introuvable","Error",JOptionPane.PLAIN_MESSAGE);
                            return 0;
                           }
           }catch (SQLException e4) {
             
                System.out.println(e4.getMessage());
            }
        return 0;
      }
      
      
       public int recherche1(String envoie) throws SQLException
      {    int EtatRecup;
           String EtatRecup1;
           myConnection = init();
           System.out.println("ici :"+envoie);
           try{
           Statement util = myConnection.createStatement();
           ResultSet resulutil = null;
           String sql = "SELECT * FROM utilisateur WHERE  CONCAT(Prenom, ' ', Nom) LIKE '"+envoie+"'";
                   resulutil = util.executeQuery(sql);
                   if(resulutil.next())
                   {
                      
                       EtatRecup1=resulutil.getString("Droit");
                       if("Oui".equals(EtatRecup1)) EtatRecup=1;
                       else EtatRecup=0;
                       
                       return EtatRecup;
                    } else{ 
                            return 0;
                           }
           }catch (SQLException e4) {
             
                System.out.println(e4.getMessage());
            }
        return 0;
      }
      
       
        public int rechercheSalle(String envoie) throws SQLException
      {    String NomRecup;
           int  NomRecup1;
           myConnection = init();
           System.out.println("ici salle :"+envoie);
           try{
           Statement util = myConnection.createStatement();
           ResultSet resulutil = null;
           String sql = "SELECT * FROM salle WHERE Nom_Salle LIKE '"+envoie+"'";
                   resulutil = util.executeQuery(sql);
                   if(resulutil.next())
                   {
                      NomRecup=resulutil.getString("Nom_Salle");
                       NomRecup1=resulutil.getInt("ID");
                       System.out.println("Recherche de la salle : "+NomRecup);
                       return NomRecup1;
                    }else    JOptionPane.showMessageDialog(null,"Salle introuvable","Error",JOptionPane.PLAIN_MESSAGE);
           }catch (SQLException e4) {
             
                System.out.println(e4.getMessage());
            }
        return 0;
      }
      
       
       
       
       
       
      public void chargecours(int semaine,JPanel la,int ID_utli1,int etat) throws SQLException
      {
           myConnection = init();
           Statement util = null;
           ResultSet resulutil = null;
           Object data[][] = new  Object [11][7];
           util= myConnection.createStatement();
           System.out.println("Semaine : "+semaine);
           if(etat==1)
                   {String sql = "SELECT * FROM seance_groupe s INNER JOIN seance se ON s.ID_Seance=se.ID ";
                   sql+=" INNER JOIN seance_enseignant sa ON sa.ID_Seance=s.ID_Seance";
                   sql+=" INNER JOIN enseignant en ON en.ID_Utilisateur=sa.ID_Enseignant";
                   sql+=" INNER JOIN utilisateur ut ON ut.ID=en.ID_Utilisateur";
                   sql+=" INNER JOIN seance_salle sea ON sea.ID_Seance=se.ID";
                   sql+=" INNER JOIN salle sal ON sal.ID=sea.ID_Salle";
                   sql+=" INNER JOIN cours co ON en.ID_Cours =co.ID";
                   sql += "  WHERE s.ID_Groupe ='"+ID_utli1+"' AND se.Semaine='"+semaine+"'";
                   resulutil = util.executeQuery(sql);
                  
                   }
           else if (etat==0){
               String sql = "SELECT * FROM enseignant en INNER JOIN utilisateur ut ON en.ID_Utilisateur=ut.ID";
                   sql += " INNER JOIN seance_enseignant sa ON sa.ID_Enseignant =ut.ID";
                    sql += " INNER JOIN seance se ON se.ID =sa.ID_Seance";
                    sql+=" INNER JOIN cours co ON en.ID_Cours =co.ID";
                    sql+=" INNER JOIN seance_groupe s ON s.ID_Seance=se.ID";
                    sql+=" INNER JOIN seance_salle sea ON sea.ID_Seance=se.ID";
                   sql+=" INNER JOIN salle sal ON sal.ID=sea.ID_Salle";
                   sql += "  WHERE ut.ID ='"+ID_utli1+"' AND se.Semaine='"+semaine+"'";
                   resulutil = util.executeQuery(sql);
                   
                }
                   
                   else if (etat==2){ //cas de recherche de salle
                   String sql = "SELECT * FROM salle sa INNER JOIN seance_salle sea ON sa.ID=sea.ID_Salle ";
                   sql+=" INNER JOIN seance se ON se.ID=sea.ID_Seance";
                   sql+=" INNER JOIN cours co ON se.ID_Cours=co.ID";
                   sql+=" INNER JOIN seance_groupe s on s.ID_Seance=se.ID";
                   sql+=" INNER JOIN seance_enseignant see ON see.ID_Seance=se.ID";
                   sql+=" INNER JOIN enseignant en ON en.ID_Utilisateur=see.ID_Enseignant";
                   sql+=" INNER JOIN utilisateur ut ON ut.ID=en.ID_Utilisateur"; 
                   sql += "  WHERE se.Semaine='"+semaine+"' AND sa.ID='"+ID_utli1+"'";
                   resulutil = util.executeQuery(sql);}
                   
           JTextArea textArea = new JTextArea(10, 50);
           
           
               int i = 0; 
                
                while (resulutil.next()) { //on remplit le tableau
                    
              
                
                int id = resulutil.getInt("ID");
                int date=resulutil.getInt("Date");
                int jour= resulutil.getInt("Jour");
                int semaine1 = resulutil.getInt("Semaine");
                int Heure_deb = resulutil.getInt("Heure_Debut");
                int id_cours = resulutil.getInt("ID_Cours");
                int id_groupe = resulutil.getInt("ID_Groupe");
                int Heure_fin = resulutil.getInt("Heure_Fin");   
                String Etat = resulutil.getString("Etat");
                String cours= resulutil.getString("Nom_Cours");
                String nom_salle=resulutil.getString("Nom_Salle");
                
                
               
                String nom= resulutil.getString("Nom");
              
                
                
               switch (Heure_deb) {
                   case 8:
                       i=0;
                       break;
                   case 10:
                       i=2;
                       break;
                   case 12:
                       i=4;
                       break;
                   case 14:
                       i=6;
                       break;
                   case 16:
                       i=8;
                       break;
                   case 18:
                       i=10;
                       break;
                   case 20:
                       i=6;
                       break;
                   default:
                       break;
               }
               
                System.out.println(i);
                
               
                Cours cour= new Cours(id_cours,cours,Color.blue);
                //Salle salle=new Salle()
                Seance sea = new Seance(id,date,jour,semaine1,Heure_deb,Heure_fin,Etat,id_cours,id_groupe);
                
                
                StringTokenizer st = null;
                textArea.setDragEnabled(true);
                //ArrayList<String> remplir = new ArrayList<>();
               String remplir =" "+cours+"- \n "+nom+" - \n"+nom_salle;
               
 
                //emplir.split(";");
                //textArea.append(remplir+" ");
                data[i][jour] = remplir ;
               
               
                
                }
                resulutil.close();
                
          String columns[] = {"Lundi", "Mardi", "Mercredi","Jeudi","Vendredi","Samedi" };
       
          
          DefaultTableCellRenderer po = new DefaultTableCellRenderer();
         DefaultTableModel model = new DefaultTableModel(data, columns);
         
        
          
          JTable table;
          
         
        table = new JTable(model) {
            
           
   
           
            public Component prepareRenderer(TableCellRenderer renderer, int rowIndex,  int columnIndex) { //colorie les cases avec cours
                //JComponent component1 = new JTextField();
                JComponent component = (JComponent) super.prepareRenderer(renderer, rowIndex, columnIndex);
                String la= (String)this.getValueAt(rowIndex, columnIndex);
                JComponent essai=(JComponent) renderer.getTableCellRendererComponent(this, la, false,true ,rowIndex, columnIndex);
                if(la!=null&&!("".equals(la)))
                {
                    
                    //essai.setToolTipText(la);
                    
                    int java = la.indexOf("JAVA");//faire ça pour tout les cours
                    int web = la.indexOf("DYNAMIQUE");//pour l'instant flemme
                    int anglais = la.indexOf("ANGLAIS");
                    
                    if (java>0) essai.setBackground(new Color(247,191,131) );
                    else if (web>0) essai.setBackground(new Color(247,234,211) );
                    else if (anglais>0) essai.setBackground(new Color(131,144,247));
                    else essai.setBackground(Color.lightGray);
                    
                    //essai.setValueAt((Value)la); 
                    //((JTextField)component1).setText("coucou \n ça va?");
                    this.setForeground(Color.black);
                    //this.add(component1);
                  
                    
                }
                
                if(rowIndex==1||rowIndex==3||rowIndex==5||rowIndex==7||rowIndex==9)
                    essai.setBackground(new Color(248,176,176));
               
                
                
                        
                return component;
            }

              
            
          
            
            
        };
        
        
        
         table.setEnabled(false);
        table.setDragEnabled(false);
      
                
        tableau(table,la,data);
        

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
          
          /* for(int j=0;j<11;j++)
           {for(int p=0;p<7;p++)
           {
               if(data[j][p]!=null)
               {System.out.print(data[j][p]+" là :  "+ j + " "+p);
               System.out.println("Value in the cell clicked :"+ " " +table.getValueAt(j,p).toString());
               
               }
           
           
           } System.out.print(" ");
           
           }*/
     //     table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
     
           la.show();
    
          JScrollPane pane = new JScrollPane(table);
          
            table.addMouseListener(new java.awt.event.MouseListener()//test modif reduit à neant

            {

                @Override
                public void mousePressed(java.awt.event.MouseEvent e)

            {

            int row=table.rowAtPoint(e.getPoint());
             System.out.println("r"+row);
            int col= table.columnAtPoint(e.getPoint());
             System.out.println("c"+col);
            if(data[row][col]!=null)
            {
            int rep = JOptionPane.showConfirmDialog(null,"Voulez vous modifier cette séance ?", "Bonjour", JOptionPane.YES_NO_OPTION);
            
            if(rep == JOptionPane.YES_OPTION)
            {
             
             System.out.println("Value in the cell clicked :"+ "R "+row+" col "+col+table.getValueAt(row,col).toString());
             Modification modif = new Modification();
             modif.modifseance(table.getValueAt(row,col).toString());
             System.out.println("ça modifie ");}
            

                }}

             @Override
             public void mouseClicked(MouseEvent e) {
               System.out.println("coucou"); // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
               System.out.println("mouse clicked at point:"
                       + e.getX() + " "
                       + e.getY() + "mouse clicked :" + e.getClickCount()); 
             }

             @Override
             public void mouseReleased(MouseEvent me) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             }

             @Override
             public void mouseEntered(MouseEvent me) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             }

             @Override
             public void mouseExited(MouseEvent me) {
                 //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             }

                });
          pane.setBounds(50,80,970,600);
          la.add(pane);
     }       
 
     
     
     
     
           public void chargecoursRecap(JPanel la,int ID_utli1,int etat) throws SQLException
      {
          myConnection = init();
          Statement util = null;
          ResultSet resulutil = null;
          ResultSet resulutil2 = null;
          
          int nbr_cours = 0; //nombre de cours
          
          System.out.println("OUI");
          ArrayList<String> noms = new ArrayList<String>();
          ArrayList<String> groupes = new ArrayList<String>();
     
          ArrayList<ArrayList<ArrayList<String>>> tab = new ArrayList<ArrayList<ArrayList<String>>>();
          util= myConnection.createStatement();
          
          String date_premiere_seance_Str = "";
                       String date_derniere_seance_Str = "";
                       String duree_total_Str = "";
                       String nb_seance_Str = "";
                       ArrayList<String> date_premiere_seance_tab = new ArrayList<String>();
                       ArrayList<String> date_derniere_seance_tab = new ArrayList<String>(); 
                       ArrayList<String> duree_total_tab = new ArrayList<String>();
                       ArrayList<String> nb_seance_tab = new ArrayList<String>();
          
          if(etat==0){
              
              
                   String sql = "SELECT Nom_Cours,Nom_Groupe,ID_Utilisateur FROM seance_groupe sg INNER JOIN seance s ON sg.ID_Seance=s.ID ";
                   sql+="INNER JOIN seance_enseignant se ON se.ID_Seance=sg.ID_Seance ";
                   sql+="INNER JOIN cours co ON s.ID_Cours =co.ID ";
                   sql+="INNER JOIN groupe ON sg.ID_Groupe = groupe.ID ";
                   sql+="INNER JOIN enseignant on enseignant.ID_Cours = co.ID ";
                   sql+="GROUP BY Nom_Cours,Nom_Groupe ";
                   sql += "HAVING ID_Utilisateur ='"+ID_utli1+"'";
                   resulutil = util.executeQuery(sql);
                   
                   
                   while (resulutil.next()) {  //Pour chaque cours avec un groupe
                       
                       
                       
                       
                       String nomCours = resulutil.getString("nom_Cours");
                       String id = resulutil.getString("ID_Utilisateur");
                       String nomGroupe = resulutil.getString("nom_Groupe");
                       nbr_cours = nbr_cours + 1;
                       System.out.println(nomCours +" "+nomGroupe);
                       
                       ArrayList<String> cours_groupe = new ArrayList<String>();
                       noms.add(nomCours);
                       groupes.add(nomGroupe);
                       
                       }
                       
                   for (int h = 0; h<nbr_cours;h++){
                       
                       String nomCours = noms.get(h);
                       String nomGroupe = groupes.get(h);
                       
                        String sql2 = "SELECT sg.ID_Seance,Heure_Debut, Heure_Fin, Date, Nom_Groupe, ID_Utilisateur, Nom_Cours, Etat  ";
                        sql2+=" FROM seance_groupe sg INNER JOIN seance s ON sg.ID_Seance=s.ID  ";
                        sql2+=" INNER JOIN cours ON s.ID_Cours = cours.ID  ";
                        sql2+=" INNER JOIN seance_enseignant se ON se.ID_Seance=sg.ID_Seance  ";
                        sql2+=" INNER JOIN enseignant ON enseignant.ID_Cours = s.ID_Cours ";
                        sql2+=" INNER JOIN groupe on groupe.ID = sg.ID_Groupe ";
                        sql2+=" GROUP BY ID_Seance ";
                        sql2+=" HAVING ID_Utilisateur='"+ID_utli1+"' AND Nom_Groupe='"+nomGroupe+"' AND Etat='passe' AND Nom_Cours='"+nomCours+"'";
                       resulutil2 = util.executeQuery(sql2);
                       
                       
                       //Ajout dans le tableau 
                       ArrayList<ArrayList<String>> tab2 = new ArrayList<ArrayList<String>>();
                       String nom_complet = nomCours+" "+nomGroupe;
                       ArrayList<String> nom_complet_tab = new ArrayList<String>();
                       nom_complet_tab.add(nom_complet);
                       tab2.add(nom_complet_tab);
                      
                       
                       String premiere_Seance = " ";
                       Date date_premiere_seance = new Date(9999,99,99);
                       Date date_derniere_seance = new Date(0000,00,00);
                       int nbr_seance = 0;
                       int duree_total = 0;
                       System.out.println("nbr seances "+nbr_seance);
                       
                       
                       while (resulutil2.next()) {  //Pour chaque séance de ce cours
                           
                           
                           
                           int heure_debut = resulutil2.getInt("Heure_Debut");
                           int heure_fin = resulutil2.getInt("Heure_Fin");
                           Date date = resulutil2.getDate("Date");
                           int duree = heure_fin - heure_debut;
                           
                           nbr_seance = nbr_seance + 1;  //Calcul du nombre de séances*
                           System.out.println("seance "+nbr_seance);
                           duree_total = duree_total + duree;  //Calcul de la durée totale
                           System.out.println("nbr seances "+nbr_seance);
                           
                           if(date.before(date_premiere_seance)){ //On récupère la date de première séance
                               date_premiere_seance = date;
                           }
                           
                           if(date.after(date_derniere_seance)){ //On récupère la date de première séance
                               date_derniere_seance = date;
                           }
                           
                           //Conversions en String
                           String heure_debut_Str = String.valueOf(heure_debut);
                           String heure_fin_Str = String.valueOf(heure_fin);
                           String date_Str = String.valueOf(date);
                           String duree_Str = String.valueOf(duree);
                           date_premiere_seance_Str = String.valueOf(date_premiere_seance);
                           date_derniere_seance_Str = String.valueOf(date_derniere_seance);
                           nb_seance_Str = String.valueOf(nbr_seance);
                           
                           //Ajout dans un tableau
                           ArrayList<String> donnees = new ArrayList<String>();
                           donnees.add(heure_debut_Str);
                           donnees.add(heure_fin_Str);
                           donnees.add(date_Str);
                           donnees.add(duree_Str);
                           

                           
                           //Ajout dans tab2
                           tab2.add(donnees);
                       } 
                       
                       duree_total_Str = String.valueOf(duree_total);

                       date_premiere_seance_tab.add(date_premiere_seance_Str);
                       date_derniere_seance_tab.add(date_derniere_seance_Str);
                       duree_total_tab.add(duree_total_Str);
                       nb_seance_tab.add(nb_seance_Str);
                      System.out.println("nbr seances "+nb_seance_Str);
                       
                       tab2.add(date_premiere_seance_tab);
                       tab2.add(date_derniere_seance_tab);
                       tab2.add(duree_total_tab);
                       tab2.add(nb_seance_tab);
                       
                       tab.add(tab2);
                       
                    
                   }
          }
          
           else if(etat==1)
                   {
                       
                       String sql = "SELECT Nom_Cours,ID_Utilisateur FROM seance_groupe sg INNER JOIN seance s ON sg.ID_Seance=s.ID ";
                   sql+="INNER JOIN seance_enseignant se ON se.ID_Seance=sg.ID_Seance ";
                   sql+="INNER JOIN cours co ON s.ID_Cours =co.ID ";
                   sql+="INNER JOIN groupe ON sg.ID_Groupe = groupe.ID ";
                   sql+="INNER JOIN etudiant on etudiant.ID_Groupe = groupe.ID ";
                   sql+="GROUP BY Nom_Cours ";
                   sql += "HAVING ID_Utilisateur ='"+ID_utli1+"'";
                   resulutil = util.executeQuery(sql);
                   
                   
                   while (resulutil.next()) {  //Pour chaque cours
                       
                       
                       
                       
                       String nomCours = resulutil.getString("nom_Cours");
                       String id = resulutil.getString("ID_Utilisateur");
                       nbr_cours = nbr_cours + 1;
                       System.out.println(nomCours);
                       
                       ArrayList<String> cours_groupe = new ArrayList<String>();
                       noms.add(nomCours);
                       
                       }
                       
                   for (int h = 0; h<nbr_cours;h++){
                       
                       System.out.println("laa");
                       
                       String nomCours = noms.get(h);
                       
                        String sql2 = "SELECT sg.ID_Seance,Heure_Debut, Heure_Fin, Date, ID_Utilisateur, Nom_Cours, Etat  ";
                        sql2+=" FROM seance_groupe sg INNER JOIN seance s ON sg.ID_Seance=s.ID  ";
                        sql2+=" INNER JOIN cours ON s.ID_Cours = cours.ID  ";
                        sql2+=" INNER JOIN seance_enseignant se ON se.ID_Seance=sg.ID_Seance  ";
                        sql2+=" INNER JOIN groupe on groupe.ID = sg.ID_Groupe ";
                        sql2+=" INNER JOIN etudiant on etudiant.ID_Groupe = groupe.ID  ";
                        sql2+=" GROUP BY ID_Seance ";
                        sql2+=" HAVING ID_Utilisateur='"+ID_utli1+"' AND Etat='passe' AND Nom_Cours='"+nomCours+"'";
                       resulutil2 = util.executeQuery(sql2);
                       
                       
                       //Ajout dans le tableau 
                       ArrayList<ArrayList<String>> tab2 = new ArrayList<ArrayList<String>>();
                       String nom_complet = nomCours;
                       ArrayList<String> nom_complet_tab = new ArrayList<String>();
                       nom_complet_tab.add(nom_complet);
                       tab2.add(nom_complet_tab);
                      
                       
                       Date date_premiere_seance = new Date(9999,99,99);
                       Date date_derniere_seance = new Date(0000,00,00);
                       int nbr_seance = 0;
                       int duree_total = 0;
                       System.out.println("nbr seances "+nbr_seance);
                       
                       
                       while (resulutil2.next()) {  //Pour chaque séance de ce cours
                           
                           
                           
                           int heure_debut = resulutil2.getInt("Heure_Debut");
                           int heure_fin = resulutil2.getInt("Heure_Fin");
                           Date date = resulutil2.getDate("Date");
                           int duree = heure_fin - heure_debut;
                           
                           nbr_seance = nbr_seance + 1;  //Calcul du nombre de séances*
                           System.out.println("seance "+nbr_seance);
                           duree_total = duree_total + duree;  //Calcul de la durée totale
                           System.out.println("nbr seances "+nbr_seance);
                           
                           if(date.before(date_premiere_seance)){ //On récupère la date de première séance
                               date_premiere_seance = date;
                           }
                           
                           if(date.after(date_derniere_seance)){ //On récupère la date de première séance
                               date_derniere_seance = date;
                           }
                           
                           //Conversions en String
                           String heure_debut_Str = String.valueOf(heure_debut);
                           String heure_fin_Str = String.valueOf(heure_fin);
                           String date_Str = String.valueOf(date);
                           String duree_Str = String.valueOf(duree);
                           date_premiere_seance_Str = String.valueOf(date_premiere_seance);
                           date_derniere_seance_Str = String.valueOf(date_derniere_seance);
                           nb_seance_Str = String.valueOf(nbr_seance);
                           
                           //Ajout dans un tableau
                           ArrayList<String> donnees = new ArrayList<String>();
                           donnees.add(heure_debut_Str);
                           donnees.add(heure_fin_Str);
                           donnees.add(date_Str);
                           donnees.add(duree_Str);
                           

                           
                           //Ajout dans tab2
                           tab2.add(donnees);
                       } 
                       
                       duree_total_Str = String.valueOf(duree_total);

                       date_premiere_seance_tab.add(date_premiere_seance_Str);
                       date_derniere_seance_tab.add(date_derniere_seance_Str);
                       duree_total_tab.add(duree_total_Str);
                       nb_seance_tab.add(nb_seance_Str);
                      System.out.println("nbr seances "+nb_seance_Str);
                       
                       tab2.add(date_premiere_seance_tab);
                       tab2.add(date_derniere_seance_tab);
                       tab2.add(duree_total_tab);
                       tab2.add(nb_seance_tab);
                       
                       tab.add(tab2);
                       
                    
                   }
                 
                   }
      
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
                
                
                la.add(cours);
                la.add(premier);
                la.add(dernier);
                la.add(nbrcours);
                la.add(total);
                
          for (int j = 0;j < nbr_cours;j++){
              
              
                JComboBox jcombo = new JComboBox();
                int size = tab.get(j).size();
                
                
                
                
                
                //Titre de la JcomboBox = Infos générales du cours
                String titre = tab.get(j).get(0).get(0)+"                                             "
                        +tab.get(j).get(size-4).get(j)+"                                             "
                        + tab.get(j).get(size-3).get(j)+"                                              "
                        +tab.get(j).get(size-1).get(j)+"                                            "
                        +tab.get(j).get(size-2).get(j)+" h";
                
                jcombo.addItem(titre);
                String info = new String();

               
                //Infos des différentes séances
                for (int k = 1; k < size-4 ; k++){
                    
                    info = "            Date:  "+tab.get(j).get(k).get(2)+"                                                     Début:  "
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

        
          } 
          
      }
     
     
     
     
     
      
      public static Connection init(){
    
       try{
        
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = myConnection=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/essaiprojet","root", ""
                );
        return conn;
        }
        catch(Exception e){
            System.out.println("Failed to get connection");
            e.printStackTrace();
        }
       return null ;
      }
       
       public Connection getMyConnection(){
           
        return myConnection;
       }
       
       
    
    public void close(ResultSet rs){
        
        if(rs !=null){
            try{
               rs.close();
            }
            catch(Exception e){}
        
        }
    }
    
     public void close(java.sql.Statement stmt){
        
        if(stmt !=null){
            try{
               stmt.close();
            }
            catch(Exception e){}
        
        }
    }
     
  public void destroy(){
  
    if(myConnection !=null){
    
         try{
               myConnection.close();
            }
            catch(Exception e){}
        
        
    }
  }

    @Override
    public void actionPerformed(ActionEvent ae) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
   

    }

    
    

