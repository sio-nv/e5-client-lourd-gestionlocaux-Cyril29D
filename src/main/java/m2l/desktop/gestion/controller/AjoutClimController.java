package m2l.desktop.gestion.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import m2l.desktop.gestion.model.Climatiseur;
import m2l.desktop.gestion.model.Model_Clim;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AjoutClimController implements Initializable {

    @FXML
    private Label label, erreur_marque, erreur_modele, erreur_puissance;

    @FXML
    private TextArea valpuiss, valmarque, valmodele, valconso, valson, valclasse;


    @FXML
    private void enregistrer_nouveau_climatiseur(ActionEvent event) throws IOException {
        System.out.println("Enregistrement du climatiseur .... ");
        boolean validation = true;

        int puiss = 0;
        String marque = valmarque.getText();
        String model = valmodele.getText();
        String classe = valclasse.getText();

        if (marque.length() == 0) {
            erreur_marque.setVisible(true);
            validation = false;
        }

        if (model.length() == 0) {
            erreur_modele.setVisible(true);
            validation = false;
        }

        if (classe.length() == 0) {
            valclasse.setText("NC");
        }

        try {
            puiss = Integer.valueOf(valpuiss.getText()).intValue();

        } catch (NumberFormatException ex) {
            erreur_puissance.setVisible(true);
            validation = false;
        }

        if (validation) {

            //création d'un nouvel objet
            Climatiseur c = new Climatiseur(marque, model, puiss);

            //ajout du climatiseur dans la liste du climatiseur du modele
            Model_Clim.getClimatiseurs().add(c);

            //Atelier Java/JavaFx part 2 exercice 2b
            Model_Clim.insertClimatiseur(c);
            //on revient à la fenêtre d'affichage de la liste de climatiseurs.
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }


    public void retourListe(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }


}