/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author lele1
 */

import controlleur.AuthentificationControleur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.util.Observable;
import vue.AuthentificationVue;
/**
 *
 * @author lele1
 */
public class Authentification extends  Observable implements ActionListener {
    
    int typeU; // 1 = étudiant , 2= Enseignant 
    static JTextField login1,mdp1 ;  
    Statement statement;
    ResultSet resultat,resultat1,resultat2;
    int ID;
    String Email ,Passwd, Nom, Prenom, Droit;
    AuthentificationControleur control = new AuthentificationControleur();
    
      static Connection myConnection; 
      
      public Authentification() throws SQLException{}
      
 
      
      public void verification(AuthentificationVue connct,String login, String mdp) throws SQLException
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
                     //on ferme la fenetre d'avant
                   
                    ID= resultat.getInt("ID");
                    Email=resultat.getString("Mail");
                    Passwd= resultat.getString("Mdp");
                    Droit=resultat.getString("Droit");
                    Prenom= resultat.getString("Prenom");
                    Nom=resultat.getString("Nom");
                    
                    
                    System.out.println("ID de l'utilisateur = "+ ID);
                    System.out.println("Droit de l'utilisateur = "+ Droit);
                    
                    
                    if("1".equals(Droit)){//si l'utilisateur est un étudiant
                    statement = myConnection.createStatement();
                    String sql1 = "SELECT * FROM etudiant WHERE ID_Utilisateur ='"+ID+"'";
                    resultat = statement.executeQuery(sql1);
                 
                    if(resultat.next()){
                     
                    int ID_Groupe = resultat.getInt("ID_Groupe");
                    //Etudiant nouveau = new Etudiant(ID,Email,Passwd,Prenom,Nom,Droit,ID_Groupe);
                    control.Etudiant(ID,Email,Passwd,Prenom,Nom,Droit,ID_Groupe);
                    connct.dispose();
                    }}
                    
                  
                    if("2".equals(Droit)){ //Si l'utilsateur est un enseignant
                     control.Enseignant(ID,Email,Passwd,Prenom,Nom,Droit);
                     connct.dispose();
                    //Enseignant nouveau = new Enseignant(ID,Email,Passwd,Prenom,Nom,Droit);
                    }
                    
                    if("0".equals(Droit)){ //Si l'utilsateur est un admin
                     control.Admin(ID,Email,Passwd,Prenom,Nom,Droit);
                     connct.dispose();
                    //Enseignant nouveau = new Enseignant(ID,Email,Passwd,Prenom,Nom,Droit);
                    }
                    
                    if("3".equals(Droit)){ //Si l'utilsateur est un enseignant référent
                     control.EnseignantRef(ID,Email,Passwd,Prenom,Nom,Droit);
                     connct.dispose();
                    //Enseignant nouveau = new Enseignant(ID,Email,Passwd,Prenom,Nom,Droit);
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

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
      
      public static Connection init(){ //lien avec la 
    
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

    

    
   

    }

    
    


