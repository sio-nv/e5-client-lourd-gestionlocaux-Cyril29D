/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m2l.desktop.gestion.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author nathalie
 */
public class Intervenant
{

    private String nom, prenom;
    private int telephone;

    /**
     * Constructeur qui initialise le nom
     * @param n : le nom de l'intervenant
     */
    public Intervenant(String n){
        this.nom = n;
    }

    public Intervenant(String n, String p, int t)
    {
        this.nom = n;
        this.prenom = p;
        this.telephone = t;
    }

    /**
     * Accesseur (getter) renvoie
     * la valeur de type "chaîne"
     * contenue dans l'attribut de
     * type "SimpleStringProperty"
     * @return : le nom de l'intervenant
     */
    public String getNom() {
        return nom;
    }

    /**
     * Mutateur (setter) modifie le nom
     * de la salle
     * @param nom : nom de l'intervenant
     */
    public void setNom(String nom) {
        this.nom=nom;
    }

    /**
     * Accesseur (getter) renvoie
     * la valeur de type "chaîne"
     * contenue dans l'attribut de
     * type "SimpleStringProperty"
     * @return : le prénom de l'intervenant
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Mutateur (setter) modifie le nom
     * de la salle
     * @param prenom : prénom de l'intervenant
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone=telephone;
    }

    @Override
    public String toString() {

        return (prenom + " " + nom);
    }

}
