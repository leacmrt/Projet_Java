package controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import modele.Modele;
import vue.Vue;

/**
 *
 * @author lele1
 */
class  Controleur implements ActionListener { 
    Modele modele;
    Vue vue;

    
     Controleur(Modele modele, Vue vue) {
	this.modele = modele;
	this.vue = vue;
    }
     
     
     public static void main(String[] arg) throws SQLException {
	Modele modele = new Modele();
	Vue vue = new Vue(modele);
	Controleur controleur =  new Controleur(modele, vue);

        
     }
    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
