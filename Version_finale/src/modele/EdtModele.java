/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import controlleur.AuthentificationControleur;
import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import static modele.Authentification.init;
import vue.View;

/**
 *
 * @author lele1
 */
public class EdtModele {
    AuthentificationControleur control;
    Connection myConnection;
    public EdtModele() throws SQLException
    {}
    
    public int recherche(String envoie) 
      {    int IDRecup;
           myConnection = Authentification.init();
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
                       if("1".equals(EtatRecup1)) EtatRecup=1;
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
           System.out.println("etat llaaaaaaaaaaaaaaaaaa :"+etat);
         
          
          if ((etat == 0)||(etat == 1)||(etat == 2)){
          control= new AuthentificationControleur();
           myConnection = init();
           int id_groupe2=1;
           Statement util ,statement1 = null;
           ResultSet resulutil = null ,resul1= null;
           Object data[][] = new  Object [11][7];
           util= myConnection.createStatement();
           statement1= myConnection.createStatement();
           System.out.println("Semaine : "+semaine+" id groupe = "+ID_utli1);
           if(etat==1)
                   {
                      String sql1="SELECT * FROM utilisateur INNER JOIN etudiant on utilisateur.ID=etudiant.ID_Utilisateur WHERE ID='"+ID_utli1+"'";
                     
                      resul1 = statement1.executeQuery(sql1);
                      if(resul1.next())
                      {
                        id_groupe2=resul1.getInt("ID_Groupe");
                      }
                       
                       
                    String sql = "SELECT * FROM seance_groupe s INNER JOIN seance se ON s.ID_Seance=se.ID ";
                   sql+=" INNER JOIN seance_enseignant sa ON sa.ID_Seance=s.ID_Seance";
                   sql+=" INNER JOIN enseignant en ON en.ID_Utilisateur=sa.ID_Enseignant";
                   sql+=" INNER JOIN utilisateur ut ON ut.ID=en.ID_Utilisateur";
                   sql+=" INNER JOIN seance_salle sea ON sea.ID_Seance=se.ID";
                   sql+=" INNER JOIN salle sal ON sal.ID=sea.ID_Salle";
                   sql+=" INNER JOIN cours co ON en.ID_Cours =co.ID";
                   sql += "  WHERE ID_Groupe ='"+id_groupe2+"' AND se.Semaine='"+semaine+"'";
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
      
                
        control.tableau(table,la,data);
        

      }}
      
      
       public boolean ajout(View aThis,JTextField Date, String jour1, JTextField Semaine, JTextField Heuredeb, JTextField HeureFin, String Etat, String cours, int Groupe,String Prof,String Salle,int id_prof,int id_cours,int id_salle) throws SQLException
     {
             boolean retour ; 
            
             Connection myco;
             myco=Authentification.init();
             Statement statement,statement2,statementhu,statementha,statementho,statementhi;
             ResultSet resultat,resultathu,resultatha,resultatho,resultathi;
             
           
            String date=Date.getText();
            

          // String date1 = new SimpleDateFormat("yyyy-MM-dd").format(getDate().getText());
            
            int Semaine1 = 0,heuredeb = 0,heurefin = 0;
            String semaine=Semaine.getText();
            String heuredeb1=Heuredeb.getText();
            String heurefin1=HeureFin.getText();
            
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
            
             
             System.out.println(heuredeb+" "+heurefin);
             
            
             
             if(Semaine1!=0&&(((8==heuredeb))&&(10==heurefin))||((10==heuredeb)&&(12==heurefin))||((12==heuredeb)&&(14==heurefin))||((14==heuredeb)&&(16==heurefin))||((16==heuredeb)&&(18==heurefin))||((18==heuredeb)&&(20==heurefin)))
             {    
            
                 //vérification qu'une séance ne se passe pas déjà en même temps : groupe
                statementhu = myco.createStatement();
                String sqlhu = "SELECT * FROM seance INNER JOIN seance_groupe ON seance.ID=seance_groupe.ID_Seance WHERE ID_Groupe ='"+Groupe+"' AND Heure_Debut='"+heuredeb+"' AND Heure_Fin='"+heurefin+"' AND Jour='"+jour+"' AND Semaine='"+Semaine1+"'";
                resultathu = statementhu.executeQuery(sqlhu);
                 
                if(resultathu.next()){   JOptionPane.showMessageDialog(null,"Horraires déjà remplies pour ce Groupe!","Error",JOptionPane.PLAIN_MESSAGE);   }
                 
                else{
                 
                  //vérification qu'une séance ne se passe pas déjà en même temps : salle 
                statementha = myco.createStatement();
                String sqlha = "SELECT * FROM seance INNER JOIN seance_salle ON seance.ID=seance_salle.ID_Seance WHERE ID_Salle ='"+id_salle+"' AND Heure_Debut='"+heuredeb+"' AND Heure_Fin='"+heurefin+"' AND Jour='"+jour+"' AND Semaine='"+Semaine1+"'";
                resultatha = statementha.executeQuery(sqlha);
                 
                if(resultatha.next()){   JOptionPane.showMessageDialog(null,"La salle est déjà remplie!","Error",JOptionPane.PLAIN_MESSAGE);   }
                 
                else{
                    
                   //vérification qu'une séance ne se passe pas déjà en même temps : enseignant 
                statementho = myco.createStatement();
                String sqlho = "SELECT * FROM seance INNER JOIN seance_enseignant ON seance.ID=seance_enseignant.ID_Seance WHERE ID_Enseignant ='"+id_prof+"' AND Heure_Debut='"+heuredeb+"' AND Heure_Fin='"+heurefin+"' AND Jour='"+jour+"' AND Semaine='"+Semaine1+"'";
                resultatho = statementho.executeQuery(sqlho);
                 
                if(resultatho.next()){   JOptionPane.showMessageDialog(null,"Cet enseignant n'est pas libre pour cette semaine et ces horraires !","Error",JOptionPane.PLAIN_MESSAGE);   }
                 
                else{   
                
                   //vérification qu'une séance ne se passe pas déjà en même temps : salle 
                statementhi = myco.createStatement();
                String sqlhi = "SELECT * FROM  enseignant WHERE ID_Cours ='"+id_cours+"' AND ID_Utilisateur='"+id_prof+"'";
                resultathi = statementhi.executeQuery(sqlhi);
                 
                if(!resultathi.next()){   JOptionPane.showMessageDialog(null,"Cet enseignant n'enseigne pas ce cours !","Error",JOptionPane.PLAIN_MESSAGE);   }
                 
                else{        
                      
                 
             statement = myco.createStatement();
             String sql = "INSERT INTO seance(`Date`, `Jour`, `Semaine`, `Heure_Debut`, `Heure_Fin`, `Etat`, `ID_Cours`, `ID_Type`) VALUES ('"+date+"','"+jour+"','"+Semaine1+"','"+heuredeb+"','"+heurefin+"','"+Etat+"','"+id_cours+"','1')";
             Seance nouvelle = new Seance(date,jour,Semaine1,heuredeb,heurefin,Etat,id_cours,1);
             
              PreparedStatement statement3 = myco.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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
             retour = true;
             System.out.println("retour : "+retour);
             return retour;
             
             }}}}}}else {   JOptionPane.showMessageDialog(null,"Erreur entrée Heure, incompatible ou les horraires ne se suivent pas","Error",JOptionPane.PLAIN_MESSAGE);
             retour= false;
             System.out.println("retour : "+retour);
              return retour;}
        return false;
           
       
   
}

