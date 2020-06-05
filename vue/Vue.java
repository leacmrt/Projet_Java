/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import modele.Modele;

/**
 *
 * @author lele1
 */
public class Vue extends JFrame implements Observer {

    private Modele modele;

    public Vue(Modele modele) throws SQLException {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       this.modele = modele;
       AuthentificationVue essai1 = new AuthentificationVue();//On se connecte 
    }
    
    

    @Override
    public void update(Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
