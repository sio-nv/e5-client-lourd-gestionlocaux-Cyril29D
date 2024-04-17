package m2l.desktop.gestion.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import m2l.desktop.gestion.model.Climatiseur;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailsFournisseurController implements Initializable {
    @FXML
    private Label nom_fournisseur;
    @FXML
    private Label telephone_fournisseur;
    @FXML
    private Label adresse_fournisseur;


    public void mettre_a_jour_affichage(Climatiseur climatiseur)
    {
        System.out.println(getClass().getName()+" : "+climatiseur.getMarque());


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}