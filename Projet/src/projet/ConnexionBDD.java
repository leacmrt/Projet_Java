/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
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

    
    