    public void modifetat(String NouveauType, String idseance) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Statement statement;
        int nouveau_id=0;
         Connection myConnection;
         myConnection=Authentification.init();
        statement = myConnection.createStatement();
        if("TP".equals(NouveauType)||"TD".equals(NouveauType)||"Cours".equals(NouveauType))
        {if("TP".equals(NouveauType))
        {nouveau_id=1;}
        if("TD".equals(NouveauType))
        {nouveau_id=2;}
        if("Cours".equals(NouveauType))
        {nouveau_id=3;}
        
        
        statement.executeUpdate("UPDATE seance SET ID_Type='"+nouveau_id+"' WHERE ID='"+idseance+"'");}
        else{ JOptionPane.showMessageDialog(null,"Veuillez rentrer TP, TD ou Cours SVP !","Error",JOptionPane.PLAIN_MESSAGE);} 
    }

    public void ajout_enseignant(String NouveauEn, String idseance) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         Statement statement,statementhf,statementhu,statementj,statementji;
         ResultSet resultathi,resultathu,resultatj,resultatji,resultathf;
         Connection myConnection;
         int id_cours = 0;
         int semaine = 0 ,heuredeb=0,heurefin=0,jour=0;
          myConnection=Authentification.init();
          statement=statementhf=statementhu=statementj=statementji = myConnection.createStatement();
          String sqlj = "SELECT * FROM seance WHERE ID ='"+idseance+"'";
                resultatj = statementj.executeQuery(sqlj);
                while(resultatj.next())
                {
                 id_cours=resultatj.getInt("ID_Cours");
                }
          
          String sqlhu = "SELECT * FROM seance_enseignant see INNER JOIN enseignant en  ON en.ID_Utilisateur= see.ID_Enseignant WHERE ID_Cours ='"+id_cours+"' AND ID_Utilisateur='"+NouveauEn+"'";
                resultathu = statementhu.executeQuery(sqlhu);
      
                if(!resultathu.next()){   JOptionPane.showMessageDialog(null,"Cet enseignant n'enseigne pas ce cours !","Error",JOptionPane.PLAIN_MESSAGE);   }
                 
                else{  
                    
           String sqlji = "SELECT * FROM seance WHERE ID ='"+idseance+"'";
                resultatji = statementji.executeQuery(sqlji);
                while(resultatji.next())
                {
                 semaine=resultatji.getInt("Semaine");
                 heuredeb=resultatji.getInt("Heure_Debut");
                 heurefin=resultatji.getInt("Heure_fin");
                 jour=resultatji.getInt("Jour");
                }
                
         String sqlf= "SELECT * FROM seance INNER JOIN seance_enseignant ON seance.ID=seance_enseignant.ID_Seance WHERE Semaine='"+semaine+"' AND Jour ='"+jour+"' AND Heure_Debut='"+heuredeb+"' AND Jour= '"+jour+"' AND ID_Enseignant='"+NouveauEn+"'" ;      
          resultathf = statementhf.executeQuery(sqlf);       
        myConnection=Authentification.init();
        statement = myConnection.createStatement();
         if(resultathf.next()){   JOptionPane.showMessageDialog(null,"Cet enseignant n'est pas disponible à cette date !","Error",JOptionPane.PLAIN_MESSAGE);   }
                
                else{         
                
          
          statement.executeUpdate("INSERT INTO seance_enseignant"+ " VALUES ('"+NouveauEn+"','"+idseance+"')");
          System.out.println("INSERT INTO seance_enseignant VALUES ('"+NouveauEn+"','"+idseance+"')");}}
    }

    public void modif_nomcours(String Changement, String substring) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Statement statement;
        Connection myConnection;
        myConnection=Authentification.init();
        statement = myConnection.createStatement();
        String query = "UPDATE cours SET Nom_Cours='"+Changement+"'WHERE Nom_Cours='"+substring+"'";
        statement.executeUpdate(query) ; 
    }

    public void supprimer(String groupe, String idseance, String supenseigant) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         Connection myConnection;
         Statement statement;
         System.out.println("tu es dans supprimer ! groupe ="+groupe+" enseignant ="+supenseigant);
                       myConnection=Authentification.init();
                        //System.out.print("co okkkkk");
                       statement = myConnection.createStatement();
                       
                                if( groupe.equals("O")){
                                 System.out.println("ici?");
                                statement.execute("DELETE FROM seance_groupe WHERE ID_Seance='"+idseance+"'");
                                //Supprimerseance.setVisible(false);
                                }
                                else if(supenseigant.equals("O"))
                                {
                                statement.execute("DELETE FROM seance_enseignant WHERE ID_Seance='"+idseance+"'");
                                //Supprimerseance.setVisible(false);
                                }
                                else if(groupe.equals("N"))
                                {
                                    System.out.println("Vous supprimez pas de groupe");
                                    // Supprimerseance.setVisible(false);
                                }
                                else if(supenseigant.equals("N") )
                                {
                                    System.out.println("Vous supprimez pas d'enseignant");
                                    // Supprimerseance.setVisible(false);
                                }
    }

    public void ajout_groupe(String valueOf, String ind) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       
        Connection myConnection;
         Statement statement,statementhi,statementhu,statementj;
         ResultSet resultathi,resultathu,resultatj;
         int semaine = 0 ,heuredeb=0,heurefin=0,jour=0;
          myConnection=Authentification.init();
          statement=statementhi=statementhu=statementj = myConnection.createStatement();
          String sqlj = "SELECT * FROM seance WHERE ID ='"+ind+"'";
                resultatj = statementj.executeQuery(sqlj);
                while(resultatj.next())
                {
                 semaine=resultatj.getInt("Semaine");
                 heuredeb=resultatj.getInt("Heure_Debut");
                 heurefin=resultatj.getInt("Heure_fin");
                 jour=resultatj.getInt("Jour");
                }
                
         String sqlf= "SELECT * FROM seance INNER JOIN seance_groupe ON seance.ID=seance_groupe.ID_Seance WHERE Semaine='"+semaine+"' AND Jour ='"+jour+"' AND Heure_Debut='"+heuredeb+"' AND Jour= '"+jour+"' AND ID_Groupe='"+valueOf+"'" ;      
          resultathu = statementhu.executeQuery(sqlf);       
        myConnection=Authentification.init();
        statement = myConnection.createStatement();
         if(resultathu.next()){   JOptionPane.showMessageDialog(null,"Ce groupe n'est pas disponible à cette date !","Error",JOptionPane.PLAIN_MESSAGE);   }
                
                else{
        
        int query =  statement.executeUpdate("INSERT INTO seance_groupe"+ " VALUES ('"+ind+"','"+valueOf+"')");
         }
       // statement.executeUpdate(query) ;
    }
  
     
}
