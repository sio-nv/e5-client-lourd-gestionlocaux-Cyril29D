package m2l.desktop.gestion.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import m2l.desktop.gestion.GestionLocaux;

/**
 *
 * @author nathalie
 */
public class AccueilController implements Initializable{

    /**
     * Permet d'accéder à la page interventions_jour
     * Les fonctionnalités de l'application sont alors accessibles
     * @throws IOException
     */
    @FXML
    public void load() throws IOException {

        GestionLocaux.setRoot("GestM2L");

    }

    /**
     * Méthode lancée au lancement de l'aplication
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
