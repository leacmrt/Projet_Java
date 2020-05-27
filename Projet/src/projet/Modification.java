/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author lele1
 */
class Modification extends JPanel implements ItemListener{
    
    public Modification(View aThis,int ID_Utilisateur,String droit,int etat)
    {
    JButton etu= new JButton("ETUDIANT");
    JButton co = new JButton("COURS ");
    JButton se=new JButton(" SEANCE ");
    JComboBox choix = new JComboBox();
    choix.addItem("Ajouter");
    choix.addItem("Supprimer");
    choix.addItem("Modifier");

    choix.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
                    ActionEvent a = null;
                    String selected= (String)choix.getSelectedItem();
                    System.out.println("Selection : "+selected);
                    
        }});
   
    choix.setBounds(150,330, 130, 30);
    etu.setBounds(10,50, 130, 30);
    co.setBounds(10,220, 130, 30);
    se.setBounds(10,330, 130, 30);
    
    aThis.add(choix);
    aThis.add(etu);
    aThis.add(co);
    aThis.add(se);
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}
