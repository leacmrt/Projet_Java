/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

/**
 *
 * @author lele1
 */
public class Seance {
    private int id ;
    private int date;
    private int jour;
    private int semaine1;
    private int Heure_deb;
    private int Heure_fin;   
    private String etat;
    private int id_cours;
    private int id_groupe;
    
    public Seance(int NewID,int NewDate,int NewJour, int NewSemaine,int NewDeb,int NewFin,String NewEtat,int NewIDc,int NewIDg)
    {
    NewID= id ;
    NewDate= date;
    NewJour=jour;
    NewSemaine= semaine1;
    NewDeb=Heure_deb;
    NewFin= Heure_fin;   
    NewEtat= etat;
    NewIDc= id_cours;
    NewIDg= id_groupe;
    System.out.println("nouvelle seance");
    }
    
    
    
}
