/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;


import java.awt.Color;
import static java.awt.Color.blue;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import static javafx.scene.paint.Color.GREY;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableCellRenderer;
/**
 *
 * @author lele1
 */
public class ConnexionBDD implements ActionListener  {
    
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
      
      public void chargecours(int semaine,JPanel la,int ID_utli1,int etat) throws SQLException
      {
           myConnection = init();
           Statement util = null;
           ResultSet resulutil = null;
          
    
     
          String data[][] = new String[11][6];
           util= myConnection.createStatement();
           
           if(etat==1)
                   {String sql = "SELECT * FROM seance_groupe s INNER JOIN seance se ON s.ID_Seance=se.ID ";
                   sql+=" INNER JOIN seance_enseignant sa ON sa.ID_Seance=s.ID_Seance";
                   sql+=" INNER JOIN enseignant en ON en.ID_Utilisateur=sa.ID_Enseignant";
                   sql+=" INNER JOIN utilisateur ut ON ut.ID=en.ID_Utilisateur";
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
                   sql += "  WHERE ut.ID ='"+ID_utli1+"' AND se.Semaine='"+semaine+"'";
                   resulutil = util.executeQuery(sql);
                   
           }
           
           
               int i = 0;
               /*JLabel h1=new JLabel("8h30");
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
               la.add(h7);*/
            
                
                
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
                Seance sea = new Seance(id,date,jour,semaine1,Heure_deb,Heure_fin,Etat,id_cours,id_groupe);
                
                
                JTextArea textArea = new JTextArea(5, 20);
                String remplir =" "+cours+"- "+nom;
                textArea.append(remplir);
                data[i][jour] =remplir;
               
               
                
                }
                resulutil.close();
                
          String columns[] = {"Lundi", "Mardi", "Mercredi","Jeudi","Vendredi","Samedi" };
       
        
         
          DefaultTableModel model = new DefaultTableModel(data, columns);
          
          JTable table = new JTable(model) {
        @Override
        public Component prepareRenderer(TableCellRenderer renderer, int rowIndex,  int columnIndex) { //colorie les cases avec cours 
            JComponent component = (JComponent) super.prepareRenderer(renderer, rowIndex, columnIndex);  
            String la= (String)this.getValueAt(rowIndex, columnIndex);
             JComponent essai=(JComponent) renderer.getTableCellRendererComponent(this, la, false,true ,rowIndex, columnIndex);
            if(la!=null&&!("".equals(la)))
            {
             int java = la.indexOf("JAVA");//faire ça pour tout les cours 
             int web = la.indexOf("DYNAMIQUE");//pour l'instant flemme 
             int anglais = la.indexOf("ANGLAIS");
             
             if (java>0) essai.setBackground(new Color(247,191,131) );
             else if (web>0) essai.setBackground(new Color(247,234,211) );
             else if (anglais>0) essai.setBackground(new Color(131,144,247));
             else essai.setBackground(Color.lightGray);
             
             this.setForeground(Color.black);
             
             
            }else if(!essai.hasFocus()) essai.setBackground(null);
            
            if(rowIndex==1||rowIndex==3||rowIndex==5||rowIndex==7||rowIndex==9)
            essai.setBackground(new Color(248,176,176));
   
            return component;
        }
           
        
        
          };
         remplirtableau(table,la);
          /*for(int u=0;u<11;u++)
          {if(u==1||u==3||u==5||u==7||u==9){
              table.setRowHeight(u,15);
          }
          else table.setRowHeight(u,84);
          }
          
          table.setColumnSelectionAllowed(true);
          table.setShowGrid(true);
          table.setShowVerticalLines(true);
         
          
          
           
          JScrollPane pane = new JScrollPane(table);
          pane.setBounds(50,80,970,600);
          la.add(pane);*/

      }
  
     public void remplirtableau(JTable table, JPanel la)
     {
         
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
          
          table.setColumnSelectionAllowed(true);
          table.setShowGrid(true);
          table.setShowVerticalLines(true);
     
     
          JScrollPane pane = new JScrollPane(table);
          pane.setBounds(50,80,970,600);
          la.add(pane);
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    }

    
    

