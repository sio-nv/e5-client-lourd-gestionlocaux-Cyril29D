package m2l.desktop.gestion.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class Climatiseur {

    private String modele;
    private int id;
    private String marque;
    private int  puissance;
    private int surface_min;
    private int  surface_max;
    private int numalle;

    private String nomSalle;
    private String batiment;



    public Climatiseur(String marque, String modele, int puissance) {
        this.puissance =puissance;
        this.marque = marque;
        this.modele = modele;


    }

    public Climatiseur(int i,String mar, String mod, int pui, int smi, int sma) {
        this.setMarque( mar);
        this.setModele( mod);
        this.setPuissance(pui);
        this.setSurface_max(sma);
        this.setSurface_min(smi);
        this.id = i;
    }

    public int getSurfaceMaxCouverte() {
        switch (this.puissance) {
            case 7000:
                return 15;
            case 9000:
                return 25;
            case 12000:
                return 35;
            case 18000:
                return 50;
            case 24000:
                return 70;
            case 30000:
                return 80;
        }
        return 0;
    }

    public int getSurfaceMinCouverte() {
        switch (this.puissance) {
            case 7000:
                return 7;
            case 9000:
                return 15;
            case 12000:
                return 25;
            case 18000:
                return 35;
            case 24000:
                return 50;
            case 30000:
                return 70;
        }
        return 0;
    }

    public List<Integer> getSurfaceCouverte() {


        List<Integer> surfaces = new ArrayList<>(2);

        switch (this.puissance) {
            case 7000:
                surfaces.add(7);
                surfaces.add(15);
                break;
            case 9000:
                surfaces.add(15);
                surfaces.add(25);
                break;
            case 12000:
                surfaces.add(25);
                surfaces.add(35);
                break;
            case 18000:
                surfaces.add(35);
                surfaces.add(50);
                break;
            case 24000:
                surfaces.add(50);
                surfaces.add(70);
                break;
            case 30000:
                surfaces.add(70);
                surfaces.add(80);
                break;
            default:
                surfaces.add(7);
                surfaces.add(15);
                break;
        }
        return surfaces;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public int getPuissance() {
        return puissance;
    }

    public void setPuissance(int puissance) {
        this.puissance = puissance;
    }

    public int getSurface_min() {
        return surface_min;
    }

    public void setSurface_min(int surface_min) {
        this.surface_min = surface_min;
    }

    public int getSurface_max() {
        return surface_max;
    }

    public void setSurface_max(int surface_max) {
        this.surface_max = surface_max;
    }

    public int getNumalle() {
        return numalle;
    }

    public void setNumalle(int numalle) {
        this.numalle = numalle;
    }

    public String getNomSalle() {
        return nomSalle;
    }

    public void setNomSalle(String nomSalle) {
        this.nomSalle = nomSalle;
    }

    public String getBatiment() {
        return batiment;
    }

    public void setBatiment(String batiment) {
        this.batiment = batiment;
    }
}