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
    //public Connection conn;
    
    static JTextField login1,mdp1 ;  
    Statement statement;
    ResultSet resultat;
    
    
      static Connection myConnection; 
      
      public ConnexionBDD() throws SQLException{
          System.out.print("Coucou");         
      }
      
     
      
      public void verification(String login, String mdp)
      {
          
         myConnection = init();
            try{
                System.out.print("allez la");
                statement = myConnection.createStatement();
                String sql = "SELECT Nom FROM Prenom WHERE Nom ='"+login+"'";
                resultat = statement.executeQuery(sql);
                 
                if(resultat.next()){
                     
                    String motDePasse = resultat.getString(1);
         
                if(motDePasse.equals(mdp)){
             
                    JOptionPane.showMessageDialog(null,"Connexion réussie ! ","Success",JOptionPane.PLAIN_MESSAGE);
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
        System.out.print("coucou2");
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
           System.out.print("allez la3");
        return myConnection;
       }
       public String getNom(Connection conn ) throws SQLException
       {      String NewNom = null; 
           Statement state = conn.createStatement();
           ResultSet result = state.executeQuery("SELECT Nom FROM prenom");
           ResultSetMetaData resultMeta = result.getMetaData();
           return NewNom; 
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

    
    

