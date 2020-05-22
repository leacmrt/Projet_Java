/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;
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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
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
                    
                    Etudiant nouveau = new Etudiant(ID,Email,Passwd,Prenom,Nom,Droit);
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
      
      public void chargecours(int semaine,JPanel la) throws SQLException
      {
           myConnection = init();
           Statement util = null;
           ResultSet resulutil;
           
    
     
          String data[][] = new String[8][6];
           util= myConnection.createStatement();
           String sql = "SELECT * FROM seance_groupe INNER JOIN seance ON seance_groupe.ID_Groupe=seance.ID  WHERE ID_Groupe ='1'";
           resulutil = util.executeQuery(sql);
           ResultSetMetaData resultMeta = resulutil.getMetaData();
                 
            
               
                int i = 0;
                
                while (resulutil.next()) { //on remplit le tableau
                int id = resulutil.getInt("ID");
                int semaine1 = resulutil.getInt("Semaine");
                int Heure_deb = resulutil.getInt("Heure_Debut");
                int Heure_fin = resulutil.getInt("Heure_Fin");   
                String etat = resulutil.getString("Etat");
                int id_cours = resulutil.getInt("ID_Cours");
                int id_groupe = resulutil.getInt("ID_Groupe");
                
                Seance sea = new Seance(id,semaine1,Heure_deb,Heure_fin,etat,id_cours,id_groupe);
                
                String newLine = System.getProperty("line.separator");
                JTextArea textArea = new JTextArea(5, 20);
                String remplir =" Debut "+Heure_deb+newLine+"\n Fin "+Heure_fin+newLine+ "\n Etat : "+etat+""+id_cours+newLine+" \n Groupe :"+id_groupe+ "";
                textArea.append(remplir);
                //data[i][0] =remplir;
                
                
                //System.out.print(data[i][0]);
                i++;
                }
                
        String columns[] = { "Lundi", "Mardi", "Mercredi","Jeudi","Vendredi","Samedi" };
       
        
        
          DefaultTableModel model = new DefaultTableModel(data, columns);
          JTable table = new JTable(model);
          table.setShowGrid(true);
          table.setShowVerticalLines(true);
          table.setRowHeight(75);
          JScrollPane pane = new JScrollPane(table);
          pane.setBounds(10,60,950,600); 
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

    
    

