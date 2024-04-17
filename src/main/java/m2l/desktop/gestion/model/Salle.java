package m2l.desktop.gestion.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author nathalie
 */
public class Salle {

    protected String nom;
    protected int capacite;
    public Rectangle r;
    public String batiment;


    /**
     * Constructeur qui initialise le nom
     * @param n : le nom de la salle
     */
    public Salle(String n){
        this.nom = n;
    }

    public Salle(String n, int c) {
        this.nom = n;
        this.capacite = c;
    }

    public Salle(String n, Rectangle r, String b)
    {
        this.nom = n;
        this.r = r;
        this.batiment = b;
    }

    /**
     * Accesseur (getter) renvoie
     * la valeur de type "chaîne"
     * contenue dans l'attribut de
     * type "SimpleStringProperty"
     * @return : le nom de la salle
     */
    public String getNom() {
        return nom;
    }

    /**
     * Mutateur (setter) modifie le nom
     * de la salle
     * @param nom : nom de la salle
     */
    public void setNom(String nom) {
        this.nom=nom;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite=capacite;
    }

}
